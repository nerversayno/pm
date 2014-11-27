package cn.lcf;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * User: lcf
 * Date: 2014/9/3
 * Time: 11:25
 * Description:
 */
public class NormalTest {

    @Test
    public void bitCount() {
        List list;
        Map map;
        System.out.println(Integer.bitCount(100));
        ;
    }

    @Test
    public void bigDecimal() {
        BigDecimal bigDecimal = new BigDecimal(1);
        BigDecimal bigDecimal2 = bigDecimal.add(new BigDecimal(2));

        System.out.println(1);
    }
}


