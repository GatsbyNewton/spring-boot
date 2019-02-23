package edu.wzm.thread;

import org.springframework.stereotype.Component;

@Component
public class DaemonThread extends Thread {
    @Override
    public void run() {
        System.out.println("DaemonThread已启动。");
    }
}
