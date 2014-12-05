package cn.lcf.akka;

import akka.actor.*;
import akka.japi.Creator;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class DemoActor extends UntypedActor {
    /**
     * Create Props for an actor of this type.
     *
     * @param magicNumber The magic number to be passed to this actor’s constructo
     * @return a Props for creating this actor, which can then be further configur
     * (e.g. calling `.withDispatcher()` on it)
     */
    public static Props props(final int magicNumber) {
        return Props.create(new Creator<DemoActor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public DemoActor create() throws Exception {
                return new DemoActor(magicNumber);
            }
        });
    }

    final int magicNumber;

    public DemoActor(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void onReceive(Object msg) {
// some behavior here
    }

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem. create("MySystem") ;
        ActorRef myActor = system. actorOf(Props.create(MyUntypedActor.class),
                "myactor") ;
        System.out.println(myActor.path());
        System.out.println(ActorSelection.serialVersionUID);
        System.out.println(myActor);
        ActorRef myActorA = system. actorOf(Props.create(A.class),
                "myactorA") ;
        System.out.println(myActorA);
        final Inbox inbox = Inbox. create(system) ;
        inbox. send(myActor, "hello") ;

        ActorRef actorRef = system.actorOf(Props.create(WatchActor.class));
        inbox.send(actorRef,"kill");
    }
}