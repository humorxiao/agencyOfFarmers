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
import scau.zxck.dao.market.GoodsInfoDao;
import scau.zxck.entity.market.GoodsInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:config/spring/spring.xml")
public class CheckLoginRankAction {
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @RequestMapping(value = "checkLoginRank", method = RequestMethod.POST)
//  @Test
  public String checkLoginRank(String jsonStr) throws Exception {
//    String jsonStr = "{\"isAdmin\":true}";
    JSONObject data = JSONObject.parseObject(jsonStr);
    String r = "";
//    BufferedReader br = request.getReader();
//    String str, wholeStr = "";
//    while((str = br.readLine()) != null){
//      wholeStr += str;
//    }
//    jsonStr=wholeStr;
//    HttpServletRequest request =
//            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    HttpSession session = request.getSession();
    if (session.getAttribute("isAdmin") != null) {
    if((boolean)session.getAttribute("isAdmin")){
//    if(data.get("isAdmin")!=null){
//      if((boolean)data.get("isAdmin")){
        r="{\"status\":2}";//管理员
      }else{
        r="{\"status\":1}";//用户
      }
    }
    else{
      r="{\"status\":0}";//游客
    }
    return "success";
//    System.out.println(r);
  }
}

