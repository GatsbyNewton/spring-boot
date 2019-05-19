package edu.wzm.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = SlaverDataSourceConfiguration.PACKAGE, sqlSessionFactoryRef = "slaverSqlSessionFactory")
public class SlaverDataSourceConfiguration {
    public static final String PACKAGE = "edu.wzm.dao.slaver";

    public static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Value("${slaver.datasource.url}")
    private String url;

    @Value("${slaver.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${slaver.datasource.username}")
    private String username;

    @Value("${slaver.datasource.password}")
    private String password;

    @Bean(name = "slaverDataSource")
    public DataSource slaverDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean(name = "slaverTransactionManager")
    public DataSourceTransactionManager slaverTransactionManager(){
        return new DataSourceTransactionManager(slaverDataSource());
    }

    @Bean(name = "slaverSqlSessionFactory")
    public SqlSessionFactory slaverSqlSessionFactory(@Qualifier("slaverDataSource") DataSource masterDataSource) throws Exception{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SlaverDataSourceConfiguration.MAPPER_LOCATION));

        return sessionFactory.getObject();
    }
}
