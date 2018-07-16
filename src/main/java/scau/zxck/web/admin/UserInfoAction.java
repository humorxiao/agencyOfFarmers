package scau.zxck.web.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.DeliveryAddress;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.IDeliveryAddressService;
import scau.zxck.service.sys.IUserLoginService;
import sun.security.krb5.EncryptedData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
@RequestMapping("/")
public class UserInfoAction {
  @Autowired
  private IUserLoginService userLoginService;
  @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
  public String getUserInfo(String jsonStr) throws BaseException {
    JSONObject data = JSONObject.parseObject(jsonStr);
    JSONObject temp = new JSONObject();
    String r = "";
    UserInfo userInfo = userLoginService.findById(data.get("User_PK").toString());
    temp.put("User_PK", userInfo.getId());
    temp.put("User_Name", userInfo.getUser_name());
    temp.put("User_Cell", userInfo.getUser_cell());
    temp.put("User_Email", userInfo.getUser_email());
    temp.put("User_Sex", userInfo.getUser_sex());
    temp.put("User_RegTime", userInfo.getUser_regtime());
    temp.put("User_Realname", userInfo.getUser_realname());
    temp.put("User_ID", userInfo.getUser_id());
    r = temp.toString();
    return null;
  }

  @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    public String updateUserInfo(String jsonStr) throws BaseException{
        JSONObject data=JSONObject.parseObject(jsonStr);
//        JSONObject temp=new JSONObject();
        String r="";
        UserInfo temp=userLoginService.findById(data.get("User_PK").toString());
        data.put("user_password",temp.getUser_password());
        temp.setUser_password(data.get("User_Password").toString());
        temp.setUser_name(data.get("User_Name").toString());
        temp.setUser_cell(data.get("User_Cell").toString());
        temp.setUser_email(data.get("User_Email").toString());
        temp.setUser_sex((int)Integer.parseInt(data.get("User_Sex").toString()));
        temp.setUser_regtime(Timestamp.valueOf(data.get("User_RegTime").toString()).toString());
        temp.setUser_realname(data.get("User_Realname").toString());
        temp.setUser_id(data.get("User_ID").toString());
        String c = data.get("User_Mark").toString();
        temp.setUser_mark(c);

        temp.setCart(new CartInfo());
        temp.setDeliveryaddress(new DeliveryAddress());
        Conditions conditions=new Conditions();
       try{
           userLoginService.update(temp,conditions.eq("id",data.get("User_PK").toString()));
           r = "{\"status\":1}";
       }catch (Exception e){
           r = "{\"status\":0}";
       }
        return null;
    }
    @Autowired
    private IDeliveryAddressService deliveryAddressService;
    @RequestMapping(value = "getUserDeliveryAddress",method = RequestMethod.POST)
    public String getUserDeliveryAddress(String jsonStr) throws BaseException{
        String r="";
      JSONObject data=JSONObject.parseObject(jsonStr);
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if(session.getAttribute("User_PK")!=null){
            data.put("Deliv_PK",session.getAttribute("User_PK"));
            data.put("User_PK",session.getAttribute("User_PK"));
        }
        else{
            data.put("Deliv_PK","");
            data.put("User_PK","");
        }
        JSONObject temp = new JSONObject();
        DeliveryAddress address = deliveryAddressService.findById(data.get("User_PK").toString());
        temp.put("Deliv_PK", address.getId());
        temp.put("Deliv_Cell", address.getDeliv_cell());
        temp.put("Deliv_Name", address.getDeliv_name());
        temp.put("Deliv_Address", address.getDeliv_address());
        temp.put("Deliv_Zipcode", address.getDeliv_zipcode());
        r=temp.toString();
       return "success";
    }
    @RequestMapping(value = "updateUserDeliveryAddress",method = RequestMethod.POST)
    public String updateUserDeliveryAddress(String jsonStr) throws BaseException{
        String r="";
        JSONObject data=JSONObject.parseObject(jsonStr);
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session.getAttribute("User_PK")!=null){
            data.put("Deliv_PK",(int)session.getAttribute("User_PK"));
            data.put("User_PK",(int)session.getAttribute("User_PK"));
        }
        else{
            data.put("Deliv_PK","");
            data.put("User_PK","");
        }
        DeliveryAddress temp=deliveryAddressService.findById(data.get("Deliv_PK").toString());
        temp.setDeliv_cell(data.get("Deliv_Cell").toString());
        temp.setDeliv_name(data.get("Deliv_Name").toString());
        temp.setDeliv_address(data.get("Deliv_Address").toString());
        temp.setDeliv_zipcode(data.get("Deliv_Zipcode").toString());
        try{
            deliveryAddressService.updateById(temp);
            r = "{\"status\":1}";
        } catch (Exception e) {
            e.printStackTrace();
            r = "{\"status\":0}";
        }
        return "success";
    }
}
