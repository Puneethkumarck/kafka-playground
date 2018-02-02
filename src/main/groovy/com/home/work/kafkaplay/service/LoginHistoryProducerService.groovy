package com.home.work.kafkaplay.service
import com.fasterxml.jackson.databind.ObjectMapper
import com.home.work.kafkaplay.domain.LoginHistory
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback


@Slf4j
@Service
class LoginHistoryProducerService {


    @Autowired KafkaTemplate<Integer,String> kafkaTemplate

    @Autowired ObjectMapper objectMapper

    @Value('${login.history.topic}')
    String topic

    void sendToLoginHistoryKafka(LoginHistory loginHistory,int i){

        try {

            String data = objectMapper.writeValueAsString(loginHistory)
            ListenableFuture<SendResult<Integer,String>> future= kafkaTemplate.send(topic,objectMapper.writeValueAsString(loginHistory))
            future.addCallback(new ListenableFutureCallback<SendResult>() {
         @Override
         void onFailure(Throwable throwable) {
            log.error("Error occurred while sending login history data to kafka ${data}")
         }

         @Override
         void onSuccess(SendResult sendResult) {
            log.info("Success Callback --> Success On sending login history event to topic=${topic} with message=${data} offset=${sendResult.recordMetadata.offset()} partition=${sendResult.recordMetadata.partition()}")
         }
     })

        }catch(Exception e){
            log.error("Exception occurred while sending login history kafka message ",e)
        }

    }
}
