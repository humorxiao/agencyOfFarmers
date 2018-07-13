package scau.zxck.web.admin;

<<<<<<< Updated upstream
import java.util.List;

=======
import com.alibaba.fastjson.JSON;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
<<<<<<< Updated upstream

import com.alibaba.fastjson.JSON;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.Link;
import scau.zxck.entity.market.Login;
import scau.zxck.service.market.ILinkService;
import scau.zxck.service.market.ILoginService;
import scau.zxck.utils.HtmlRegexpUtil;

=======
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.Login;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.service.market.ILoginService;
import scau.zxck.service.market.ILoginService;
import scau.zxck.serviceImpl.market.LoginService;
import scau.zxck.utils.HtmlRegexpUtil;

import java.util.List;

>>>>>>> Stashed changes
/**
 * Created by suruijia on 2016/2/6.
 */
@Controller
@RequestMapping("/")
public class LoginAction {
    @Autowired
<<<<<<< Updated upstream
    private ILoginService loginService;
=======
    private LoginService loginService;
>>>>>>> Stashed changes


    /**
     * 获取分类
<<<<<<< Updated upstream
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String userName,String password) throws BaseException{
        Login loginResult=loginService.findOne("123qweasdzxc");
        if(userName.equals(loginResult.getUsername())&&password.equals(loginResult.getPassword())){
            return "success";
        }
        else return "fail";
    }
=======
     *
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String admin_name, String admin_password,String admin_cell,String admin_email) throws BaseException {

//        Login loginResult = loginService.findOne("1");
//
//        if (userName.equals(loginResult.getUsername()) && password.equals(loginResult.getPassword())) {
//            return "success";
//        } else {
//            return "fail";
//        }
        AdminInfo adminInfo=new AdminInfo();
        adminInfo.setAdmin_name(admin_name);
        adminInfo.setAdmin_password(admin_password);
        adminInfo.setAdmin_cell(admin_cell);
        adminInfo.setAdmin_email(admin_email);

        return "success";
    }


>>>>>>> Stashed changes
}
