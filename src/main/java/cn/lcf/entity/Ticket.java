package cn.lcf.entity;

import com.alibaba.druid.support.json.JSONUtils;

import java.io.Serializable;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 9:01
 * Description:
 */
public class Ticket implements Serializable {
    private String guid;
    private Boolean sold;
    private int client;

    public Ticket() {
    }

    public Ticket(String guid, Boolean sold, int client) {
        this.guid = guid;
        this.sold = sold;
        this.client = client;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "guid='" + guid + '\'' +
                ", sold=" + sold +
                ", client=" + client +
                '}';
    }

    public String toRedisString(){
        return  guid + ","+sold +","+client;
    }
}
