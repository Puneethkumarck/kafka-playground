# kafka-playground
To play with Latest Apache kafka features

## Install kafka with docker

(https://hub.docker.com/r/wurstmeister/kafka/)
  
scaling kafka brokers using docker-compose

docker-compose up -d

Add more brokers:
docker-compose scale kafka=3

Destroy a cluster:
docker-compose stop
