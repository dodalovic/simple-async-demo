kafka-up:
	docker-compose up -d
kafka-down:
	docker-compose down -v
run-app:
	./gradlew -p $(app) bootRun