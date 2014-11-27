package cn.lcf.cartman.annotation;

import cn.cartman.annotation.Method;
import cn.lcf.doc.parse.Scanner;
import cn.lcf.service.CartmanService;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.junit.Test;

import java.lang.reflect.Parameter;
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
        Class clazz = CartmanService.class;
        Arrays.asList(clazz.getDeclaredMethods()).forEach(m -> {
            try {
                ClassPool pool = ClassPool.getDefault();
                CtClass cc = pool.get(clazz.getName());
                CtMethod cm = cc.getDeclaredMethod(m.getName());
                MethodInfo methodInfo = cm.getMethodInfo();
                CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
                LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
//                System.out.println("\t" + m.getName() + "\t" + attr.tableLength());
                String[] paramNames = new String[cm.getParameterTypes().length];
                CtClass[] ctClass = cm.getParameterTypes();
                int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
                for (int i = 0; i < paramNames.length; i++)
                    paramNames[i] = attr.variableName(i + pos);
                for(int i=0;i <paramNames.length;i++){
                    System.out.print("\t" +paramNames[i]);
                }
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testScanParameters() throws NoSuchMethodException {
        java.lang.reflect.Method method = CartmanService.class.getMethod("getApiDoc", String.class);
        Arrays.asList(CartmanService.class.getDeclaredMethods()).forEach(m->{
            Arrays.asList(m.getParameters()).forEach(o->{
                String str = o.getParameterizedType().getTypeName();
                System.out.println(o.getName() +"\t" + o.getType().getSimpleName());
            });
        });

    }

}
