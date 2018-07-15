package scau.zxck.web.admin;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class ValidateVCodeAction {
    @RequestMapping(value = "validateVCode",method = RequestMethod.POST)
    public String validateVCode(){
        String r="";
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        String code=request.getParameter("code");
        boolean ret=code.equals((String) session.getAttribute("authcode"));
        if(ret){
            r="{\"status\":1}";
        }else{
            r="{\"status\":0}";
        }
        return null;
    }
}
