package cn.lcf.jedis;

import cn.lcf.dao.ITicket;

/**
 * User: lcf
 * Date: 2014/8/21
 * Time: 14:00
 * Description:
 */
public class TicketAdder implements Runnable {

    private String SERVER_SELL_URL = ITicket.SERVER + ITicket.ADD;
    public static final int MIN_COUNT = 1000000;

    private int clientNumber;

    private int count;

    private long start;

    public static  boolean isAdding = false;

    public TicketAdder(int clientNumber, int count) {
        this.clientNumber = clientNumber;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("ticket adder running");
        start = System.currentTimeMillis();
        isAdding =true;
        try {
            //售票
            StringBuffer stringBuffer = new TicketConnector(SERVER_SELL_URL
                    + "?count=" + count).connect();
            //记录
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        isAdding =false;
        System.out.println(clientNumber + "  add "
                + (double) count / (double) 10000 + "万 use " + (double) (System.currentTimeMillis() - start) / (double) 1000 + "秒");
    }
}
