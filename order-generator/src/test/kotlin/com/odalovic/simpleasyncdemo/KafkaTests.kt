package com.odalovic.simpleasyncdemo

import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.ClassRule
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.util.*

@SpringBootTest
@Testcontainers
class KafkaTests {
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, Order>

    @Autowired
    lateinit var testConsumer: TestConsumer

    companion object {
        @ClassRule
        @JvmField
        var kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"))

        @DynamicPropertySource
        @JvmStatic
        fun postgresqlProperties(registry: DynamicPropertyRegistry) {
            kafka.start()
            with("spring.kafka.producer") {
                registry.add("$this.bootstrap-servers") { kafka.bootstrapServers }
                registry.add("$this.key-serializer") { "org.apache.kafka.common.serialization.StringSerializer" }
                registry.add("$this.value-serializer") { "org.springframework.kafka.support.serializer.JsonSerializer" }
            }
            with("spring.kafka.consumer") {
                registry.add("$this.bootstrap-servers") { kafka.bootstrapServers }
                registry.add("$this.group-id") { "test" }
                registry.add("$this.auto-offset-reset") { "earliest" }
                registry.add("$this.key-deserializer") { "org.apache.kafka.common.serialization.StringDeserializer" }
                registry.add("$this.value-deserializer") { "org.springframework.kafka.support.serializer.JsonDeserializer" }
                registry.add("$this.properties.spring.json.trusted.packages") { "*" }
            }
        }
    }

    @Test
    fun `publishes and consumes an order`() = runBlocking<Unit> {
        kafkaTemplate.send("topic", Order(UUID.randomUUID(), emptyList()))
        delay(1000L)
        testConsumer.latch.count shouldBe 0
    }
}
