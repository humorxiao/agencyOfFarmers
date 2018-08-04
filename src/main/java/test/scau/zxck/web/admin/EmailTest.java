package test.scau.zxck.web.admin;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.junit.Test;

public class EmailTest {

  public static final String SMTPSERVER = "smtp.qq.com";
  public static final String SMTPPORT = "465";
  public static final String ACCOUT = "582780857@qq.com";
  public static final String PWD = "dfyqgbgtosxnbcdj ";

  @Test
  public void testSendEmail() throws Exception {

    // 创建邮件配置
    Properties props = new Properties();
    props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
    props.setProperty("mail.smtp.host", SMTPSERVER); // 发件人的邮箱的 SMTP 服务器地址
    props.setProperty("mail.smtp.port", SMTPPORT);
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
    props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl


    // 根据邮件配置创建会话，注意session别导错包
    Session session = Session.getDefaultInstance(props);
    // 开启debug模式，可以看到更多详细的输入日志
    session.setDebug(true);
    //创建邮件
    MimeMessage message = createEmail(session);
    //获取传输通道
    Transport transport = session.getTransport();
    transport.connect(SMTPSERVER,ACCOUT, PWD);
    //连接，并发送邮件
      transport.sendMessage(message, message.getAllRecipients());
    transport.close();

  }


  public MimeMessage createEmail(Session session) throws Exception {
    // 根据会话创建邮件
    MimeMessage msg = new MimeMessage(session);
    // address邮件地址, personal邮件昵称, charset编码方式
    InternetAddress fromAddress = new InternetAddress(ACCOUT,
      "kimi", "utf-8");
    // 设置发送邮件方
    msg.setFrom(fromAddress);
    InternetAddress receiveAddress = new InternetAddress(
      "1364736594@qq.com", "test", "utf-8");
    // 设置邮件接收方
    msg.setRecipient(RecipientType.TO, receiveAddress);
    // 设置邮件标题
    msg.setSubject("美女裸聊，请加Q", "utf-8");
    msg.setText("guanren15.com");
    // 设置显示的发件时间
    msg.setSentDate(new Date());
    // 保存设置
    msg.saveChanges();
    return msg;
  }
}
