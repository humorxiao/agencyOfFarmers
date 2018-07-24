package scau.zxck.web.admin;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.service.sys.IUserLoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import java.awt.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TestAction {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IUserLoginService userLoginService;

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public String s(String name, String password) throws Exception {
        Conditions conditions = new Conditions();
        List list = userLoginService.list(conditions.eq("user_name", name).and().eq("user_password", password));
        if (list != null)
            return "success";
        else return "fail";
    }
}
