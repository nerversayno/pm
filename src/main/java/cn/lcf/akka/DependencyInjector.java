package cn.lcf.akka;

import akka.actor.*;

class DependencyInjector implements IndirectActorProducer {
    final Object applicationContext;
    final String beanName;

    public DependencyInjector(Object applicationContext, String beanName) {
        this.applicationContext = applicationContext;
        this.beanName = beanName;
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return MyActor.class;
    }

    @Override
    public MyActor produce() {
        MyActor result = null;
// obtain fresh Actor instance from DI framework ...
        return result;
    }

    class MyActorD extends UntypedActor {
        final  ActorRef myActor = getContext().actorOf(
                Props.create(DependencyInjector.class, applicationContext, "MyActor"),
                "myactor3");
        @Override
        public void onReceive(Object o) throws Exception {

        }
    }

}
