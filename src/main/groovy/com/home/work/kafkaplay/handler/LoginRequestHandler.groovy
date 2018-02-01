package com.home.work.kafkaplay.handler

import com.home.work.kafkaplay.domain.LoginHistory
import org.springframework.stereotype.Component

import java.text.SimpleDateFormat
import java.util.concurrent.atomic.AtomicInteger


@Component
class LoginRequestHandler {

    AtomicInteger atomicInteger=new AtomicInteger(0)

   LoginHistory buildLoginHistoryRequest(){
       LoginHistory loginHistory=new LoginHistory()
       loginHistory.channel="mobile"
       loginHistory.uid=uuidGenerator()
       loginHistory.creationTime=creationTime()
       loginHistory.device=getDevice()
       loginHistory.location=getLocation()
       loginHistory.loginCount=atomicInteger.incrementAndGet()
       loginHistory
    }

    String uuidGenerator(){
        UUID uuid = UUID.randomUUID()
        uuid.toString()
    }

    String creationTime(){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, y-M-d 'at' h:m:s a z")
        dateFormatter.format(new Date())
    }

    String getDevice(){
        ArrayList<String> deviceList=["samsung","apple","windows","google nexus","lenovo","vivo","Red me"]
        Random random=new Random()
        deviceList.get(random.nextInt(deviceList.size()))
    }

    String getLocation(){
        ArrayList<String> locationList=["bangalore","chennai","delhi","mumbai","seattle","delaware","newyork"]
        Random random=new Random()
        locationList.get(random.nextInt(locationList.size()))
    }
}
