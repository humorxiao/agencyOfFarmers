package scau.zxck.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.entity.sys.SystemUserInfo;
import scau.zxck.utils.FlushWriteUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:43
 */
@Controller
@RequestMapping("/")
public class CheckLoginUserAction {
    @Autowired
    private HttpSession session;
    @RequestMapping(value = "checkLoginUser",method = RequestMethod.POST)
    public void checkLoginUser(HttpServletResponse response) throws Exception{
        String r = "{\"status\":\"\",\"loginName\":\"\"}";//返回的字符串
        SystemUserInfo u=(SystemUserInfo)session.getAttribute("loginUser");
        if(u!=null){
                r = "{\"status\":\"1\",\"loginName\":\""+u.getSystem_user_name()+"\"}";
        }
        else {
            r = "{\"status\":\"0\",\"loginName\":\"\"}";
        }
        FlushWriteUtil.flushWrite(response,r);
    }
}
