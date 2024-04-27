package edu.wzm.service;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService.Iface {
    @Override
    public String helloString(String para) throws TException {
        return "hello " + para;
    }
}