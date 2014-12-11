package cn.lcf.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static akka.pattern.Patterns.pipe;

/**
 * Created by lcf on 2014/12/11.
 */
public class WathActorTest {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem. create("MySystem") ;
        ActorRef actorA = system. actorOf(Props.create(WatchActor.class), "actorA") ;
        ActorRef actorB = system. actorOf(Props.create(WatchActor.class), "actorB") ;
        ActorRef actorC = system. actorOf(Props.create(WatchActor.class), "actorC") ;

        List<String> list = new ArrayList<>();

        final Timeout t = new Timeout(Duration. create(5, TimeUnit.SECONDS) ) ;
        final ArrayList<Future<Object>> futures = new ArrayList<Future<Object>>() ;
        futures. add(ask(actorA,new ImmutableMessage(1,list), 1000) ) ; // using 1000ms timeout
        futures. add(ask(actorB,new ImmutableMessage(2,list), t) ) ; // using timeout from
        final Future<Iterable<Object>> aggregate = Futures. sequence(futures,system.dispatcher()) ;
        final Future<ImmutableMessage> transformed = aggregate. map(
                new Mapper<Iterable<Object>, ImmutableMessage>() {
                    public ImmutableMessage apply(Iterable<Object> coll) {
                        final Iterator<Object> it = coll. iterator() ;
                        it.forEachRemaining(s->{
                            System.out.println(s);
                        });
                        return new ImmutableMessage(1,null);
                    }
                } , system. dispatcher() ) ;
        pipe(transformed,system.dispatcher()).to(actorC);
    }
}
