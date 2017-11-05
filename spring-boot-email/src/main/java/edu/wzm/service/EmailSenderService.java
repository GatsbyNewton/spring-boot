package edu.wzm.service;

import edu.wzm.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by gatsbynewton on 2017/11/5.
 */
@Service
public class EmailSenderService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendSimpleEmail(){
        String subject = "文本邮件";
        String content = "测试文件邮件的发送。";
        String[] to = {"1554093309@qq.com"};
        String[] cc = {"775023626@qq.com"};
        return EmailSender.send(mailSender, from, to, cc, subject, content);
    }

    public boolean sendHtmlEmail(){
        String subject = "HTML邮件";
        String[] to = {"1554093309@qq.com"};
        String[] cc = {"775023626@qq.com"};
        StringBuffer content = new StringBuffer();
        content.append("<h1>大标题-h1</h1>")
                .append("<p style='color:#F00'>红色字</p>")
                .append("<p style='text-align:right'>右对齐</p>");
        boolean isHtml = true;

        return EmailSender.send(mailSender, from, to, cc, subject, content.toString(), isHtml);
    }

    public boolean sendAttachment(){
        String subject = "HTML邮件";
        String[] to = {"1554093309@qq.com"};
        String[] cc = {"775023626@qq.com"};
        String imgPath = EmailSenderService.class.getClassLoader().getResource("files/java.jpeg").getPath();
        String filePath = EmailSenderService.class.getClassLoader().getResource("files/test.txt").getPath();
        StringBuffer content = new StringBuffer();
        content.append("<h1>大标题-h1</h1>")
                .append("<p style='color:#F00'>图片</p>")
                .append("<p style='text-align:right'>文件</p>");
        boolean isHtml = true;

        return EmailSender.send(mailSender, from, to, cc, subject, content.toString(), imgPath, filePath, isHtml);
    }
}
