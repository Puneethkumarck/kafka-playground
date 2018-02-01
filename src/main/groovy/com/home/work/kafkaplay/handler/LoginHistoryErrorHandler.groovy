package com.home.work.kafkaplay.handler

import groovy.util.logging.Slf4j
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.listener.ErrorHandler
import org.springframework.stereotype.Component


@Component
@Slf4j
class LoginHistoryErrorHandler implements ErrorHandler{
    @Override
    void handle(Exception thrownException, ConsumerRecord<?, ?> data) {
     log.error("Error occurred while processing consumer record for details topic=${data.topic()} key=${data.key()} partition=${data.partition()} timestamp=${data.timestamp()} ${data.dump()} exceptionMessage=${thrownException.message}")
    }
}
