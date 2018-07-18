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
import org.springframework.web.context.request.ServletWebRequest;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.UnionNewsDao;
import scau.zxck.entity.market.UnionNews;
import scau.zxck.service.market.INewsService;
import scau.zxck.service.market.IUnionNewsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class NewsAction {
  @Autowired
  private INewsService newsService;

  @RequestMapping(value = "getNews", method = RequestMethod.POST)
//  @Test
  public String getNews(String jsonStr) throws BaseException {
    String r = "";
//    String jsonStr = "{\"News_Title\":\"the third title\",\"News_Text\":\"hhh\",\"News_Time\":\"2018-07-18 11:50:25\",\"News_Mark\":\"1\"}";
    List list = newsService.listAll();
    JSONArray jsonarr = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
      JSONObject temp = new JSONObject();
      UnionNews news = (UnionNews) iter.next();

      temp.put("News_PK", news.getId());
      temp.put("News_Title", news.getNews_title());
      temp.put("News_Text", news.getNews_text());
      temp.put("News_Time", news.getNews_time());

      if (news.getNews_mark() == 1) {
        jsonarr.add(temp);
      }
    }
    r = jsonarr.toString();
    return "success";

  }

  @RequestMapping(value = "getNewsNoPage", method = RequestMethod.POST)
//  @Test
  public String getNewsNoPage() throws BaseException {
    String r = "";
    List list = newsService.listAll();
    JSONArray jsonarr = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
      JSONObject temp = new JSONObject();
      UnionNews news = (UnionNews) iter.next();

      temp.put("News_PK", news.getId());
      temp.put("News_Title", news.getNews_title());
      temp.put("News_Text", news.getNews_text());
      temp.put("News_Time", news.getNews_time());

      if (news.getNews_mark() == 1) {
        jsonarr.add(temp);
      }
    }
    r = jsonarr.toString();
//    System.out.println(r);
    return "success";
  }

  @RequestMapping(value = "addNews", method = RequestMethod.POST)
//  @Test
  public String addNews(String jsonStr) throws BaseException, IOException {
    String r = "";
//    String jsonStr = "{\"News_Title\":\"the third title\",\"News_Text\":\"hhh\",\"News_Time\":\"2018-07-18 13:50:25\",\"News_Mark\":\"1\"}";
    JSONObject data = JSONObject.parseObject(jsonStr);
    HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpServletResponse response =
            ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    HttpSession session = request.getSession();
    String News_Title =
            request.getParameter("News_Title") != null ? request.getParameter("News_Title") : "";
    String htmlData =
            request.getParameter("content1") != null ? request.getParameter("content1") : "";
    String News_Mark =
            request.getParameter("News_Mark") != null ? request.getParameter("News_Mark") : "";
    data.put("News_Title", News_Title);
    data.put("News_Text", htmlData);
    data.put("News_Mark", News_Mark);
    data.put("News_Time", (new SimpleDateFormat("yyyy-MM-dd HH:MM:ss")).format(new Date()));
    boolean ret;
    UnionNews temp = new UnionNews();
    temp.setNews_title(data.get("News_Title").toString());
    temp.setNews_text(data.get("News_Text").toString());
    temp.setNews_time(Timestamp.valueOf(data.get("News_Time").toString()).toString());
    temp.setNews_mark((int) Integer.parseInt(data.get("News_Mark").toString()));
    try {
      newsService.add(temp);
      ret = true;
    } catch (Exception e) {
      e.printStackTrace();
      ret = false;
    }
    if (ret) {
      r = "{\"status\":1}";
    } else {
      r = "{\"status\":0}";
    }
    response.sendRedirect("../user&newsManagePage.html#panel-923725");
//    System.out.println(r);
    return "success";
  }

  @Autowired
  private IUnionNewsService unionNewsService;

  @RequestMapping(value = "getLikesNews", method = RequestMethod.POST)
  public String getLikesNews(String jsonStr) throws BaseException, UnsupportedEncodingException {
    String r = "";
    JSONObject data = JSONObject.parseObject(jsonStr);
    HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpServletResponse response =
            ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    HttpSession session = request.getSession();
    String likes = request.getParameter("likes");
    likes = java.net.URLDecoder.decode(likes, "utf-8");
    if (likes != null) {
      JSONArray jsonarr = new JSONArray();
      Conditions conditions = new Conditions();
//      String hql = "from UnionNews where news_title like '%" + likes + "%'";
      List list = unionNewsService.list(conditions.like("news_title", "%" + likes + "%"));
//              DataSearch.searchByHQL(hql);

      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
        JSONObject temp = new JSONObject();
        UnionNews news = (UnionNews) iter.next();

        temp.put("News_PK", news.getId());
        temp.put("News_Title", news.getNews_title());
        temp.put("News_Text", news.getNews_text());
        temp.put("News_Time", news.getNews_time());
        jsonarr.add(temp);
      }
      r = jsonarr.toString();
    }
    return "success";
  }

  @RequestMapping(value = "getOneNews", method = RequestMethod.POST)
//  @Test
  public String getOneNews(String jsonStr) throws BaseException {
    String r = "";
    //String jsonStr ="{\"News_PK\":\"1\"}";
    JSONObject data = JSONObject.parseObject(jsonStr);
    HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpServletResponse response =
            ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    HttpSession session = request.getSession();
    if (session.getAttribute("User_PK") != null) {
      data.put("User_PK", (int) session.getAttribute("User_PK"));
      JSONObject temp = new JSONObject();
      Conditions conditions = new Conditions();
//          String hql = "from UnionNews where news_pk = " + json.get("News_PK").toString();
      List list = unionNewsService.list(conditions.eq("id", data.get("News_PK").toString()));
//                  DataSearch.searchByHQL(hql);

      if (!list.isEmpty()) {
        UnionNews news = (UnionNews) list.get(0);
        temp.put("News_PK", news.getId());
        temp.put("News_Title", news.getNews_title());
        temp.put("News_Text", news.getNews_text());
        temp.put("News_Time", news.getNews_time().toString());
        temp.put("News_Mark", news.getNews_mark());
        r = temp.toString();
      }
//    System.out.println(r);

    }

    return "success";
  }
}


