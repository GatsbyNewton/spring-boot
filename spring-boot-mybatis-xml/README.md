# Spring Boot 集成 MyBatis
Spring Boot 集成 MyBatis 需要进行以下配置：
1. 在 application.properties 文件中，至少配置以下几项：数据库源、实体类所在包名和 Mapper 文件的位置。
    - spring.datasource.driver-class-name
    - spring.datasource.url
    - spring.datasource.username
    - spring.datasource.password
    - mybatis.type-aliases-package 指定实体类所在包。例如，mybatis.type-aliases-package=edu.wzm.entity
    - mybatis.mapper-locations 指定 MyBatis Mapper 文件所在位置。例如，mybatis.mapper-locations=classpath:mapper/*.xml
2. 在 Spring Boot 启动类中配置 MyBatis Mapper 文件扫描器。例如，@MapperScan("edu.wzm.dao")。