package cn.lcf.rest;

import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * User: lcf
 * Date: 14-3-31
 * Time: ����10:50
 * Description:
 */
public class JsonParamMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver implements WebArgumentResolver {

    @Autowired
    private CustomObjectMapper objectMapper;

    public JsonParamMethodArgumentResolver() {
        super(null);
        System.out.println(this.toString());
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(JsonParam.class)) {
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        return new JsonParamNameValueInfo(parameter.getParameterAnnotation(JsonParam.class));  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        String[] paramValues = request.getParameterValues(name);
        Class<?> paramType = parameter.getParameterType();
        if (paramValues == null) {
            return null;
        }
        try {
            if (paramValues.length == 1) {
                String value = paramValues[0];
                Type type = parameter.getGenericParameterType();
                JavaType javaType = TypeFactory.type(paramType);
                if (Collection.class.isAssignableFrom(paramType)) {
                    javaType = javaType.narrowContentsBy((Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0]);
                }
                return objectMapper.readValue(value, javaType);  //To change body of implemented methods use File | Settings | File Templates.
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read json parameter '" + name + "'", e);
        }
        throw new RuntimeException("Too many values in json parameter");
    }

    @Override
    protected void handleMissingValue(String name, MethodParameter parameter) throws ServletException {
        throw new RuntimeException("Missing json parameter '" + parameter.getParameterType().getName() + "'");
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
        if (!supportsParameter(methodParameter)) {
            return WebArgumentResolver.UNRESOLVED;
        }
        return resolveArgument(methodParameter, null, webRequest, null);  //To change body of implemented methods use File | Settings | File Templates.
    }


    private class JsonParamNameValueInfo extends NamedValueInfo {
        private JsonParamNameValueInfo() {
            super("", false, null);
        }

        private JsonParamNameValueInfo(JsonParam annotation) {
            super(annotation.value(), annotation.required(), null);
        }
    }
}
