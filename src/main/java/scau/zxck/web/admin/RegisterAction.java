package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.ILoginService;
import scau.zxck.service.sys.IAdminLoginService;
import scau.zxck.service.sys.IUserLoginService;
import scau.zxck.service.sys.IUserRegisterService;
import scau.zxck.utils.DataManagerUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class RegisterAction {
  @Autowired
  private IUserRegisterService userRegisterService;

  private DataManagerUtil manager = new DataManagerUtil();

  @RequestMapping(value = "register", method = RequestMethod.POST)
  public String register(String jsonStr) throws BaseException {
    JSONObject json = JSONObject.parseObject(jsonStr);
    JSONObject temp = new JSONObject();
    json.put("User_RegTime", (new SimpleDateFormat("yyy-MM-dd HH:MM:ss")
            .format(new Date())));
    temp.put("isSuccess", manager.insertData("UserInfo", json));
    Conditions conditions = new Conditions();
    List list = userRegisterService.list(conditions.eq("user_name", json.get("User_name").toString())
        .or().eq("user_cell", json.get("User_Cell").toString()).or()
        .eq("user_email", json.get("User_Email").toString()).and()
        .eq("user_password", json.get("User_Password").toString()));
    if (list.isEmpty()) {
      return null;//原来是return temp
    } else {
      UserInfo user = (UserInfo) list.get(0);
      temp.put("User_PK", user.getId());

      // 生成购物车和收货地址ַ
      json.put("Cart_PK", (int) Integer.parseInt(temp.get("User_PK").toString()));
      manager.insertData("CartInfo", json);
      json.put("Deliv_PK", (int) Integer.parseInt(temp.get("User_PK").toString()));
      manager.insertData("DeliveryAddress", json);
    }


      return null;//原来是return temp
  }
}
