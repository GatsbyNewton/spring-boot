package edu.wzm.config;

import edu.wzm.service.HelloService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

/**
 * @author wangzhiming07
 * @version 1.0
 * @date 2024/4/23
 * @description
 */
public class RpcThriftClient {
    private HelloService.Client client;

    private TCompactProtocol protocol; //压缩协议

    private String host; // 主机ip

    private int port; // 端口

    private TTransport transport; // 运输

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    /**
     * 初始化
     * @throws TTransportException
     */
    public void init() throws TTransportException {
        transport =new TFramedTransport(new TSocket(host, port));
        protocol = new TCompactProtocol(transport);
        client = new HelloService.Client(protocol);
    }

    public HelloService.Client getRPCThriftService() {
        return client;
    }

    /**
     * 打开
     * @throws TTransportException
     */
    public void open() throws TTransportException {
        transport.open();
    }

    /**
     * 关闭
     */
    public void close() {
        transport.close();
    }
}
