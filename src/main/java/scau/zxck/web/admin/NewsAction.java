package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.UnionNews;
import scau.zxck.service.market.INewsService;
import scau.zxck.service.market.IUnionNewsService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:43
 */
@Controller
@RequestMapping("/")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class NewsAction {
  @Autowired
  private INewsService newsService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @Autowired
  private IUnionNewsService unionNewsService;

  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @RequestMapping(value = "getNews", method = RequestMethod.POST)
//  @Test
  public void getNews(HttpServletResponse response) throws Exception {
    String r = "";
    List list = newsService.listAll();
    JSONArray jsonarr = new JSONArray();
    int cnt = 0;
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
      JSONObject temp = new JSONObject();
      UnionNews news = (UnionNews) iter.next();
      temp.put("News_PK", news.getId());
      temp.put("News_Title", news.getNews_title());
      temp.put("News_Text", news.getNews_text());
      temp.put("News_Time", news.getNews_time());
      temp.put("News_Picture", news.getNews_picture());
      temp.put("Remark", news.getRemark());
      jsonarr.add(temp);
      cnt++;
      if (cnt == 4) break;
    }
    r = jsonarr.toString();
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "getNewsNoPage", method = RequestMethod.POST)
  public void getNewsNoPage(HttpServletResponse response) throws Exception {
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
      temp.put("News_Picture", news.getNews_picture());
      temp.put("Remark", news.getRemark());
      jsonarr.add(temp);
    }
    r = jsonarr.toString();
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "addNews", method = RequestMethod.POST)
//  @Test
  public void addNews(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    String news_title=data.get("News_Title").toString();
    String news_text=data.get("News_Text").toString();
    String time=data.get("News_Time").toString();
    data.put("News_Mark","1");
    boolean ret;
    UnionNews temp = new UnionNews();
    temp.setNews_title(data.get("News_Title").toString());
    temp.setNews_text(data.get("News_Text").toString());
    Date date = simpleDateFormat.parse(data.get("News_Time").toString());
    temp.setNews_time(simpleDateFormat.format(date));
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "deleteNews", method = RequestMethod.POST)
  public void deleteNews(HttpServletResponse response) throws Exception {
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    String news_id = data.get("News_PK").toString();
    JSONObject temp = new JSONObject();
    String r = null;
    try {
      unionNewsService.deleteByIds(news_id);
      temp.put("status", true);
      r = temp.toString();
      FlushWriteUtil.flushWrite(response, r);
    } catch (Exception e) {
      temp.put("status", false);
      r = temp.toString();
      FlushWriteUtil.flushWrite(response, r);
    }
  }

  @RequestMapping(value = "getLikesNews", method = RequestMethod.POST)
  public void getLikesNews(HttpServletResponse response) throws Exception, UnsupportedEncodingException, IOException {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    String likes = data.get("likes").toString();
    likes = java.net.URLDecoder.decode(likes, "utf-8");
    if (likes != null) {
      JSONArray jsonarr = new JSONArray();
      Conditions conditions = new Conditions();
      List list = unionNewsService.list(conditions.like("news_title", "%" + likes + "%"));
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "getOneNews", method = RequestMethod.POST)
//  @Test
  public void getOneNews(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    if (session.getAttribute("User_PK") != null) {
      data.put("User_PK", (int) session.getAttribute("User_PK"));
      JSONObject temp = new JSONObject();
      Conditions conditions = new Conditions();
      List list = unionNewsService.list(conditions.eq("id", data.get("News_PK").toString()));
      if (!list.isEmpty()) {
        UnionNews news = (UnionNews) list.get(0);
        temp.put("News_PK", news.getId());
        temp.put("News_Title", news.getNews_title());
        temp.put("News_Text", news.getNews_text());
        temp.put("News_Time", news.getNews_time());
        temp.put("News_Mark", news.getNews_mark());
        r = temp.toString();
      }
    }
    FlushWriteUtil.flushWrite(response, r);
  }
}
