package com.home.work.kafkaplay.service
import com.datastax.driver.core.BoundStatement
import com.datastax.driver.core.PreparedStatement
import com.datastax.driver.core.Session
import com.home.work.kafkaplay.domain.LoginHistory
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


@Service
@Slf4j
class LoginHistoryDao {

    @Autowired
    Session session

    PreparedStatement preparedStatement

    String query="INSERT INTO login_history(userId,createdAt,location,channel,device) VALUES (?,?,?,?,?)"


    @PostConstruct
    void init(){
        preparedStatement=session.prepare(query)
    }

/*
    LoginHistory loginHistory

    LoginHistoryDao(LoginHistory loginHistory){
        this.loginHistory=loginHistory
    }*/

    void insertCassandra(LoginHistory loginHistory) {
        try {

            log.info("CASSANDRA insertion is triggered for record ${loginHistory}")
            BoundStatement bs = preparedStatement.bind(loginHistory.uid, loginHistory.creationTime,loginHistory.location, loginHistory.channel, loginHistory.device)
            session.execute(bs)
        }catch (Exception e){
            log.error("Exception occurred while processing cassandra record",e)
        }
    }
}
