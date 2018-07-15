package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AlterCartAction {
    @RequestMapping(value = "alterCart",method = RequestMethod.POST)
    public String alterCart(String jsonStr){
        JSONObject data=JSONObject.parseObject(jsonStr);
        
        return null;
    }
}
