# simple-async-demo

Demonstrates asynchronous communication between two headless services:

* order-generator
* order-validator

## App configuration

### Required properties

### Optional properties

Any of two apps by default connects to `localhost:9092` kafka, but we can override the property by setting from outside:

```
spring.kafka.consumer.bootstrap-servers
```
