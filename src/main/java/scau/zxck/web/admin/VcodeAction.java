package scau.zxck.web.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import scau.zxck.utils.AuthCodeUtil;
import scau.zxck.utils.FlushWriteUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class VcodeAction {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    @RequestMapping(value = "getVCODE", method = RequestMethod.GET)
    public void getVCODE(HttpServletResponse response) throws IOException {
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        ServletOutputStream sos=response.getOutputStream();
        try {
            ImageIO.write(AuthCodeUtil.createImage(),"JPEG",sos);
            session.setAttribute("authcode",AuthCodeUtil.getsRand());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            sos.flush();
            sos.close();
        }
      PrintWriter out=response.getWriter();
      out.flush();
      out.flush();

    }

    @RequestMapping(value = "validateVCode",method = RequestMethod.POST)
    public void validateVCode(HttpServletResponse response)throws Exception{
        String r="";
      BufferedReader br = request.getReader();
      String str, wholeStr = "";
      while((str = br.readLine()) != null){
        wholeStr += str;
      }
      String jsonStr=wholeStr;

        System.out.println(jsonStr);
      JSONObject data= JSON.parseObject(jsonStr);
        String code=data.get("code").toString();
        boolean ret=code.equals((String) session.getAttribute("authcode"));
        if(ret){
            r="{\"status\":1}";
        }else{
            r="{\"status\":0}";
        }
        FlushWriteUtil.flushWrite(response,r);
    }
}
