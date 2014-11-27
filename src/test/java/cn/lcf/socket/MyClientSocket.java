package cn.lcf.socket;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * User: lcf
 * Date: 2014/9/26
 * Time: 10:04
 * Description:
 */
public class MyClientSocket implements Runnable{
    Socket socket;

    public MyClientSocket(String host,int port) {
        try {
            this.socket = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BufferedReader bufferedReader;
            BufferedWriter      bufferedWriter;
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (true){
                 line = bufferedReader.readLine();
                 if (line.equals("ok")){
                     bufferedWriter.write(stringBuffer.toString());
                     bufferedWriter.flush();
                     stringBuffer = new StringBuffer();
                 } else {
                     stringBuffer.append(line+"\n");
                 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
