# Spring Boot 定时任务
Spring Boot 自带的 ***Scheduled*** 可以实现定时任务，通过 ***@EnableScheduling*** 注解开启定时任务功能。Spring Boot 中，有两种定时任务：
1. 单线程，Spring Boot 定时任务的默认执行方式。
2. 多线程，如果要想实现多线程定时任务，需要用户实现 ***org.springframework.scheduling.annotation.SchedulingConfigurer*** 接口。