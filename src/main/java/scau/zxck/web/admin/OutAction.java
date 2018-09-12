package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.web.listener.UserSessionListener;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class OutAction {

  @Autowired
  private HttpSession session;
  @RequestMapping(value = "out", method = RequestMethod.POST)
  public void out(HttpServletResponse response)throws Exception{
    JSONObject temp=new JSONObject();
    if(session.getAttribute("isAdmin")!=null){
      if((boolean)session.getAttribute("isAdmin")){
        session.removeAttribute("Admin_PK");
      }
      else{
        session.removeAttribute("User_PK");
      }
    }
    session.removeAttribute("isAdmin");
    UserSessionListener.out(session);
    temp.put("status",1);
    FlushWriteUtil.flushWrite(response,temp.toString());
  }
}
