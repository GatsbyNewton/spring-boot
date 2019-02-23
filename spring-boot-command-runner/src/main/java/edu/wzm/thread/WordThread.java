package edu.wzm.thread;

import org.springframework.stereotype.Component;

@Component
public class WordThread extends Thread {

    @Override
    public void run() {
        System.out.println("WorkThread已启动");
    }
}
