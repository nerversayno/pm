package cn.lcf.jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * User: lcf
 * Date: 2014/8/21
 * Time: 14:04
 * Description:
 */
public class TicketConnector {

    private String url;

    public TicketConnector(String url) {
        this.url = url;
    }

    public  StringBuffer connect() throws IOException {
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lines;
        StringBuffer stringBuffer = new StringBuffer();
        while ((lines = reader.readLine()) != null) {
//            System.out.println(lines);
            stringBuffer.append(lines);

        }
        reader.close();
        connection.disconnect();
        return stringBuffer;
    }

}
