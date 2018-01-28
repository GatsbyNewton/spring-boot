# Spring Boot 异步调用
Spring Boot 异步调用需要在被调用的方法上添加 ***@Async*** 注解，并且启用 ***@EnableAsync*** 即可。同时，Spring
Boot 允许用户自定义 Executor，并在方法上通过 ***@Async("xxx")*** 指定特定的 Executor。