package com.home.work.kafkaplay.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext
import org.springframework.data.cassandra.mapping.CassandraMappingContext


@Configuration
class CassandraConfig extends AbstractCassandraConfiguration {

    @Value('${keyspace.name}')
    String keySpace


    @Bean
    CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean()
        cluster.setContactPoints("127.0.0.1")
        cluster.setPort(9042)
        return cluster
    }

    @Override
    protected String getKeyspaceName() {
        return keySpace
    }


    @Bean
    CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext()
    }

}
