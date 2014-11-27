package cn.lcf.jedis;

import java.io.*;
import java.nio.Buffer;

/**
 * User: lcf
 * Date: 2014/8/27
 * Time: 15:46
 * Description:
 */
public class TicketReaderF {

    BufferedReader bufferedReader = null;

    public TicketReaderF(int clientNumber) {
        String infile = "C:\\Users\\Administrator\\Desktop\\redis\\ticket\\client"+ clientNumber +".txt";
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(infile))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
