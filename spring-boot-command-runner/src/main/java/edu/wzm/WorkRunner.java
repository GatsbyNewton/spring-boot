package edu.wzm;

import edu.wzm.thread.WordThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WorkRunner implements CommandLineRunner {

    @Autowired
    private WordThread wordThread;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=======WordRunner在SpringBoot启动之后同时启动========");
        if (!wordThread.isAlive()){
            wordThread.start();
        }
    }
}
