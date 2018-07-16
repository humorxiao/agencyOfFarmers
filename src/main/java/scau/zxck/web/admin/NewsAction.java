package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UnionNews;
import scau.zxck.service.market.INewsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class NewsAction {
  @Autowired
  private INewsService newsService;

  @RequestMapping(value = "getNews", method = RequestMethod.POST)
  public String getNews(String jsonStr) throws BaseException {
      String r="";
    List list = newsService.listAll();
    JSONArray jsonarr = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      UnionNews news = (UnionNews) iter.next();

      temp.put("News_PK", news.getId());
      temp.put("News_Title", news.getNews_title());
      temp.put("News_Text", news.getNews_text());
      temp.put("News_Time", news.getNews_time().toLocaleString());

      if (news.getNews_mark() == 1) {
        jsonarr.add(temp);
      }
    }
    r=jsonStr.toString();
    return "success";
  }
    @RequestMapping(value = "getNewsNoPage", method = RequestMethod.POST)
    public String getNewsNoPage(String jsonStr) throws BaseException {
        String r="";
        List list = newsService.listAll();
        JSONArray jsonarr = new JSONArray();
        for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
            JSONObject temp = new JSONObject();
            UnionNews news = (UnionNews) iter.next();

            temp.put("News_PK", news.getId());
            temp.put("News_Title", news.getNews_title());
            temp.put("News_Text", news.getNews_text());
            temp.put("News_Time", news.getNews_time().toLocaleString());

            if (news.getNews_mark() == 1) {
                jsonarr.add(temp);
            }
        }
        r=jsonStr.toString();
        return "success";
    }
    @RequestMapping(value = "addNews", method = RequestMethod.POST)
    public String addNews(String jsonStr) throws BaseException, IOException {
      String r="";
      JSONObject data=JSONObject.parseObject(jsonStr);
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response =
                ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpSession session = request.getSession();
        String News_Title=request.getParameter("News_Title") != null ? request.getParameter("News_Title") : "";
        String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
        String News_Mark=request.getParameter("News_Mark") != null ? request.getParameter("News_Mark") : "";
        data.put("News_Title",News_Title);
        data.put("News_Text", htmlData);
        data.put("News_Mark",News_Mark);
        data.put("News_Time", (new SimpleDateFormat("yyyy-MM-dd HH:MM:ss")).format(new Date()));
        boolean ret;
        UnionNews temp = new UnionNews();
        temp.setNews_title(data.get("News_Title").toString());
        temp.setNews_text(data.get("News_Text").toString());
        temp.setNews_time(Timestamp.valueOf(data.get("News_Time").toString()));
        temp.setNews_mark((int)Integer.parseInt(data.get("News_Mark").toString()));
        try {
            newsService.add(temp);
            ret=true;
        }catch (Exception e){
            e.printStackTrace();
            ret=false;
        }
        if(ret){
            r="{\"status\":1}";
        }
        else{
            r="{\"status\":0}";
        }
        response.sendRedirect("../user&newsManagePage.html#panel-923725");
        return "success";
    }
}
