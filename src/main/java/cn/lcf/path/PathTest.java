package cn.lcf.path;

import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by lcf on 2014/11/26.
 */
public class PathTest {
    @Test
    public void getPath() throws ClassNotFoundException {
        String path = PathTest.class.getClassLoader().getResource("").getPath();
        System.out.println(path);
        scanPath(path + "cn/lcf/akka");
    }

    private void scanPath(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            Arrays.asList(file.list()).forEach(p -> scanPath(path + "/"+ p));
        } else {
            if(file.exists()){
                System.out.println(file.getAbsolutePath());
            }
        }
    }

}
