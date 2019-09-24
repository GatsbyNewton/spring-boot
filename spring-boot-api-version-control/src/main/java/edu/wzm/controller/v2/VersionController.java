package edu.wzm.controller.v2;

import edu.wzm.conf.ApiVersion;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{version}")
@ApiVersion(2)
@Component("V2Controller")  // 解决Bean同名冲突
public class VersionController {

    @RequestMapping("/hello")
    public String test() {
        return "Hello Version 2.0";
    }
}
