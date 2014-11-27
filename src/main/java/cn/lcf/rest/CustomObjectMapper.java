package cn.lcf.rest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.text.SimpleDateFormat;

/**
 * User: lcf
 * Date: 14-4-1
 * Time: ����3:16
 * Description:
 */
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        this.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,false);
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
