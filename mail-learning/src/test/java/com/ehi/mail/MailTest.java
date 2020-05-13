package com.ehi.mail;

import com.ehi.MailLearningApplication;
import com.ehi.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/13 10:57
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 发送带简单的邮件
     */
    @Test
    public void sendSimpleMail() {
        //构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件主题
        message.setSubject("这是一封测试邮件");
        //设置邮件发送者
        message.setFrom("1224900839@qq.com");
        //设置邮件接收者，可以有多个接收者
        message.setTo("318448121@qq.com");
        //设置邮件抄送人，可以有多个抄送人
        message.setCc("318448121@qq.com");
        //设置隐秘抄送人，可以有多个
        message.setBcc("318448121@qq.com");
        //设置邮件发送日期
        message.setSentDate(new Date());
        //设置邮件的正文
        message.setText("这是测试邮件的正文");
        //发送邮件
        javaMailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     * @throws MessagingException
     */
    @Test
    public void sendAttachFileMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("1224900839@qq.com");
        helper.setTo("318448121@qq.com");
        helper.setCc("318448121@qq.com");
        helper.setBcc("318448121@qq.com");
        helper.setSentDate(new Date());
        helper.setText("这是测试邮件的正文");
        helper.addAttachment("300000839764127060614318218_950.jpg",new File("C:\\Users\\12249\\Pictures\\300000839764127060614318218_950.jpg"));
        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送带图片资源的邮件
     * @throws MessagingException
     */
    @Test
    public void sendImgResMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("1224900839@qq.com");
        helper.setTo("318448121@qq.com");
        helper.setCc("318448121@qq.com");
        helper.setBcc("318448121@qq.com");
        helper.setSentDate(new Date());
        helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>",true);
        helper.addInline("p01",new FileSystemResource(new File("C:\\Users\\12249\\Pictures\\300000839764127060614318218_950.jpg")));
        helper.addInline("p02",new FileSystemResource(new File("C:\\Users\\12249\\Pictures\\300000839764127060614318218_955.jpg")));
        javaMailSender.send(mimeMessage);
    }

    /**
     * 使用 Freemarker 作邮件模板
     * @throws MessagingException
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void sendFreemarkerMail() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("1224900839@qq.com");
        helper.setTo("318448121@qq.com");
        helper.setCc("318448121@qq.com");
        helper.setBcc("318448121@qq.com");
        helper.setSentDate(new Date());
        //构建 Freemarker 的基本配置
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        // 配置模板位置
        ClassLoader loader = MailLearningApplication.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader, "templates");
        //加载模板
        Template template = configuration.getTemplate("mail.ftl");
        User user = new User();
        user.setUsername("javaboy");
        user.setNum(1);
        user.setSalary((double) 99999);
        StringWriter out = new StringWriter();
        //模板渲染，渲染的结果将被保存到 out 中 ，将out 中的 html 字符串发送即可
        template.process(user, out);
        helper.setText(out.toString(),true);
        javaMailSender.send(mimeMessage);
    }

    @Autowired
    TemplateEngine templateEngine;

    /**
     * 使用 Thymeleaf 作邮件模板
     * @throws MessagingException
     */
    @Test
    public void sendThymeleafMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("1224900839@qq.com");
        helper.setTo("318448121@qq.com");
        helper.setCc("318448121@qq.com");
        helper.setBcc("318448121@qq.com");
        helper.setSentDate(new Date());
        Context context = new Context();
        context.setVariable("username", "javaboy");
        context.setVariable("num","000001");
        context.setVariable("salary", "99999");
        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        javaMailSender.send(mimeMessage);
    }

}