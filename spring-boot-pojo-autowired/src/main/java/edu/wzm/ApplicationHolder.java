package edu.wzm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 在没有注入Spring容器的类中，注入已@Component的Java类。
 */
@Component
public class ApplicationHolder implements ApplicationContextAware {
    public final static Logger LOGGER = LoggerFactory.getLogger(ApplicationHolder.class);
    
    private static ApplicationContext applicationContext = null;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationHolder.applicationContext  = applicationContext;
        LOGGER.info("======== set applicaton context = " + ApplicationHolder.applicationContext + "========");
    }

    /**
     * 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}