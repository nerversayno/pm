package cn.lcf.akka;

import akka.japi.Option;
import scala.concurrent.Future;

public interface Squarer {
    void squareDontCare(int i); //fire-forget

    Future<Integer> square(int i); //non-blocking send-request-repl

    Option<Integer> squareNowPlease(int i); //blocking send-request-

    int squareNow(int i); //blocking send-request-reply
}