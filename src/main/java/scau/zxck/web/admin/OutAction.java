package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class OutAction {

  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @RequestMapping(value = "out", method = RequestMethod.POST)
  public void OutAction() throws Exception {
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    if(session.getAttribute("isAdmin")!=null){
      if((boolean)session.getAttribute("isAdmin")){
        session.removeAttribute("Admin_PK");
      }
      else{
        session.removeAttribute("User_PK");
      }
    }else{

    }
    session.removeAttribute("isAdmin");
  }
}
