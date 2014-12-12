package cn.lcf.akka;

import akka.actor.PoisonPill;
import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;

public class MyReceiveTimeoutUntypedActor extends UntypedActor {
    public MyReceiveTimeoutUntypedActor() {
// To set an initial delay
        getContext().setReceiveTimeout(Duration.create("3 seconds"));
    }

    public void onReceive(Object message) {
        System.out.println(getSender() + " receive " + message);
        if("hello".equals(message)){
            getContext().setReceiveTimeout(Duration.create("4 seconds"));
        }else  if("poison".equals(message)){
            getSelf().tell(PoisonPill.getInstance(),getSelf());
        }
    }
}