package scau.zxck.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.Link;
import scau.zxck.entity.market.Login;
import scau.zxck.service.market.ILinkService;
import scau.zxck.service.market.ILoginService;
import scau.zxck.utils.HtmlRegexpUtil;

/**
 * Created by suruijia on 2016/2/6.
 */
@Controller
@RequestMapping("/")
public class LoginAction {
    @Autowired
    private ILoginService loginService;


    /**
     * 获取分类
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
}
