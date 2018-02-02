package com.home.work.kafkaplay.service
import com.home.work.kafkaplay.domain.LoginHistory
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service



@Service
@Slf4j
class LoginHistoryDao {

    @Autowired CassandraTemplate cassandraTemplate

    void insertCassandra(LoginHistory loginHistory) {
        try {
            log.info("CASSANDRA insertion is triggered for record ${loginHistory}")
            cassandraTemplate.insert(loginHistory)
        }catch (Exception e){
            log.error("Exception occurred while processing cassandra record",e)
        }
    }
}
