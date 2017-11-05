package edu.wzm.controller;

import edu.wzm.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gatsbynewton on 2017/11/5.
 */
@RestController
@RequestMapping("/email")
public class controller {
    @Autowired
    private EmailSenderService senderService;

    @RequestMapping(value = "/simple")
    public boolean sendSimpleEmail(){
        return senderService.sendSimpleEmail();
    }

    @RequestMapping(value = "/html")
    public boolean sendHtmlEmail(){
        return senderService.sendHtmlEmail();
    }

    @RequestMapping(value = "/attachment")
    public boolean sendAttachment(){
        return senderService.sendAttachment();
    }
}
