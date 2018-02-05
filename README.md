kafka-playground
================
Apache kafka is a distributed streaming platform that enables publish and subscribe to a stream of records stored in a fault tolerant way also letting you process this stream of records as it occurs.

Kafka Components
================

- Broker/kafka server - Kafka messaging platform 
- Producer - which is an application send the message to the kafka
- Consumer - which is an application that receives/cosnumes data from kafka
- Topic -  name of a kafka stream / Placeholder to send and recieves message from kafka .
- Partition - part of a topic
- Offset - unique id for a message within partition
- Consumer Groups -  a group of consumers acting as a single logical unit

core concepts
=============

- [Introduction](theory/core_concept.md)
- [Producer Config](theory/producer_config.md)
- [Consumer Config](theory/consumer_config.md)
- [kafka offset](theory/kafka-offset.md)

kafka Installation
==================
 - Install single broker from downloading kafka distribution from offcial website
     - download the binary distribution from [https://kafka.apache.org/downloads]
     - extract the tar file into local drive.
     - start the zookeeper first.
     
       cd kafka_2.11-1.0.0 (respective folder u have extracted in previous step)
       
       bin/zookeeper-server-start.sh config/zookeeper.properties
       
     - start the broker
     
       bin/kafka-server.start.sh config/server.properties
     
     - create the kafka topics
     
       bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 2 --partition 3 --topic login_history_topic
    
 - Install kafka downloading from confluent center 
 
   Confluent center is an company which is managing kafka . Binary from confluent center allows us to start the zookeeper,
   broker and schema registry usning an single command . and also it provides kafka monitoring features via kafka control          
   center UI. 
   
   - download binary distribution from (https://www.confluent.io/download/)
   - set the path varibale , to execute the confluent commands 
     
     export CONFLUENT_HOME=/Documents/confluent-4.0.0
     export PATH=$CONFLUENT_HOME/bin:$PATH
     
   - confluent start . which will start zookeeper , kafka , schema-registry ,kafka rest & kafka connect .
   
   - create the kafka topics
   
     CONFLUENT_HOME/kafka-topics --create --zookeeper localhost:2181 --replication-factor 2 --partitions 3 login_history_topic
   
   - start the control center for to check cluster health and moniter the kafka broker /topics and throughputs & latency etc.
   
    $CONFLUENT_HOME/bin/control-center-start  $CONFLUENT_HOME/etc/confluent-control-center/control-center.properties
     
   
 - Install kafka using docker machine & docker compose
 
    - cd dcoker
    - docker-compose full-stack-single-zoo-multi-broker.yml


 - Install three kafka broker/cluster in single node/machine.
 
     - download the binary distribution from [https://kafka.apache.org/downloads]
     - extract the tar file into local drive.
     - start the zookeeper first.
     
       cd kafka_2.11-1.0.0 (respective folder u have extracted in previous step)
       
       bin/zookeeper-server-start.sh config/zookeeper.properties
       
     -  make three copies of server.properties under config/server.properties namely server-1.properties ,server-2.propeties
        & server-3.properties
        
     - update the broker id in each of the properties as 1 , 2 & 3 . broker id should be unique in the cluster.
     
     - start the brokers
     
       bin/kafka-server.start.sh config/server-1.properties
       
       bin/kafka-server.start.sh config/server-2.properties
       
       bin/kafka-server.start.sh config/server-3.properties
       
    - create the kafka topics 
    
     bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partition 6 --topic login_history_topic

Tech
===
  * Groovy
  * kafka
  * cassandra
  * spring-data-cassandra
  * spring boot
  * Docker Tool box
  * Docker compose
  * Kitematic
  * Mvn
  * Intelli j idea
  
RUN
===

This App built to demonstrates the kafka features , Have created the controller accepts the number of requests which needs to be sent to     kafka topics , assume you are working on a application where you are having millions of logins to your application in a seconds , you need to store each login details in an table to maintain the login history of the each user. later you can use this data for analytics and many other purpose as per usecase. This App dynamically creates an login details and sends the message to kafka topics(login_history_topic), Kafka_listner in the application will listen to (login_history_topic) which process the each of the message and stores in cassandra login_hostory table.

Login history details storing in cassandra .
 - uid (userId) -Unique id of the logged in customer
 - createdAt - login time
 - location - location from where he logged in
 - channel - to identify from which channel he has logged in (mobile,desktop,tablet etc)
 - device - device from which he has logged in
 - loginCount - sequnece number

   - Clone the repo into your local drive
   - start zookeeper & kafka broker using any of the above steps
   - strat the cassandra
     - cd cloned_repo
     - cd docker
     - cd docker-compose.yml (which starts cassandra instance in docker)
   - mvn clean install
   - mvn spring-boot:run

### Request : curl -v http:localhost:8080/kafka/loginhistory/1/100000


Other useful commands
======================
docker-compose scale kafka=3

docker-compose stop

alter partitions in exiting topic - 

bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic login_history_topic --partition=10

Note: if partitions are increased for a topic that has a key , the partition logic or ordering of the messages will be affected.




