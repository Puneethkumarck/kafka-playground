

- Producer sends the message to kafka , consumer polls the kafka for the messages .

 ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core.jpg)
 
 - More than one broker forms the kafka cluster , which helps to acheive fault tolerant system & scale horizontally
 
  ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core_2.jpg)
  
 - Create the topics in the kafka broker to exchange the messages related to single domain. Ex : Order_topics , paymants_topic , product_topic etc.
 
 ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core_topic.jpg)
 
 - Create the partitions which helps further splits the messges & maintain the replicas to avoid loss of messages & allows parrallel/concurrent message processing
  
  ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core_partition.jpg)
  
  ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core_partitions.jpg)
  
  ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core_partitions_rp.jpg)
  
 - Messages are stored in the partitions , kafka uses object key hashing mechanism to locate partitions for the incoming messages, developer can take the control of the partitioner by creating their own custom partitioner which guides messages to respective partition created in topics.
   kafka follows leader & follower pattern to maintain message replicas and monitor the follower. if leader goes down , one of the follower becomes the leader.
   
   ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core_partitions_Leader_follower.jpg)
   
  - kafka producer,broker & consumer representation,multiple producer,partition & single consumer slow down the consumer throghput.
  
  ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core_producer_consumer.jpg)
  
  - Consumer groups is an single logical unit , each consumer in the group assigned to an separate partition to process the messages , which allows parallel processing of the message & increases the comsumer throughput.
    Formula is to number of partition = number of consumer , any extra consumer waste since kafka assign single consumer to each partition @ any given point of time. 
    ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core_producer_consumer_group.jpg)
    
  - offset - is an unique number is assigned for each message to identify message uniquely.
  
   ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/core_offset.jpg)
   
   - Kafka producer consumer life cycle 
        - producer send the message to kafka topic with (topicName,objectkey,value)
        - serilzes the messages as specified
        - partitioner assign the partition for the message.
        - partition buffer stores the messages in batch (which is confogurabale)
        - once buffer reaches the specified limit , which sends message to kafka.
        - consumer polls the kafka for mesage to process
        - consumer process the message and commit the processed message (enable the autocommit=true)(else do manual commit)
        - sends back the record Metadata which contanis the commited offset and timestamp details
        
  ![Settings Window](https://raw.github.com/Puneethkumarck/kafka-playground/master/screen_shots/kafka_producer-consumer-lifecycle.jpg)
  
  pictures taken from learning_journel .
   

  
