package cn.lcf.akka;

import akka.actor.UntypedActor;
import akka.japi.Procedure;

/**
 * Created by lcf on 2014/12/12.
 */
public class HotSwapActor extends UntypedActor {

    Procedure<Object> angry = new Procedure<Object>() {
        @Override
        public void apply(Object o) throws Exception {
            if (o.equals("angry")) {
                String reply = "I'm already angry!";
                System.out.println(getSender() + "," + reply);
                getSender().tell(reply, getSelf());
            } else if ("happy".equals(o)) {
                getContext().become(happy);
            }
        }
    };
    Procedure<Object> happy = new Procedure<Object>() {
        @Override
        public void apply(Object o) throws Exception {
            System.out.println(getSender() +" " + o.toString());
            if (o.equals("happy")) {
                String reply = "I'm happy now!";
                System.out.println(getSender() + "," + reply);
                getSender().tell(reply, getSelf());
            } else if ("angry".equals(o)) {
                getContext().become(angry);
            }
        }
    };

    @Override
    public void onReceive(Object message) throws Exception {
        if (message.equals("angry")) {
            getContext().become(angry);
        } else if (message.equals("happy")) {
            getContext().become(happy);
        } else {
            System.out.println(message);
        }
    }
}
