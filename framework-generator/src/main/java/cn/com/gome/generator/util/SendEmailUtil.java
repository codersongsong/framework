package cn.com.gome.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class SendEmailUtil {
    private static Logger logger = LoggerFactory.getLogger("SendEmailUtil");

    /**
     * 发送普通文字邮件
     *
     * @param sendTo  接收人，多个接收人用','隔开
     * @param title   邮件标题
     * @param content 内容
     * @return void 返回类型
     * @throws
     * @Title: sendSimpleEmail
     */
    public static void sendSimpleEmail(String host, String userName, String password, String sendFrom, String sendTo, String title, String content) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(userName);
        mailSender.setPassword(password);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");

        //通过文件获取信息    
        mailSender.setJavaMailProperties(props);

        SimpleMailMessage smm = new SimpleMailMessage();
        // 设定邮件参数
        smm.setFrom(sendFrom);
        smm.setTo(sendTo.split(","));
        smm.setSubject(title);
        smm.setText(content);
        // 发送邮件
        mailSender.send(smm);

        logger.info("send success !!!");

    }


    public static void main(String[] args) {
        SendEmailUtil.sendSimpleEmail("mail.gomeplus.com", "DS\\liuxianzhao", "Lxz.4263156", "liuxianzhao@gomeplus.com", "liuxianzhao@gomeholdings.com,liuxianzhao@gomeplus.com", "标题", "内容");
    }

}
