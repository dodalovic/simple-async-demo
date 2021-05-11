package com.odalovic.simpleasyncdemo.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

const val INCOMING_TOPIC = "order_created"
const val OUTGOING_TOPIC = "order_validated"

@Configuration
class KafkaTopicsConfiguration {

    @Value("\${spring.kafka.producer.bootstrap-servers}")
    lateinit var bootstrapServers: String

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs = mutableMapOf<String, Any>()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        return KafkaAdmin(configs)
    }

    @Bean
    fun outgoingTopic(): NewTopic {
        return NewTopic(OUTGOING_TOPIC, 1, 1.toShort())
    }

    @Bean
    fun incomingTopic(): NewTopic {
        return NewTopic(INCOMING_TOPIC, 1, 1.toShort())
    }
}