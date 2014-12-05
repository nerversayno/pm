package cn.lcf.akka;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class Follower extends AbstractActor {
    final Integer identifyId = 1;

    public Follower() {
        ActorSelection selection = context().actorSelection("/user/another");
        selection.tell(new Identify(identifyId), self());
        receive(ReceiveBuilder.
                        match(ActorIdentity.class, id -> id.getRef() != null, id -> {
                            ActorRef ref = id.getRef();
                            context().watch(ref);
                            context().become(active(ref));
                        }).
                        match(ActorIdentity.class, id -> id.getRef() == null, id -> {
                            context().stop(self());
                        }).build()
        );
    }

    final PartialFunction<Object, BoxedUnit> active(final ActorRef another) {
        return ReceiveBuilder.
                match(Terminated.class, t -> t.actor().equals(another), t -> {
                    context().stop(self());
                }).build();
    }
}