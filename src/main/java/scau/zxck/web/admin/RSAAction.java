package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.exception.BaseException;
import scau.zxck.utils.RSAManager;

@Controller
@RequestMapping("/")
public class RSAAction {
    @RequestMapping(value = "startRSAThread",method = RequestMethod.POST)
    public String startRSAThread(String jsonStr) throws BaseException{
        RSAManager rsa = RSAManager.getInstance();
       rsa.startCircleKeyThread();
       return "success";
    }
    @RequestMapping(value = "getRSAPublicKey",method = RequestMethod.POST)
    public String getRSAPublicKey(){
       String r= RSAManager.getPublicKey();
       return "success";
    }
    @RequestMapping(value = "encodeByPublicKey",method = RequestMethod.POST)
    public String encodeByPublicKey(String jsonStr) throws Exception {
        RSAManager rsa = RSAManager.getInstance();
        JSONObject data=JSONObject.parseObject(jsonStr);
        String data1=(String)data.get("data");
        String publickey=(String)data.get("publickey");
        String r=rsa.encryptByPublicKey(data1,publickey);
        return "success";
    }
    @RequestMapping(value = "decodeByPublicKey",method = RequestMethod.POST)
    public String decodeByPublicKey(String jsonStr)throws Exception{
        RSAManager rsa = RSAManager.getInstance();
        JSONObject data=JSONObject.parseObject(jsonStr);
        String data2=(String)data.get("data");
        String publickey=(String)data.get("publickey");
        String r=rsa.decryptByPublicKey(data2,publickey);
        return "success";
    }

}
