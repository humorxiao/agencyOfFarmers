package scau.zxck.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail2Util implements Runnable {
  public static final String host = "smtp.qq.com";
  private String to;
  private String from;//自己的邮箱，拿来玩的
  private String accreditCode;

  public void setTo(String to) {
    this.to = to;
  }

  public void setAccreditCode(String accreditCode) {
    this.accreditCode = accreditCode;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public void run() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(host);
    mailSender.setUsername(from);
    mailSender.setPassword(accreditCode);
    //加认证机制
    Properties javaMailProperties = new Properties();
    javaMailProperties.put("mail.smtp.auth", true);
    javaMailProperties.put("mail.smtp.starttls.enable", true);
    javaMailProperties.put("mail.smtp.timeout", 5000);
    mailSender.setJavaMailProperties(javaMailProperties);
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(from);
      helper.setTo(to);
      helper.setSubject("guanren15.com");
      helper.setText("前方高能");
      FileSystemResource couponImage = new FileSystemResource("renguan.jpg");
      helper.addAttachment("guanren15.jpg", couponImage);
      mailSender.send(message);
      System.out.println("邮件发送成功！");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
