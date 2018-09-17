package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.exception.BaseException;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.RSAManagerUtil;
import scau.zxck.utils.ReadJSONUtil;

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
        RSAManagerUtil rsa = RSAManagerUtil.getInstance();
       rsa.startCircleKeyThread();
    }
    @RequestMapping(value = "getRSAPublicKey",method = RequestMethod.POST)
    public void getRSAPublicKey(HttpServletResponse response) throws Exception{
       String r= RSAManagerUtil.getPublicKey();
        FlushWriteUtil.flushWrite(response,r);
    }
    @RequestMapping(value = "encodeByPublicKey",method = RequestMethod.POST)
    public void encodeByPublicKey(HttpServletResponse response) throws Exception {
        RSAManagerUtil rsa = RSAManagerUtil.getInstance();
        JSONObject data= ReadJSONUtil.readJSONStr(request);
        String data1=(String)data.get("data");
        String publickey=(String)data.get("publickey");
        String r=rsa.encryptByPublicKey(data1,publickey);
        FlushWriteUtil.flushWrite(response,r);
    }
    @RequestMapping(value = "decodeByPublicKey",method = RequestMethod.POST)
    public void decodeByPublicKey(HttpServletResponse response)throws Exception{
        RSAManagerUtil rsa = RSAManagerUtil.getInstance();
        JSONObject data= ReadJSONUtil.readJSONStr(request);
        String data2=(String)data.get("data");
        String publickey=(String)data.get("publickey");
        String r=rsa.decryptByPublicKey(data2,publickey);
        FlushWriteUtil.flushWrite(response,r);
    }

}
