package edu.wzm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcThriftClientConfig {
    @Value("${thrift.host}")
    private String host;

    @Value("${thrift.port}")
    private int port;


    @Bean(initMethod = "init")
    public RpcThriftClient rpcThriftClient() {
        RpcThriftClient rpcThriftClient = new RpcThriftClient();
        rpcThriftClient.setHost(host);
        rpcThriftClient.setPort(port);
        return rpcThriftClient;
    }
}