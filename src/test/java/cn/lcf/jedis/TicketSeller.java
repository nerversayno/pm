package cn.lcf.jedis;

import cn.lcf.dao.ITicket;
import cn.lcf.entity.Ticket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User: lcf
 * Date: 2014/8/21
 * Time: 14:00
 * Description:
 */
public class TicketSeller implements Runnable {

    private String SERVER_SELL_URL = ITicket.SERVER + ITicket.SELL;

    private int clientNumber;

    private int maxRandom;

    private long sellCount;
    private long start;
    public  Boolean flag = true;
    Queue<String> queue = new ConcurrentLinkedQueue<String>();

    BufferedWriter bufferedWriter;

    public TicketSeller(int clientNumber, int maxRandom) {
        this.clientNumber = clientNumber;
        this.maxRandom = maxRandom;
     //   new Thread(new TicketWriter(clientNumber,queue,flag)).start();
//        bufferedWriter = new TicketWriterF(clientNumber).bufferedWriter;
    }

    @Override
    public void run() {
        start = System.currentTimeMillis();
        flag = true;
        while (TicketMonitor.flag){
            try {
                if(TicketMonitor.susFlag) {
                    Thread.sleep(1000);
                    System.out.println(clientNumber + " 暂停售票,休息1秒");
                    continue;
                }

                //售票
                 StringBuffer stringBuffer = new TicketConnector(SERVER_SELL_URL
                +"?client="+clientNumber).connect();
//                bufferedWriter.write(stringBuffer.toString()+"\n");
//                bufferedWriter.flush();
              //   queue.add(stringBuffer.toString());
                //记录
//                System.out.println(stringBuffer.toString());
                if(stringBuffer.toString().length() >0 ){
                    sellCount++;
                }else{
                    flag = true;
                }
//                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    System.out.println(clientNumber + "售票失败,休息1秒");
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                flag = false;
            } finally {
            }
        }
        try {
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
        }
        System.out.println(clientNumber + " 停止售票, sell "
                + (double)sellCount/(double)10000 +"万 use " + (double)(System.currentTimeMillis() -start)/(double)1000 +"秒");
    }
}
