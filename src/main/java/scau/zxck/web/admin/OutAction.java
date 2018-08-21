package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class OutAction {

  @Autowired
  private HttpSession session;

  @RequestMapping(value = "out", method = RequestMethod.POST)
  public void out(HttpServletResponse response) throws Exception {//
//    JSONObject temp=new JSONObject();
    if (session.getAttribute("isAdmin") != null) {
      if ((boolean) session.getAttribute("isAdmin")) {
        session.removeAttribute("Admin_PK");
      } else {
        session.removeAttribute("User_PK");
      }
    }
    session.removeAttribute("isAdmin");
//    temp.put("isAdmin",session.getAttribute("isAdmin"));
//    temp.put("Admin_PK",session.getAttribute("Admin_PK"));
//    temp.put("User_PK",session.getAttribute("User_PK"));
//    String r=temp.toString();
//    PrintWriter out = response.getWriter();
//    out.flush();
//    out.write(r);
//    out.flush();
  }
}
