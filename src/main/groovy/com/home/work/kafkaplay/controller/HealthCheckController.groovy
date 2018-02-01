package com.home.work.kafkaplay.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class HealthCheckController {

    @Value('${application.name}')
    String applicationName

    @Value('${application.status}')
    String status

    @Value('${java.version}')
    String version


    @GetMapping(value = "/healthcheck",produces = "application/json")
    @ResponseBody
    Object getHealthCheck(){
        ["appName":applicationName,"version":version,"status":status]
    }
}
