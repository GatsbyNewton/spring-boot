package edu.wzm.controller;

import edu.wzm.aspect.AudienceArgsAspect;
import edu.wzm.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private AudienceArgsAspect audienceArgsAspect;

    @RequestMapping(value = "/perform", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String perform(@RequestParam("name") String name){
        String result = performanceService.perform(name);
        return result;
    }

    @RequestMapping(value = "/monitor", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String monitor(@RequestParam("name") String name,
                          @RequestParam("count") long count){
        for (int i = 0; i < count; i++) {
            String result = performanceService.play(name);
        }
        return "Count: " + audienceArgsAspect.getPlayCount(name);
    }
}
