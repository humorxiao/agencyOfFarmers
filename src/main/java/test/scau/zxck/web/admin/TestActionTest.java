package test.scau.zxck.web.admin;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import scau.zxck.utils.CodeUtil;
import scau.zxck.web.admin.TestAction;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * TestAction Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 5, 2018</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml", "classpath:config/spring/web/spring-mvc.xml"})
public class TestActionTest {
//    @Autowired
//    private JavaMailSender mailSender;
    @Autowired
    private MockHttpServletRequest request;
    @Autowired
    private MockHttpSession session;
    @Autowired
    private TestAction testAction;
    private MockMvc mockMvc;
    @Before
    public void before() throws Exception {
        mockMvc=standaloneSetup(testAction).build();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testS() throws Exception {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("1769969562@qq.com");
        mailSender.setPassword("mwnfcuwdztvveihg");
        //加认证机制
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.timeout", 5000);
        mailSender.setJavaMailProperties(javaMailProperties);
        //创建邮件内容
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("1769969562@qq.com");
        message.setTo("2998043186@qq.com");
        message.setSubject("hahahaha");
        message.setText("abcdefghijklmnopqrstuvwxyz");
        //发送邮件
        mailSender.send(message);
        System.out.println("邮件发送成功");
    }
    @Test
    public void testTest2() throws Exception {
        String[] strings={"1364736594@qq.com","582780857@qq.com","1572868725@qq.com"};
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("1769969562@qq.com");
        mailSender.setPassword("mwnfcuwdztvveihg");
        //加认证机制
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.timeout", 5000);
        mailSender.setJavaMailProperties(javaMailProperties);
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom("1769969562@qq.com");
        for(int i=0;i<strings.length;i++) {
            helper.setTo(strings[i]);
            helper.setSubject("guanren15.com");
            helper.setText("前方高能");
            FileSystemResource couponImage = new FileSystemResource("renguan.jpg");
            helper.addAttachment("guanren15.jpg", couponImage);
            mailSender.send(message);
            System.out.println("邮件发送成功！");
        }
    }
    @Test
    public void test1()throws Exception{
        File file=new File("src/main/webapp/WEB-INF/pages/index.txt");
        if(file.exists()){
            System.out.println("exist");
        }
        else {
            PrintWriter out = new PrintWriter(file);
            out.print("abcd");
        }
    }
}
