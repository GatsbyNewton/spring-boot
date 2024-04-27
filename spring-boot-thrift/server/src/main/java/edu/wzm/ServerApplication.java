package edu.wzm;

import edu.wzm.config.RpcThriftServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author wangzhiming07
 * @version 1.0
 * @date 2024/4/23
 * @description
 */
@SpringBootApplication
public class ServerApplication {

    private static RpcThriftServer rpcThriftServer;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServerApplication.class, args);
        try {
            // 利用反射获取对象
            rpcThriftServer = context.getBean(RpcThriftServer.class);
            // 服务启动
            rpcThriftServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
