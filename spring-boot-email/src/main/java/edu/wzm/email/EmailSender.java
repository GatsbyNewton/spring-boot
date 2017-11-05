package edu.wzm.email;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by gatsbynewton on 2017/11/5.
 */
public class EmailSender {

    /**
     * 发送普通文本邮件
     * @param mailSender
     * @param from 发件人
     * @param to 收件人
     * @param cc 抄送人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return
     */
    public static boolean send(JavaMailSender mailSender, String from, String[] to, String[] cc, String subject, String content){
        if (to == null){
            System.err.println("收件人地址为空！");
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try {
            mailSender.send(simpleMailMessage);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 发送HTML格式的邮件
     * @param mailSender
     * @param from 发件人
     * @param to 收件人
     * @param cc 抄送人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param isHtml 是否为HTML格式
     * @return
     */
    public static boolean send(JavaMailSender mailSender, String from, String[] to, String[] cc, String subject, String content, boolean isHtml){
        if (to == null){
            System.err.println("收件人地址为空！");
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            String encoding = "UTF-8";
            boolean multipart = true;
            initMimeMessageHelper(message, from, to, cc, subject, content, isHtml, multipart, encoding);
            mailSender.send(message);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 发送带有附件的邮件
     * @param mailSender
     * @param from 发件人
     * @param to 收件人
     * @param cc 抄送人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param imgPath 图片附件地址
     * @param filePath 文件附件地址
     * @param isHtml 是否为HTML格式
     * @return
     */
    public static boolean send(JavaMailSender mailSender, String from, String[] to, String[] cc, String subject, String content, String imgPath, String filePath, boolean isHtml){
        if (to == null){
            System.err.println("收件人地址为空！");
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            String encoding = "UTF-8";
            boolean multipart = true;
            String fileName = "文件.txt";
            String imgName = "图片.jpg";
            MimeMessageHelper messageHelper = initMimeMessageHelper(message, from, to, cc, subject, content, isHtml, multipart, encoding);
            messageHelper.addAttachment(imgName, new File(imgPath));
            messageHelper.addAttachment(fileName, new File(filePath));

            mailSender.send(message);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private static MimeMessageHelper initMimeMessageHelper(MimeMessage mailMessage, String from, String[] to, String[] cc, String subject,
                                                           String content, boolean isHTML, boolean multipart, String encoding) throws MessagingException {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, multipart, encoding);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setCc(cc);
        messageHelper.setSubject(subject);
        messageHelper.setText(content, isHTML);

        return messageHelper;
    }
}
