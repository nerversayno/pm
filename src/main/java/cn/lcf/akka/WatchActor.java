package cn.lcf.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

public class WatchActor extends UntypedActor {
    final ActorRef child = this.getContext().actorOf(Props.empty(), "child");

    {
        this.getContext().watch(child); // <-- the only call needed for registration
    }

    ActorRef lastSender = getContext().system().deadLetters();

    @Override
    public void onReceive(Object message) {
        System.out.println(getSelf().toString()+" receive " + message +" from " + getSender().toString());
        if (message.equals("kill")) {
            getContext().stop(child);
            lastSender = getSender();
        } else if (message instanceof ImmutableMessage) {
            System.out.println(getSender().toString());
            List<String> list= new ArrayList<String>();
            list.add(getSelf().toString() + " reply " + ((ImmutableMessage) message).getSequenceNumber());
            getSender().tell( new ImmutableMessage(((ImmutableMessage) message).getSequenceNumber()+1,list), getSelf());
        } else {
            unhandled(message);
        }
    }
}