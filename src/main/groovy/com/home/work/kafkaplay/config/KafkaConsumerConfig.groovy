package com.home.work.kafkaplay.config
import com.home.work.kafkaplay.handler.LoginHistoryErrorHandler
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer



@Configuration
@EnableKafka
class KafkaConsumerConfig {

    @Value('${consumer.concurrency}')
    private int consumerConcurrency

    @Value('${login.history.topic}')
    private String loginHistoryTopic

    @Value('${bootstrap.servers}')
    private String bootStrapServers

    @Autowired
    LoginHistoryErrorHandler loginHistoryErrorHandler

    @Bean
    Map<String, Object> consumerConfigs() {
        Map<String, Object> properties = [:]
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers)
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false)
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100")
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000")
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "LOGIN_HISTORY_GROUP")
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
        return properties
    }

   @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> concurrentMessageListenerContainer=new ConcurrentKafkaListenerContainerFactory<>()
        concurrentMessageListenerContainer.consumerFactory=consumerFactory()
        concurrentMessageListenerContainer.concurrency=consumerConcurrency
        concurrentMessageListenerContainer.getContainerProperties().errorHandler=loginHistoryErrorHandler
        concurrentMessageListenerContainer.getContainerProperties().pollTimeout=2000
        concurrentMessageListenerContainer
    }



     @Bean
     ConsumerFactory<String,String> consumerFactory(){
         return new DefaultKafkaConsumerFactory<String, String>(consumerConfigs())
     }

}
