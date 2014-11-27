package cn.lcf.jedis;

import java.io.*;

/**
 * User: lcf
 * Date: 2014/8/25
 * Time: 14:51
 * Description:
 */
public class TicketWriterF {
    BufferedWriter bufferedWriter = null;

    public TicketWriterF(int clintNumber) {
        String outfile = "C:\\Users\\Administrator\\Desktop\\redis\\ticket\\client"+ clintNumber +".txt";
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outfile), true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
