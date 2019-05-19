package edu.wzm.manager;

import org.springframework.stereotype.Component;

@Component
public class AutowiredObject {

    public void call(String msg){
        System.out.println("message: " + msg);
    }
}
