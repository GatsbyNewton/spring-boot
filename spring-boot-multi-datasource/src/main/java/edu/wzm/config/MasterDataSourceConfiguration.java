package edu.wzm.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = MasterDataSourceConfiguration.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfiguration {

    public static final String PACKAGE = "edu.wzm.dao.master";

    public static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${master.datasource.username}")
    private String username;

    @Value("${master.datasource.password}")
    private String password;

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(){
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfiguration.MAPPER_LOCATION));

        return sessionFactory.getObject();
    }
}
