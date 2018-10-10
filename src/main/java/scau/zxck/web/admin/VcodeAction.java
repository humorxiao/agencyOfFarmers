package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.utils.AuthCodeUtil;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:43
 */
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
      JSONObject data=ReadJSONUtil.readJSONStr(request);
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
