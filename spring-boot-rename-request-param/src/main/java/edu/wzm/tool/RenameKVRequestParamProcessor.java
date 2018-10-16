package edu.wzm.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jimmy Wong
 */
public class RenameKVRequestParamProcessor extends ServletModelAttributeMethodProcessor {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private final Map<Class<?>, Map<String, String>> replaceMap = new ConcurrentHashMap<>();

    public RenameKVRequestParamProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        Object target = binder.getTarget();
        Class<?> targetClass = target.getClass();
        if (!replaceMap.containsKey(targetClass)){
            Map<String, String> mapping = analyzeClass(targetClass);
            replaceMap.put(targetClass, mapping);
        }

        Map<String, String> mapping = replaceMap.get(targetClass);
        ParamAliasDataBinder paramAliasDataBinder = new ParamAliasDataBinder(target, binder.getObjectName(), mapping);
        requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(paramAliasDataBinder, request);
        super.bindRequestParameters(paramAliasDataBinder, request);
    }

    private static Map<String, String> analyzeClass(Class<?> targetClass){
        Field[] fields = targetClass.getDeclaredFields();
        Map<String, String> renameMap = new HashMap<>();

        Arrays.stream(fields)
                .forEach(field -> {
                    ParamAlias paramNameAnnotation = field.getAnnotation(ParamAlias.class);
                    if (paramNameAnnotation != null && !paramNameAnnotation.name().isEmpty()){
                        renameMap.put(paramNameAnnotation.name(), field.getName());
                    }
                });

        if (renameMap.isEmpty()){
            return Collections.emptyMap();
        }

        return renameMap;
    }
}
