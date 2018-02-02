package com.home.work.kafkaplay.domain
import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.mapping.Table



@Table("login_history")
class LoginHistory {

    @PrimaryKeyColumn(name = 'userId', ordinal = 0,type=PrimaryKeyType.PARTITIONED)
    String uid

    @Column('created')
    String creationTime

    @PrimaryKeyColumn(name = "location", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    String location

    @Column('channel')
    String channel

    @Column('device')
    String device

    @Column('count')
    int loginCount
}

