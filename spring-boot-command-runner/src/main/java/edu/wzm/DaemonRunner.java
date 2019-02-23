package edu.wzm;

import edu.wzm.thread.DaemonThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DaemonRunner implements CommandLineRunner {

    @Autowired
    private DaemonThread daemonThread;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=======DaemonRunner在SpringBoot启动之后同时启动========");
        if (!daemonThread.isAlive()){
            daemonThread.start();
        }
    }
}
