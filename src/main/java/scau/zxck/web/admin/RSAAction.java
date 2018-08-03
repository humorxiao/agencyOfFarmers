package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.exception.BaseException;
import scau.zxck.utils.RSAManager;
import scau.zxck.utils.ReadJSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class RSAAction {
    @Autowired
    private HttpServletRequest request;
    @RequestMapping(value = "startRSAThread",method = RequestMethod.POST)
    public void startRSAThread() throws BaseException{
        RSAManager rsa = RSAManager.getInstance();
       rsa.startCircleKeyThread();
    }
    @RequestMapping(value = "getRSAPublicKey",method = RequestMethod.POST)
    public void getRSAPublicKey(HttpServletResponse response) throws Exception{
       String r= RSAManager.getPublicKey();
        PrintWriter out=response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }
    @RequestMapping(value = "encodeByPublicKey",method = RequestMethod.POST)
    public void encodeByPublicKey(HttpServletResponse response) throws Exception {
        RSAManager rsa = RSAManager.getInstance();
        JSONObject data=ReadJSON.readJSONStr(request);
        String data1=(String)data.get("data");
        String publickey=(String)data.get("publickey");
        String r=rsa.encryptByPublicKey(data1,publickey);
        PrintWriter out=response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }
    @RequestMapping(value = "decodeByPublicKey",method = RequestMethod.POST)
    public void decodeByPublicKey(HttpServletResponse response)throws Exception{
        RSAManager rsa = RSAManager.getInstance();
        JSONObject data=ReadJSON.readJSONStr(request);
        String data2=(String)data.get("data");
        String publickey=(String)data.get("publickey");
        String r=rsa.decryptByPublicKey(data2,publickey);
        PrintWriter out=response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }

}
