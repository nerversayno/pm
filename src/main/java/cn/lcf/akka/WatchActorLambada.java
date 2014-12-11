package cn.lcf.akka;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;

/**
 * Created by lcf on 2014/11/28.
 */
public class WatchActorLambada extends AbstractActor {
    private final ActorRef child = context() . actorOf(Props. empty() , "target") ;
    private ActorRef lastSender = context().system(). deadLetters() ;
    public WatchActorLambada() {
        context() . watch(child) ; // <-- this is the only call needed for registration
        receive(ReceiveBuilder.
                        matchEquals("kill", s -> {
                            context().stop(child);
                            lastSender = sender();
                        }).
                        matchAny(s -> {
                            System.out.println(s);
                        }).
                        match(Terminated.class, t -> t.actor().equals(child), t -> {
                            lastSender.tell("finished", self());
                        }).build()
        ) ;
    }

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem. create("MySystem") ;
        ActorRef myActor = system. actorOf(Props.create(WatchActorLambada.class),
                "watchLambada") ;

//        final Inbox inbox = Inbox. create(system) ;
//        inbox. send(myActor, "a") ;
//        inbox.send(myActor,"kill");

        myActor.tell("hello this is myActor",myActor);
    }
}
