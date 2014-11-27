package cn.lcf.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

public class WatchActor extends UntypedActor {
    final ActorRef child = this.getContext().actorOf(Props.empty(), "child");

    {
        this.getContext().watch(child); // <-- the only call needed for registration
    }

    ActorRef lastSender = getContext().system().deadLetters();

    @Override
    public void onReceive(Object message) {
        System.out.println("WatchActor onReceive " + message);
        if (message.equals("kill")) {
            getContext().stop(child);
            lastSender = getSender();
        } else if (message instanceof Terminated) {
            final Terminated t = (Terminated) message;
            if (t.getActor() == child) {
                lastSender.tell("finished", getSelf());
            }
        } else {
            unhandled(message);
        }
    }
}