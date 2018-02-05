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




Other useful commands
======================
docker-compose scale kafka=3

docker-compose stop

alter partitions in exiting topic - 

bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic login_history_topic --partition=10

Note: if partitions are increased for a topic that has a key , the partition logic or ordering of the messages will be affected.




