package edu.wzm.manager;

import edu.wzm.ApplicationHolder;

public class NonAutowiredObject {

    private String name;

    private AutowiredObject testManager = ApplicationHolder.getBean(AutowiredObject.class);

    public NonAutowiredObject(String name) {
        this.name = name;
    }

    public void print(){
        testManager.call(name + " TEST.");
    }
}
