# students-detail-microservices

microservices will fetch student basic data in future it will interact with other microservices to get student course data

## Getting Started

We will be using a Docker Tool box to create container to run a single node Cassandra Cluster.

* Below commands could be used to start the node, get docker machine ip, create the keyspace and table

```
	docker run --name cassandraDb -d -p 7199:7199 -p 7000:7000 -p 9042:9042 -p 9160:9160 -p 7001:7001 cassandra:3.11
	docker-machine ip
	docker exec -it cassandraDb cqlsh
	CREATE KEYSPACE student_keyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'}  AND durable_writes = true;
	CREATE TABLE student_keyspace.students (
    id uuid,
    name text,
    dob timestamp,
    city text,
    course text,
    state text,
    street text,
    street2 text,
    zip text,
    PRIMARY KEY (id, name, dob)
) WITH CLUSTERING ORDER BY (name ASC, dob ASC);
```

## Install

```
	mvn clean package
```

## Running Test

```
	TBD
```

### Running the microservice

```
	java -jar students-detail-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=local
```
#		