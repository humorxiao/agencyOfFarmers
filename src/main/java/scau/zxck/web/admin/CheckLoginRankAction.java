package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.utils.FlushWriteUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class CheckLoginRankAction {
  @Autowired
  private HttpSession session;

  @RequestMapping(value = "checkLoginRank", method = RequestMethod.POST)
//  @Test
  public void checkLoginRank(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject temp = new JSONObject();
    if (session.getAttribute("isAdmin") != null) {
      if ((boolean) session.getAttribute("isAdmin")) {
        temp.put("status", 2);//管理员
        temp.put("Admin_PK",session.getAttribute("Admin_PK").toString());
      } else {
        temp.put("status", 1);//用户
        temp.put("User_PK",session.getAttribute("User_PK").toString());
      }
    } else {
      temp.put("status", 0);//游客
    }
    r=temp.toString();
    FlushWriteUtil.flushWrite(response, r);
  }
}
