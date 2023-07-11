<h1>Groceries shopping list application</h1>

<h2>additional services</h2>

<h3>container creation and starting database instance</h3>
podman run --pod postgre-sql \
-e 'PGADMIN_DEFAULT_EMAIL=someEmail@test.com' \
-e 'PGADMIN_DEFAULT_PASSWORD=123456'  \
--name pgadmin \
-d docker.io/dpage/pgadmin4:14


podman run --name db --pod=postgre-sql -d \
    -e POSTGRES_USER=user \
    -e POSTGRES_PASSWORD=123456 \
    docker.io/library/postgres:14

<h3>container creation and starting message broker and load balancer</h3>

initialize Zookeeper service
podman run -d --name zookeeper --net kafka \
    -e ZOOKEEPER_CLIENT_PORT=2181 \
    -e ZOOKEEPER_TICK_TIME=2000 \
    -p 22181:2181 confluentinc/cp-zookeeper:latest

initialize Kafka service
podman run -d --name kafka --net kafka -e KAFKA_BROKER_ID=1 \
    -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
    -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092 \
    -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT \
    -e KAFKA_INTER_BROKER_LISTENER_NAME=PLAINTEXT \
    -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
    -p 29092:29092 confluentinc/cp-kafka:latest

<p>
to connect to the container
<br>podman exec -it kafka bash
<br> to create topic
kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
<br>to verify topic existence
kafka-topics --describe --topic quickstart-events --bootstrap-server localhost:9092
<br>to test message producer
kafka-console-producer --topic quickstart-events --bootstrap-server kafka:9092
<br>to test message consumer
kafka-console-consumer --topic quickstart-events --bootstrap-server kafka:9092 --from-beginning</p>