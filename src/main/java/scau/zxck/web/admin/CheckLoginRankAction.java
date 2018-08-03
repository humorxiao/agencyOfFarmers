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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.PrintWriter;
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
    public void checkLoginRank( HttpServletResponse response) throws Exception {
        JSONObject r=new JSONObject();
      //  String r="";
        if (session.getAttribute("isAdmin") != null) {
            if ((boolean) session.getAttribute("isAdmin")) {
//    if(data.get("isAdmin")!=null){
//      if((boolean)data.get("isAdmin")){
                r.put("status",2);//管理员
                r.put("Admin_PK",session.getAttribute("Admin_PK"));
            } else {
                r.put("status",1);
                r.put("User_PK",session.getAttribute("User_PK"));//用户
            }
        } else {
          r.put("status", 0);//游客

        }
      System.out.println(r.toJSONString());
      PrintWriter out = response.getWriter();
      out.flush();
      out.write(r.toJSONString());
      out.flush();
    }
}

