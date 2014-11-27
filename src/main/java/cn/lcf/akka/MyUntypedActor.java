package cn.lcf.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

/**
 * Created by lcf on 2014/11/25.
 */
public class MyUntypedActor extends UntypedActor{
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            System.out.println("Received String message: "+ message) ;
            getSender().tell(message, getSelf()) ;
        } else
            unhandled(message);
    }

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem. create("MySystem") ;
         ActorRef myActor = system. actorOf(Props.create(MyUntypedActor.class),
                "myactor") ;
        System.out.println(myActor);
         ActorRef myActorA = system. actorOf(Props.create(A.class),
                "myactorA") ;
        System.out.println(myActorA);
    }
}

class A extends UntypedActor {
    final ActorRef child =
            getContext() . actorOf(Props. create(MyUntypedActor. class) , "myChild") ;

    @Override
    public void onReceive(Object o) throws Exception {

    }
// plus some behavior ...
}
 class MyActor extends UntypedActor{
     public MyActor(String s) {

     }

     @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            System.out.println("Received String message: "+ message) ;
            getSender().tell(message, getSelf() ) ;
        } else
            unhandled(message);
    }
}
 class MyActorC implements Creator<MyActor> {
    @Override public MyActor create() {
        return new MyActor("...") ;
    }
}

