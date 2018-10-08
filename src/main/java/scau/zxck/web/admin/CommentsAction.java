package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.market.UserComments;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.IGoodsInfoService;
import scau.zxck.service.market.IUserCommentsService;
import scau.zxck.service.sys.IUserLoginService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
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
public class CommentsAction {
  @Autowired
  private IGoodsInfoService goodsInfoService;
  @Autowired
  private IUserCommentsService userCommentsService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private IUserLoginService userLoginService;
  @Autowired
  private HttpSession session;
  @RequestMapping(value = "getGoodsComments", method = RequestMethod.POST)
  public void getGoodsComments( HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    JSONArray jsonArray = new JSONArray();
    Conditions conditions = new Conditions();
    List list =
      userCommentsService.list(conditions.eq("goods_info_id", data.get("Goods_PK").toString()));
    if (!list.isEmpty()) {
      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
        JSONObject temp = new JSONObject();
        UserComments comm = (UserComments) iter.next();
        temp.put("Comm_PK", comm.getId());
        temp.put("User_PK", comm.getUser_info_id());
        UserInfo user=userLoginService.findById(comm.getUser_info_id());
        temp.put("User_Name", user.getUser_name());
        temp.put("User_Cell", user.getUser_cell());
        temp.put("User_Email", user.getUser_email());
        temp.put("User_Sex", user.getUser_sex());
        SimpleDateFormat m1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        temp.put("User_RegTime", user.getUser_regtime());
        temp.put("User_Realname", user.getUser_realname());
//        temp.put("User_ID", user.getUser_id());
        temp.put("User_Mark", user.getUser_mark());
        temp.put("Goods_PK", comm.getGoods_info_id());
        temp.put("Comm_Rank", comm.getComm_rank());
        temp.put("Comm_Text", comm.getComm_text());
        temp.put("Comm_Time", comm.getComm_time());
        jsonArray.add(temp);
      }
    }
    r = jsonArray.toString();
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "addComments", method = RequestMethod.POST)
  public void addComments(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    if (session.getAttribute("User_PK") != null) {
      data.put("User_PK", session.getAttribute("User_PK"));
    } else {
      data.put("User_PK", "");
    }
    data.put("Comm_Time", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
    UserComments temp = new UserComments();
    temp.setUser_info_id(data.get("User_PK").toString());
    temp.setGoods_info_id(data.get("Goods_PK").toString());
    temp.setComm_rank((int) Integer.parseInt(data.get("Comm_Rank").toString()));
    temp.setComm_text(data.get("Comm_Text").toString());
    temp.setComm_time(Timestamp.valueOf(data.get("Comm_Time").toString()).toString());

    temp.setUserinfo(new UserInfo());
    temp.setGoodsinfo(new GoodsInfo());
    try {
      userCommentsService.add(temp);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }

  @RequestMapping(value = "getUserGoodsComments", method = RequestMethod.POST)
  public void getUserGoodsComments(HttpServletResponse response) throws Exception {
    Conditions conditions = new Conditions();
    JSONObject temp=new JSONObject();
    String r = "";
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    if (session.getAttribute("User_PK") != null) {
      data.put("User_PK", session.getAttribute("User_PK"));
    } else {
      data.put("User_PK", "");
    }
    List list =
      userCommentsService.list(conditions.eq("goods_info_id", data.get("Goods_PK").toString())
        .and().eq("user_info_id", data.get("User_PK").toString()));
    if(!list.isEmpty()){
      UserComments comm =(UserComments)list.get(0);

      temp.put("Comm_PK", comm.getId());
      temp.put("User_PK", comm.getUser_info_id());
      temp.put("Goods_PK", comm.getGoods_info_id());
      temp.put("Comm_Rank", comm.getComm_rank());
      temp.put("Comm_Text", comm.getComm_text());
      temp.put("Comm_Time", comm.getComm_time());
    }
    r=temp.toString();
    FlushWriteUtil.flushWrite(response,r);
  }
  @RequestMapping(value = "deleteComments",method = RequestMethod.POST)
  public void deleteComments(HttpServletResponse response) throws Exception{
    String r="";
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    try {
      userCommentsService.deleteByIds(data.get("Comm_PK").toString());
      r="{\"status\":1}";
    }catch (Exception e){
      e.printStackTrace();
      r="{\"status\":0}";
    }
    FlushWriteUtil.flushWrite(response,r);
  }
}
