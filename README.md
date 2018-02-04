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


## Install kafka with docker

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

