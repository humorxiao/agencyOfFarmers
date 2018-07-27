//package scau.zxck.web.admin;
//
//import org.apache.shiro.web.session.HttpServletSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/")
//public class TestAction {
//  @Autowired
//  private HttpServletRequest request;
//
//  @RequestMapping(value = "test", method = RequestMethod.POST)
//  @ResponseBody
//  public String s() throws Exception{
//      Enumeration<String> headerNames = request.getHeaderNames();
//      String sss="";
//      while(headerNames.hasMoreElements())
//      {
//          String headerName = headerNames.nextElement();
//          // 获取每个请求、及其对应的值
//                 sss+= headerName + "-->" + request.getHeader(headerName) + "<br/>";
//      }
//// 设置解码方式，对于简体中文，使用GBK解码
//      request.setCharacterEncoding("GBK");   // ①
//// 下面依次获取表单域的值
//      String name = request.getParameter("name");
//      String gender = request.getParameter("gender");
//// 如果某个请求参数有多个值，将使用该方法获取多个值
//      String[] color = request.getParameterValues("color");
//      String national = request.getParameter("country");
//      return "name:"+name+"\n"+"gender:"+gender+"\n"+"colors:"+color.toString()+"national:"+national;
//  }
//}

package scau.zxck.web.admin;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import scau.zxck.service.sys.IUserLoginService;


import javax.servlet.http.*;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TestAction {
    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    public String s(String string) throws Exception{
        return "success "+string;
    }
    @Autowired
    private IUserLoginService userLoginService;

  @RequestMapping(value = "test", method = RequestMethod.POST)
  @ResponseBody
  public String s(MultipartFile  profilePicture) throws Exception{
      profilePicture.transferTo(new File("d:/"+profilePicture.getOriginalFilename()));
      return "success";
  }

}
