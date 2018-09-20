package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import scau.zxck.utils.FlushWriteUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("/")
public class UploadPictureAction {
  @RequestMapping(value = "uploadPictures", method = RequestMethod.POST)
  public void uploadPicture(HttpServletResponse response,MultipartFile image) throws Exception {
    String path = "src/main/webapp/static/image/";
    JSONObject temp=new JSONObject();
    if (image.getSize()>5*1024*1024){
      temp.put("status",0);
      temp.put("Exception","文件大小超过5M");
      FlushWriteUtil.flushWrite(response,temp.toString());
      throw new Exception("文件不能超过5M");
    }
    File file=new File(path + image.getOriginalFilename());
    image.transferTo(file);
    temp.put("status",1);
    FlushWriteUtil.flushWrite(response,temp.toString());
  }
}
