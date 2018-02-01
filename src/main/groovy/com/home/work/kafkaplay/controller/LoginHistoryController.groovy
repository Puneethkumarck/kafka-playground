package com.home.work.kafkaplay.controller

import com.home.work.kafkaplay.domain.LoginHistory
import com.home.work.kafkaplay.handler.LoginRequestHandler
import com.home.work.kafkaplay.service.LoginHistoryProducerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController



@RestController
@RequestMapping("/kafka")
class LoginHistoryController {


    @Autowired LoginRequestHandler loginRequestHandler

    @Autowired LoginHistoryProducerService loginHistoryProducerService

    @RequestMapping("/loginhistory/{from}{to}")
    Object loginHistoryEvent(@PathVariable int from,@PathVariable int to){

        if(to>from)
        (from..to).each {
           LoginHistory loginHistory= loginRequestHandler.buildLoginHistoryRequest()
           loginHistoryProducerService.sendToLoginHistoryKafka(loginHistory,it)
        }

        "SUCCESS"
    }


}
