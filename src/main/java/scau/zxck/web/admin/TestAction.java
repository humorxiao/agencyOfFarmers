package scau.zxck.web.admin;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.*;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TestAction {
  @Autowired
  private HttpServletRequest request;

  @RequestMapping(value = "test", method = RequestMethod.POST)
  @ResponseBody
  public String s(MultipartFile  profilePicture) throws Exception{
      profilePicture.transferTo(new File("d:/"+profilePicture.getOriginalFilename()));
      return "success";
  }
}
