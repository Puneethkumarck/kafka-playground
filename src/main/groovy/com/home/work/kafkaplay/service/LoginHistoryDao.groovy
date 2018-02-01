package com.home.work.kafkaplay.service
import com.datastax.driver.core.BoundStatement
import com.datastax.driver.core.PreparedStatement
import com.datastax.driver.core.Session
import com.home.work.kafkaplay.domain.LoginHistory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class LoginHistoryDao implements Runnable{

    @Autowired
    Session session

    PreparedStatement preparedStatement

    String query="INSERT INTO login_history(userId,createdAt,location,channel,device,loginCount) VALUES (?,?,?)"

    void init(){
        preparedStatement=session.prepare(query)
    }

    LoginHistory loginHistory

    LoginHistoryDao(LoginHistory loginHistory){
        this.loginHistory=loginHistory
    }


    @Override
    void run() {
        BoundStatement bs=preparedStatement.bind(this.loginHistory.uid,this.loginHistory.creationTime,this.loginHistory.channel,this.loginHistory.device,this.loginHistory.loginCount)
        session.execute(bs)
    }
}
