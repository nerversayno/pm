package cn.lcf.doc.parse;

import cn.cartman.annotation.Mapping;
import cn.cartman.annotation.Method;
import cn.cartman.annotation.Param;
import cn.cartman.annotation.Service;
import cn.lcf.doc.view.*;
import javassist.ClassPool;
import javassist.NotFoundException;
import sun.management.MethodInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lcf on 2014/11/26.
 */
public class Scanner {
    public static String packageName = "cn.lcf.service";

    public static List<String> scan(String packageName,String basePath) {
        final String fPath;
        packageName = packageName.replace(".", File.separator);
        if(null == basePath || "".equals(basePath.trim())){
            fPath = Scanner.class.getClassLoader().getResource("").getPath();
        }else{
            fPath = basePath;
        }
        String path = fPath + packageName;
        File file = new File(path);
        final List<String> list = new ArrayList<String>();
        scanFile(file).forEach(str -> {
            list.add(str.substring(fPath.length() - 1, (str.length() - ".class".length())).replace(File.separator, "."));
        });
//        System.out.println(list);
        return list;
    }

    public static List<String> scanFile(File file) {
        List<String> list = new ArrayList<String>();
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                list.addAll(scanFile(f));
            }
        } else {
            if (file.getAbsolutePath().endsWith(".class"))
                list.add(file.getAbsolutePath());
        }
        return list;
    }

    public static List<Operation> generateOperations(java.lang.reflect.Method method, Service service) {
        List<Operation> operations = new ArrayList<>();
        Arrays.asList(service.method()).forEach(m -> {
            Operation operation = new Operation();
            operation.setMethod(m.name());
            operation.setSummary(method.getName());
            operation.setParameters(generateDocParams(method));
            operations.add(operation);
        });
        return operations;
    }
    public  static javassist.bytecode.MethodInfo getMethodInfo(java.lang.reflect.Method method){
        try {
            javassist.bytecode.MethodInfo methodInfo = ClassPool.getDefault().get(method.getDeclaringClass().getName()).getDeclaredMethod(method.getName()).getMethodInfo();

            return methodInfo;
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Operation> generateOperations(java.lang.reflect.Method method, Mapping mapping) {
        List<Operation> operations = new ArrayList<>();
        Arrays.asList(mapping.method()).forEach(m -> {
            Operation operation = new Operation();
            operation.setMethod(m.name());
            operation.setSummary(mapping.descriptoin());
            operation.setParameters(generateDocParams(method));
            operations.add(operation);
        });
        return operations;
    }

    private static List<DocParam> generateDocParams(java.lang.reflect.Method method) {
        List<DocParam> docParams = new ArrayList<DocParam>();
//        Arrays.asList(method.getParameterTypes()).forEach(clazz -> {
//            System.out.println(clazz);
//            System.out.println(clazz.getSimpleName());
//        });
        javassist.bytecode.MethodInfo methodInfo = getMethodInfo(method);
        Arrays.asList(method.getParameters()).forEach(parameter -> {
            DocParam docParam = new DocParam();
            if (parameter.isAnnotationPresent(Param.class)) {
                Param param = parameter.getAnnotation(Param.class);
                docParam.setName(param.value());
                docParam.setDescription(param.descriptoin());
                docParam.setRequired(param.required());
                docParam.setType(parameter.getType().getSimpleName());
                docParams.add(docParam);
            } else {
                docParam.setName(parameter.getName());
                docParam.setRequired(false);
                docParam.setType(parameter.getType().getSimpleName());
                docParams.add(docParam);
            }
            System.out.println(parameter.getModifiers());
            System.out.println(parameter.getName());
            System.out.println(parameter.getType().getSimpleName());
            System.out.println(parameter.getParameterizedType().getTypeName());
        });
        return docParams;
    }

    private static List<DocParam> generateDocParamsByAsist(java.lang.reflect.Method method) {
        List<DocParam> docParams = new ArrayList<DocParam>();

        return docParams;
    }

    public static DocApi generateDocApi(String packageName,String basePath) {
        DocApi docApi = new DocApi();
        List services = docApi.getApis();
        scan(packageName,basePath).forEach(str -> {
            try {
                Class clazz = Class.forName(str);
                if (clazz.isAnnotationPresent(Service.class)) {
                    DocService docService = new DocService();
                    Service service = (Service) clazz.getAnnotation(Service.class);
                    if ("".equals(service.value())) {
                        String name = clazz.getSimpleName();
                        docService.setPath("/" + name.substring(0, 1).toLowerCase() + name.substring(1));
                    } else {
                        docService.setPath(service.value());
                    }
                    docService.setDescription(service.description());
                    services.add(docService);
                    Arrays.asList(clazz.getDeclaredMethods()).forEach(method -> {
                        if (method.getModifiers() == 1) {
                            DocMapping docMapping = new DocMapping();
                            if (method.isAnnotationPresent(Mapping.class)) {
                                Mapping mapping = method.getAnnotation(Mapping.class);
                                docMapping.setDescription(mapping.descriptoin());
                                if (mapping.value().toString().startsWith("/")) {
                                    docMapping.setPath(mapping.value());
                                } else {
                                    docMapping.setPath(docService.getPath() + "/" + mapping.value());
                                }
                                docMapping.setOperationses(generateOperations(method, mapping));
                            } else {
                                docMapping.setDescription(method.getName());
                                docMapping.setPath(docService.getPath() + "/" + method.getName());
                                docMapping.setOperationses(generateOperations(method, service));
                            }
                            docService.getDocMappings().add(docMapping);
                        }
                    });
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return docApi;
    }

    public static void main(String[] args) {
        DocApi docApi = generateDocApi("cn.lcf.service","");
        System.out.println(docApi);
    }
}
