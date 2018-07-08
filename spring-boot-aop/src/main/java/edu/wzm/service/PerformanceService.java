package edu.wzm.service;

import org.springframework.stereotype.Service;

@Service
public class PerformanceService {

    public String perform(String name){
        return "Hello, " + name;
    }

    public long monitor(String name, long count){
        for (int i = 0; i < count; i++){
            String result = play(name);
            System.out.println("monitor: " + result);
        }
        return 0;
    }

    public String play(String name){
        return "Hello, " + name;
    }
}
