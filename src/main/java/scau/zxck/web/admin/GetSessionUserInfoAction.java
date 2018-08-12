package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.sys.IUserLoginService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class GetSessionUserInfoAction {
    @Autowired
    private IUserLoginService userLoginService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "getSessionUserInfo", method = RequestMethod.POST)
//  @Test
    public void getSessionUserInfo( HttpServletResponse response) throws Exception {
        String r = "";
        JSONObject data = new JSONObject();
        if (session.getAttribute("User_PK") != null) {
            data.put("User_PK",session.getAttribute("User_PK").toString());
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
        FlushWriteUtil.flushWrite(response,r);
    }
}






