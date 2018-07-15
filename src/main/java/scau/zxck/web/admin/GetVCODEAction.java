package scau.zxck.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import scau.zxck.utils.AuthCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class GetVCODEAction {
  @RequestMapping(value = "getVCODE", method = RequestMethod.POST)
  public String getVCODE() throws IOException {
    HttpServletResponse response =
        ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
      HttpServletRequest request =
              ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      HttpSession session=request.getSession();
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
      return null;
  }

}
