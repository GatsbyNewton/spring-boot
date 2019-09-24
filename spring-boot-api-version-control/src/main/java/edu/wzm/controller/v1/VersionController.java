package edu.wzm.controller.v1;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{version}")
@Component("V1Controller")  // 解决Bean同名冲突
public class VersionController {

    @RequestMapping("/hello")
    public String test() {
        return "Hello Version 1.0";
    }
}
