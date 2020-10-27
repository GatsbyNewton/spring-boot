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
@MapperScan(basePackages = ReplicaDataSourceConfiguration.PACKAGE, sqlSessionFactoryRef = "replicaSqlSessionFactory")
public class ReplicaDataSourceConfiguration {
    public static final String PACKAGE = "edu.wzm.dao.replica";

    public static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Value("${replica.datasource.url}")
    private String url;

    @Value("${replica.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${replica.datasource.username}")
    private String username;

    @Value("${replica.datasource.password}")
    private String password;

    @Bean(name = "replicaDataSource")
    public DataSource replicaDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean(name = "replicaTransactionManager")
    public DataSourceTransactionManager slaverTransactionManager(){
        return new DataSourceTransactionManager(replicaDataSource());
    }

    @Bean(name = "replicaSqlSessionFactory")
    public SqlSessionFactory slaverSqlSessionFactory(@Qualifier("replicaDataSource") DataSource masterDataSource) throws Exception{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ReplicaDataSourceConfiguration.MAPPER_LOCATION));

        return sessionFactory.getObject();
    }
}
