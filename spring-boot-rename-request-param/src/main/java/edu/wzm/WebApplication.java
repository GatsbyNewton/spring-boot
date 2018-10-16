package edu.wzm;

import edu.wzm.tool.RenameKVRequestParamProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author Jimmy Wong
 */
@Configuration
public class WebApplication extends WebMvcConfigurerAdapter {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(renameProcessor());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    protected RenameKVRequestParamProcessor renameProcessor(){
        return new RenameKVRequestParamProcessor(true);
    }
}
