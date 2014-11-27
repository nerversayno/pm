package cn.lcf.cartman.annotation;

import cn.cartman.annotation.Mapping;
import cn.cartman.annotation.Method;
import cn.cartman.annotation.Param;
import cn.cartman.annotation.Service;

/**
 * Created by lcf on 2014/11/19.
 */
@Service(value = "/", method = {Method.POST,Method.GET,Method.PUT},description = "test controller")
public class TestContoller {

    @Mapping(value = "/test", method = {Method.GET, Method.POST}, descriptoin = "test method")
    public void test(
            @Param(value = "id",required = false,descriptoin = "params id ")
            String id) {
        System.out.println("test method id = "+ id);
    }
}
