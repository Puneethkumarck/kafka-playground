package com.home.work.kafkaplay.domain

import org.springframework.stereotype.Component


@Component
class LoginHistory {

    String uid

    String creationTime

    String location

    String channel

    String device

    int loginCount
}

