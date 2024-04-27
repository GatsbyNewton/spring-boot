package edu.wzm.config;

import edu.wzm.service.Hello2Service;
import edu.wzm.service.Hello2ServiceImpl;
import edu.wzm.service.HelloService;
import edu.wzm.service.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.layered.TFastFramedTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RpcThriftServer {
    @Value("${thrift.port}")
    private int port;
    @Value("${thrift.minWorkerThreads}")
    private int minThreads;
    @Value("${thrift.maxWorkerThreads}")
    private int maxThreads;

    private TCompactProtocol.Factory protocolFactory;

    private TFastFramedTransport.Factory transportFactory;

    @Autowired
    private HelloServiceImpl helloService;

    @Autowired
    private Hello2ServiceImpl hello2Service;

    /**
     * 定义处理器工厂
     */
    public void init() {
        // 压缩协议
        protocolFactory = new TCompactProtocol.Factory();
        // 传输
        transportFactory = new TFastFramedTransport.Factory();
    }

    /**
     * 初始化方法
     */
    public void start() {
        HelloService.Processor<HelloService.Iface> processor = new HelloService.Processor<>(helloService);

        init();

        try {
            // 非阻塞socket
            TNonblockingServerSocket transport = new TNonblockingServerSocket(port);
            // 建立高可用server
            THsHaServer.Args tArgs = new THsHaServer.Args(transport).minWorkerThreads(minThreads).maxWorkerThreads(maxThreads);
            // 处理器
            tArgs.processor(processor);
            // 协议
            tArgs.protocolFactory(protocolFactory);
            // 传输层
            tArgs.transportFactory(transportFactory);
            // TNonblockingServer 对半同步/半异步服务器的扩展
            TServer server = new THsHaServer(tArgs);
            log.info("thrift服务启动成功, 端口={}", port);
            // 启动
            server.serve();

        } catch (Exception e) {
            log.error("thrift服务启动失败", e);
        }
    }
}