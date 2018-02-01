package com.home.work.kafkaplay.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.home.work.kafkaplay.domain.LoginHistory
import groovy.util.logging.Slf4j
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicInteger

@Slf4j
@Service
class LoginHistoryConsumerService {

    AtomicInteger consumerCount=new AtomicInteger(0)

    @Autowired ObjectMapper objectMapper

    @Autowired LoginHistoryDao loginHistoryDao

    @KafkaListener(topics="LOGIN_HISTORY_TOPIC")
    void process(ConsumerRecord<?,?> record){
        try {
            Optional<?> messages = Optional.ofNullable(record.value())
            Object message=null
            if (messages.isPresent()) {
                message  = messages.get()
            }
            log.info("Consumer service incoming message is = ${message} and count is ${consumerCount.incrementAndGet()}")
            LoginHistory loginHistory = objectMapper.readValue(message as String, LoginHistory.class)
            CompletableFuture.runAsync({ new LoginHistoryDao(loginHistory)})
        }catch (Exception e){
            log.error("Exception occurred while processing login history kafka message @ consumer",e)
        }
    }

}
