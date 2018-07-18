package scau.zxck.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TestAction {
    @RequestMapping(value = "loginx",method = RequestMethod.POST)
    public String s(Model model){
        Map<String,String> map=new HashMap<>();
        map.put("yhx","666");
        model.addAllAttributes(map);
        return "success";
    }
}
