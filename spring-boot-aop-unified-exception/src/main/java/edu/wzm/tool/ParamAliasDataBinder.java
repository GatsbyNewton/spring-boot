package edu.wzm.tool;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * @author Jimmy Wong
 */
public class ParamAliasDataBinder extends ExtendedServletRequestDataBinder {

    private final Map<String, String> renameMapping;

    public ParamAliasDataBinder(Object target, String objectName, Map<String, String> renameMapping) {
        super(target, objectName);
        this.renameMapping = renameMapping;
    }

    @Override
    protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
        super.addBindValues(mpvs, request);

        renameMapping.forEach((k, v) -> {
            if (mpvs.contains(k)){
                mpvs.add(v, mpvs.getPropertyValue(k).getValue());
            }
        });
    }
}
