package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.market.UserCollection;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.IUserCollectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/")
public class CollectInfoAction {
    @Autowired
    private IUserCollectService userCollectService;
    @RequestMapping(value = "addCollect", method = RequestMethod.POST)
    public String addCollection(String jsonStr) throws BaseException {
        String r="";
        JSONObject data = JSONObject.parseObject(jsonStr);
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session.getAttribute("User_PK") != null) {
            data.put("User_PK", session.getAttribute("User_PK"));
        } else {
            data.put("User_PK", "");
        }
        data.put("Collect_Time", (new SimpleDateFormat("yyyy-MM-dd HH:MM:ss"))
                .format(new Date()));
//      UserComments userComments=new UserComments();
//      userComments.setUser_info_id(data.get("User_PK").toString());
//      userComments.setGoods_info_id(data.get("Goods_PK").toString());
//      userComments.setComm_rank((int)Integer.parseInt(data.get("Comm_Rank").toString()));
//      userComments.setComm_text(data.get("Comm_Text").toString());
//      userComments.setComm_time(Timestamp.valueOf(data.get("Comm_Time").toString()));
//      userComments.setUserinfo(new UserInfo());
//      userComments.setGoodsinfo(new GoodsInfo());
        UserCollection userCollection=new UserCollection();
        userCollection.setUser_info_id(data.get("User_PK").toString());
        userCollection.setGoods_info_id(data.get("Goods_PK").toString());
        userCollection.setCollect_time(Timestamp.valueOf(data.get("Collect_Time").toString()));
        userCollection.setUserinfo(new UserInfo());
        userCollection.setGoodsinfo(new GoodsInfo());
        try {
            userCollectService.add(userCollection);
            r="{\"status\":1}";
        }catch (Exception e){
            e.printStackTrace();
            r="{\"status\":0}";
        }
        return null;
    }
    @RequestMapping(value = "removeCollect",method = RequestMethod.POST)
    public String removeCollect(String jsonStr){
        String r="";
        JSONObject data=JSONObject.parseObject(jsonStr);
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        if(session.getAttribute("User_PK")!=null){
            data.put("User_PK",session.getAttribute("User_PK"));
        }else{
            data.put("User_PK","");
        }
        try {
            userCollectService.deleteByIds(data.get("Collect_PK").toString());
            r="{\"status\":1}";
        } catch (BaseException e) {
            e.printStackTrace();
            r="{\"status\":0}";
        }
        return null;
    }
}
