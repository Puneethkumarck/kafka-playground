package com.home.work.kafkaplay.config

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CassandraConfig {

    @Bean
    Session getSession(){
        Cluster cluster=Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).build()
        cluster.connect('login')
    }
}
