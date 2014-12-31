package cn.lcf.akka;

import akka.dispatch.Futures;
import akka.japi.Option;
import scala.concurrent.Future;

/**
 * Created by lcf on 2014/12/15.
 */
public class SquarerImpl implements Squarer {

    private String name;

    public SquarerImpl() {
        this.name = "default";
    }
    public SquarerImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void squareDontCare(int i) {
        int sq = i*i;
    }

    @Override
    public Future<Integer> square(int i) {
        return Futures.successful(i*i);
    }

    @Override
    public Option<Integer> squareNowPlease(int i) {
        return Option.some(i*i);
    }

    @Override
    public int squareNow(int i) {
        return i*i;
    }
}
