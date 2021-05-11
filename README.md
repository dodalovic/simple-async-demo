# simple-async-demo

Demonstrates asynchronous communication between two headless services:

* `order-generator`
* `order-validator`

## App configuration

### Required properties

None

### Optional properties

Any of two apps by default connects to `localhost:9092` kafka, but we can override the property by setting from outside:

```
spring.kafka.consumer.bootstrap-servers
```

## Running apps locally

From the root directory, execute the following commands in different shells (tabs / panes):

* `make kafka-up`
* `make run-app app=order-generator`
* `make run-app app=order-validator`