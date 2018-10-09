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
  /**
   * response也可以自动填充，编译器报错是编译器的问题，运行是没有错的，
   * 每个方法一定要做视图处理，视图处理有多种方式，1）可以在controller方法最后返回一个
   * 字符串，返回的字符串会在/WEB-INF/pages/目录下找 "返回的字符串"+.jsp这个文件作为视图渲染
   * 2）可以用response写回各种各样的信息。如果少了视图渲染这步（例如controller方法没有返回值，
   * 方法里面有没有用response作页面渲染，那么Spring就会默认用controller方法的方法名去/WEN-INF/pages
   * 目录下找对应的jsp文件，若找不到就会出错
   */
  @Autowired
  private HttpServletResponse response;
  @RequestMapping(value = "out", method = RequestMethod.POST)
  public void out()throws Exception{
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
