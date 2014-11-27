package cn.lcf.disruptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Objects;

/**
 * User: lcf
 * Date: 2014/9/9
 * Time: 14:32
 * Description:
 */
public class DBEvent {
    String sql;
    Object[] args;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object... args) {
        this.args = args;
    }
}
