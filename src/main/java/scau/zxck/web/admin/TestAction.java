package scau.zxck.web.admin;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class TestAction {
  @Autowired
  private HttpServletRequest request;

  @RequestMapping(value = "test", method = RequestMethod.POST)
  public String s() throws Exception {
    HttpSession session=request.getSession();
    String s=(String)session.getAttribute("yhx");
    s+=(String)session.getAttribute("yyy");
    s+=(String)session.getAttribute("yjl");
    s+=(String)session.getAttribute("yjm");
    System.out.println(s);
    return "success";
  }
}
