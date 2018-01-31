# kafka-playground
To play with Latest Apache kafka features

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
c)rename it to server-1.properties , server-2.properties , server-3.properties
d)update the broker id in each of the property namely 1 ,2 ,3
e) start the zookeeper and brokers

