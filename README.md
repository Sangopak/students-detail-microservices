# students-detail-microservices

microservices will fetch student basic data in future it will interact with other microservices to get student course data

## Getting Started

We will be using a Docker Tool box to create container to run a single node Cassandra Cluster.

* Below commands could be used to start the node and create the basic table

```
	docker run --name=node1 -d tobert/cassandra
	docker exec -it node1 cqlsh
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