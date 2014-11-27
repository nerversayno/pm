package cn.lcf.jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User: lcf
 * Date: 2014/8/27
 * Time: 15:50
 * Description:
 */
public class TicketClientReader implements Runnable {
    private int clientNumber;

    private BufferedReader bufferedReader;

    public TicketClientReader(int clientNumber) {
        this.clientNumber = clientNumber;
        this.bufferedReader = new TicketReaderF(clientNumber).bufferedReader;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        String line;
        Connection connection = null;
        int count = 10000;
        try {
            int i = 0;
            connection = TicketUtil.getConnection();
            String sql = "insert into clientRecord(client,guid) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            while ((line = bufferedReader.readLine()) != null) {
                line =line.replaceAll("\"","");
                preparedStatement.setInt(1, clientNumber);
                preparedStatement.setString(2, line);
                preparedStatement.addBatch();
                i++;
                if (i % count == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                    if (i % (10 * count) == 0) {
                        System.out.println(clientNumber + "  read " + i +
                                "   records  use " + (double) (System.currentTimeMillis() - start) / (double) 1000 + "秒");
                    }
                }
            }
            preparedStatement.executeBatch();
            System.out.println(clientNumber + "  finish record read " + i +
                    "   records  use " + (double) (System.currentTimeMillis() - start) / (double) 1000 + "秒");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                TicketUtil.closeConnection(connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
