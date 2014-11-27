package cn.lcf.socket;

import org.apache.log4j.net.SocketServer;

/**
 * User: lcf
 * Date: 2014/9/26
 * Time: 10:29
 * Description:
 */
public class MySocketTest {
    public static void main(String[] args) {
        new Thread(new MySocketServer(11824)).start();
        new Thread(new MyClientSocket("192.168.0.187",11824)).start();
    }
}
