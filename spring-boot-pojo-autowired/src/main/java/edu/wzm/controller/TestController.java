package edu.wzm.controller;

import edu.wzm.manager.NonAutowiredObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping("/test")
    public Object test(@RequestParam("name") String name){
        NonAutowiredObject manager = new NonAutowiredObject(name);
        manager.print();
        return "DONE";
    }
}
