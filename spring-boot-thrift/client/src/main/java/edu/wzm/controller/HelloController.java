package edu.wzm.controller;

import edu.wzm.config.RpcThriftClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private RpcThriftClient thriftClient;

    @RequestMapping("/test")
    public String test(@RequestParam("param") String param){
        try {
            thriftClient.open();
            return thriftClient.getRPCThriftService().helloString(param) + "\t" ;
//                    thriftClient.getRPCThriftService2().hello2String(param);
        } catch (Exception e) {
            log.error("RPC调用失败", e);
            return null;
        }
        finally {
            thriftClient.close();
        }
    }
}