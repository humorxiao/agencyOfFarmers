package scau.zxck.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TestAction {
    @RequestMapping(value = "test",method = RequestMethod.POST)
    @ResponseBody
    public String s(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String ss="";
       ss+=request.getAttribute("yhx");
       ss+=request.getAttribute("123");
       ss+=request.getAttribute("abc");
       ss+=request.getAttribute("456");
        ss+=session.getAttribute("ILYJL");
        ss+=session.getAttribute("YJLIL");
        Cookie[] cookies=request.getCookies();
        for(Cookie c:cookies){
            if(c.getName().equals("username")){
                ss+="success3";
            }
        }
        return ss;
    }
}
