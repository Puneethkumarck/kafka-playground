package com.home.work.kafkaplay.controller
import com.google.common.base.Stopwatch
import com.home.work.kafkaplay.domain.LoginHistory
import com.home.work.kafkaplay.handler.LoginRequestHandler
import com.home.work.kafkaplay.service.LoginHistoryProducerService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.TimeUnit


@Slf4j
@RestController
@RequestMapping("/kafka")
class LoginHistoryController {


    @Autowired LoginRequestHandler loginRequestHandler

    @Autowired LoginHistoryProducerService loginHistoryProducerService

    @RequestMapping("/loginhistory/{from}/{to}")
    Object loginHistoryEvent(@PathVariable int from,@PathVariable int to){

        Stopwatch stopwatch = Stopwatch.createStarted()

        if(to>from)
        (from..to).each {
           LoginHistory loginHistory= loginRequestHandler.buildLoginHistoryRequest()
           loginHistoryProducerService.sendToLoginHistoryKafka(loginHistory,it)
        }
        stopwatch.stop()

            log.info("Total time taken to process the request is =${stopwatch.elapsed(TimeUnit.SECONDS)}")

                "SUCCESS"
       }
}
