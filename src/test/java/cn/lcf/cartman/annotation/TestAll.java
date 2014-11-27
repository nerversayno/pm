package cn.lcf.cartman.annotation;

import cn.lcf.service.CartmanService;
import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.PrioritizedParameterNameDiscoverer;

import java.util.Arrays;

/**
 * Created by lcf on 2014/11/19.
 */
public class TestAll {
    @Test
    public void testDefaultValue() {
        System.out.println("\n\t\t\n\t\t\n\uE000\uE001\uE002\n\t\t\t\t\n");
    }

    @Test
    public void testPackageScan() {
        Package aPackage = this.getClass().getPackage();
    }

    @Test
    public void testScanAnnotations(){
    }

    @Test
    public void testScanParameters() throws NoSuchMethodException {
        java.lang.reflect.Method method = CartmanService.class.getMethod("getApiDoc", String.class);
        Arrays.asList(CartmanService.class.getDeclaredMethods()).forEach(m->{
        });
        int length = method.getParameters().length;
        for(int i=0;i<length;i++){
            MethodParameter methodParameter = new MethodParameter(method,i);
            PrioritizedParameterNameDiscoverer discoverer = new PrioritizedParameterNameDiscoverer();
            discoverer.addDiscoverer(new LocalVariableTableParameterNameDiscoverer());
            methodParameter.initParameterNameDiscovery(discoverer);
            System.out.println(i + " " + methodParameter.getParameterName());
        }

    }

}
