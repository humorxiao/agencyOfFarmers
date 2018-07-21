package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.market.UserComments;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.sys.IUserLoginService ;
import scau.zxck.service.market.IGoodsInfoService;
import scau.zxck.service.market.IUserCommentsService;
import scau.zxck.service.sys.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:config/spring/spring.xml")
public class GetSessionUserInfoAction {
  @Autowired
  private IUserLoginService userLoginService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @RequestMapping(value = "getSessionUserInfo", method = RequestMethod.POST)
//  @Test
  public String getSessionUserInfo(String jsonStr) throws Exception {
    String r = "";
//   String jsonStr ="{\"User_PK\":\"100003\"}";
//      BufferedReader br = request.getReader();
//      String str, wholeStr = "";
//      while((str = br.readLine()) != null){
//          wholeStr += str;
//      }
//      jsonStr=wholeStr;
    JSONObject data = JSONObject.parseObject(jsonStr);
//    HttpServletRequest request =
//            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    HttpSession session = request.getSession();
    if (session.getAttribute("User_PK") != null) {
//      if (data.get("User_PK") != null) {
        Conditions conditions = new Conditions();
        UserInfo user = userLoginService.findById(data.get("User_PK").toString());
        JSONObject temp = new JSONObject();


        temp.put("User_PK", user.getId());
        temp.put("User_Name", user.getUser_name());
        temp.put("User_Cell", user.getUser_cell());
        temp.put("User_Email", user.getUser_email());
        temp.put("User_Sex", user.getUser_sex());
        temp.put("User_RegTime", user.getUser_regtime());
        temp.put("User_Realname", user.getUser_realname());
        temp.put("User_ID", user.getUser_id());
        r = temp.toJSONString();
      } else {
        r = "{\"User_Name\":\"未登录成功\"}";
      }

      return "success";
//    System.out.println(r);
    }

  }






