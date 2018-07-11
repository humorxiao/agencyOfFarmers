package scau.zxck.web.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.Login;
import scau.zxck.service.market.ILoginService;
import scau.zxck.utils.HtmlRegexpUtil;

import java.util.List;

/**
 * Created by suruijia on 2016/2/6.
 */
@Controller
@RequestMapping("/")
public class LoginAction {
    @Autowired
    private ILoginService loginService;


    /**
     * 登录
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String userName, String password) throws BaseException{

        Login loginResult = loginService.findOne("123qweasdzxc");
        if (userName.equals(loginResult.getUsername()) && password.equals(loginResult.getPassword())) {
            return "success";
        } else {
            return "fail";
        }
    }

}
