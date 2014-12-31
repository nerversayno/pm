package cn.lcf.akka;

import akka.actor.*;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.japi.Creator;
import akka.japi.Option;
import akka.util.Timeout;
import javafx.concurrent.Task;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static akka.pattern.Patterns.pipe;
import static org.junit.Assert.assertEquals;

/**
 * Created by lcf on 2014/12/11.
 */
public class WathActorTest {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("MySystem");
//        testReceiveTimeout(actorSystem);
//        testHotSwapActor();
//        testTypedActorExtension(actorSystem);
//        mustGetTheTypedActorExtension(actorSystem);
        createATypedActor(actorSystem);
    }

    public static void createATypedActor(ActorSystem system) {
        try {
            Squarer mySquarer =
                    TypedActor. get(system) . typedActorOf(
                            new TypedProps<SquarerImpl>(Squarer. class, SquarerImpl. class) ) ;
            Squarer otherSquarer =
                    TypedActor. get(system) . typedActorOf(
                            new TypedProps<SquarerImpl>(Squarer. class,
                                    new Creator<SquarerImpl>() {
                                        public SquarerImpl create() { return new SquarerImpl("foo") ; }
                                    } ) ,
                            "name") ;
            mySquarer. squareDontCare(10) ;
            Future<Integer> fSquare = mySquarer. square(10) ; //A Future[Int]
            Option<Integer> oSquare = mySquarer. squareNowPlease(10) ; //Option[Int]
            int iSquare = mySquarer. squareNow(10) ; //Int
            assertEquals(100, Await. result(fSquare,
                    Duration.create(3, TimeUnit.SECONDS)) . intValue() ) ;
            assertEquals(100, oSquare. get() . intValue() ) ;
            assertEquals(100, iSquare) ;
            System.out.println(Await.result(fSquare,Duration.create(3,TimeUnit.SECONDS)).intValue());
            System.out.println(oSquare.get().intValue());
            System.out.println(iSquare);
            TypedActor. get(system) . stop(mySquarer) ;
            TypedActor. get(system) . poisonPill(otherSquarer) ;
        } catch(Exception e) {
//Ignore
        }
    }

    public static void mustGetTheTypedActorExtension(ActorSystem system) {
        ActorRef actorA = system.actorOf(Props.create(HotSwapActor.class), "actorA");
        try {
//Returns the Typed Actor Extension
            TypedActorExtension extension =
                    TypedActor.get(system); //system is an instance of ActorSystem
//Returns whether the reference is a Typed Actor Proxy or not
            TypedActor.get(system).isTypedActor(actorA);
//Returns the backing Akka Actor behind an external Typed Actor Proxy
            TypedActor.get(system).getActorRefFor(actorA);
//Returns the current ActorContext,
// method only valid within methods of a TypedActor implementation
//            ActorContext context = TypedActor.context();
//Returns the external proxy of the current Typed Actor,
// method only valid within methods of a TypedActor implementation
            Squarer sq = TypedActor.<Squarer>self();
//Returns a contextual instance of the Typed Actor Extension
//this means that if you create other Typed Actors with this,
//they will become children to the current Typed Actor.
            TypedActor.get(TypedActor.context());
        } catch (Exception e) {
            e.printStackTrace();
//dun care
        }
    }

    public static void testTypedActorExtension(ActorSystem actorSystem) {

        TypedActorExtension typedActorExtension = TypedActor.get(actorSystem);
        System.out.println(typedActorExtension.actorFactory());

    }

    public static void testReceiveTimeout(ActorSystem system) {
        ActorRef actorA = system.actorOf(Props.create(MyReceiveTimeoutUntypedActor.class), "actorA");
        Inbox inbox = Inbox.create(system);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 3) {
                inbox.send(actorA, "poison");
            }
            inbox.send(actorA, "Hello" + i);
        }

    }

    public static void testHotSwapActor() {
        final ActorSystem system = ActorSystem.create("MySystem");
        ActorRef actorA = system.actorOf(Props.create(HotSwapActor.class), "actorA");
        Inbox inbox = Inbox.create(system);
        inbox.send(actorA, Kill.getInstance());
        System.out.println(system.guardian().path());
        inbox.send(actorA, "hello");
    }

    public static void testReply() {
        final ActorSystem system = ActorSystem.create("MySystem");
        ActorRef actorA = system.actorOf(Props.create(WatchActor.class), "actorA");
        ActorRef actorB = system.actorOf(Props.create(WatchActor.class), "actorB");
        ActorRef actorC = system.actorOf(Props.create(WatchActor.class), "actorC");

        List<String> list = new ArrayList<>();

        final Timeout t = new Timeout(Duration.create(5, TimeUnit.SECONDS));
        final ArrayList<Future<Object>> futures = new ArrayList<Future<Object>>();
        futures.add(ask(actorA, new ImmutableMessage(1, list), 1000)); // using 1000ms timeout
        futures.add(ask(actorB, new ImmutableMessage(2, list), t)); // using timeout from
        final Future<Iterable<Object>> aggregate = Futures.sequence(futures, system.dispatcher());
        final Future<ImmutableMessage> transformed = aggregate.map(
                new Mapper<Iterable<Object>, ImmutableMessage>() {
                    public ImmutableMessage apply(Iterable<Object> coll) {
                        final Iterator<Object> it = coll.iterator();
                        it.forEachRemaining(s -> {
                            System.out.println(s);
                        });
                        return new ImmutableMessage(1, null);
                    }
                }, system.dispatcher());
        pipe(transformed, system.dispatcher()).to(actorC);
    }
}
