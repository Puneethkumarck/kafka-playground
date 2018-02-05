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
     
   
 - Install kafka using docker machine & docker compose
 
    - cd dcoker
    - docker-compose full-stack-single-zoo-multi-broker.yml




(https://hub.docker.com/r/wurstmeister/kafka/)
  
scaling kafka brokers using docker-compose

docker-compose up -d

#### Add more brokers:

docker-compose scale kafka=3

#### Destroy a cluster:

docker-compose stop

Installing virutal box to create docker machine in MAC 

brew cask install virtualbox

Manual Kafka installation three brokers on single node 

a)download the kafka from kafka offcial website , extract the zip file

b)copy server.properties from kafka config directory

c)Rename it to server-1.properties , server-2.properties , server-3.properties

d)update the broker id in each of the property file .

e) start the zookeeper and brokers

