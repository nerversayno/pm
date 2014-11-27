package cn.lcf.jedis;

import cn.lcf.dao.ITicket;
import cn.lcf.entity.Ticket;

/**
 * User: lcf
 * Date: 2014/8/21
 * Time: 14:00
 * Description:
 */
public class TicketMonitor implements Runnable {

    private String SERVER_MONITOR_URL = ITicket.SERVER +ITicket.LENGTH;


    public static final long MIN_NUMBER = 100000;

    private int clientNumber;
    private long count;
    private long maxTime;

    private long start;

    public static boolean susFlag = false;

    public static boolean flag = true;

    public TicketMonitor(int clientNumber,long count,long maxTime) {
        this.clientNumber = clientNumber;
        this.maxTime = maxTime;
        this.count =count;
    }

    @Override
    public void run() {
        start = System.currentTimeMillis();
        flag = true;
        while (flag){
            if(System.currentTimeMillis()-start >= maxTime) {
                flag =false;
                System.out.println("售票时间:"+(double)maxTime/(double)(1000*60)+" 分钟已耗尽");
                 continue;
            }
//            try {
//                //售票
//                StringBuffer stringBuffer = new TicketConnector(SERVER_MONITOR_URL).connect();
//                //记录
//                System.out.println("票池余量" + stringBuffer.toString());
//                long length = Long.valueOf(stringBuffer.toString());
//                if(length<MIN_NUMBER){
////                if(flag)  {
//                     if(!TicketAdder.isAdding && count>0){
//                         System.out.println("剩余" +count+"次  增加票池");
//                         new  Thread(new TicketAdder(1,TicketAdder.MIN_COUNT)).start();
//                         count--;
//                     }
//                    if(count == 0 && length ==0 ){
//                        TicketMonitor.susFlag = true;
//                        flag=false;
//                    }else {
//                        TicketMonitor.susFlag = false;
//                    }
//
//                }  else {
//                    TicketMonitor.susFlag = false;
//                }
//                Thread.sleep(5000);
//            } catch (Exception e) {
//                e.printStackTrace();
//                flag =false;
//            } finally {
//            }
        }
        System.out.println(clientNumber + "  handle "
                + count *10 +"万 use " + (double)(System.currentTimeMillis() -start)/(double)1000 +"秒");
    }
}
