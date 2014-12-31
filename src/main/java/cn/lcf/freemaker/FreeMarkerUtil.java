package cn.lcf.freemaker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcf on 2014/12/23.
 */
public class FreeMarkerUtil {
    public static void main(String[] args) throws IOException, TemplateException {
        User user = new User();
        user.setUserName("测试");
        user.setUserPassword("123");

        Map<String, Object> root = new HashMap<String, Object>();
        root.put("user", user);

        analysisTemplate("user.ftl","UTF-8",root);
        FreeMarkerViewResolver freeMarkerViewResolver;
    }
    public static void analysisTemplate(String templateName, String templateEncoding, Map<?, ?> root) throws IOException, TemplateException {
        Configuration config = new Configuration();
        File file = new File("");
        config.setDirectoryForTemplateLoading(file);
        config.setObjectWrapper(new DefaultObjectWrapper());
        Template template = config.getTemplate(templateName,
                templateEncoding);
        Writer out = new OutputStreamWriter(System.out);
                 template.process(root, out);
                    out.flush();
                    out.close();

    }
}
