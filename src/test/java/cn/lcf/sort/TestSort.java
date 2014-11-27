package cn.lcf.sort;

import cn.lcf.entity.Ticket;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Umysser: lcf
 * Date: 2014/9/15
 * Time: 11:45
 * Description:
 */
public class TestSort {
    @Test
    public void sort() {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket();
            ticket.setClient(i % 2);
            ticket.setGuid("" + i % 3);
            ticket.setSold(i % 4 == 0 ? true : false);
            ticketList.add(ticket);
        }
        Collections.sort(ticketList,new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) {
                if(o1.getSold()^o2.getSold()){
                    return o1.getSold()?1:-1;
                }  else {
                    if(o1.getClient() == o2.getClient())  {
                         return  o1.getGuid().compareTo(o2.getGuid());
                    }   else {
                        return o1.getClient() > o2.getClient() ? 1:-1;
                    }
                }
            }
        });
        for(Ticket ticket : ticketList){
            System.out.println(ticket.toRedisString());
        }
    }
}
