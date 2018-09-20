package scau.zxck.web.admin;

import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import scau.zxck.serviceImpl.market.UpImgService;
import scau.zxck.utils.FlushWriteUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:43
 */
@Controller
@RequestMapping("/")
public class UpImgAction {
  public static final Logger logger = LoggerFactory.getLogger(UpImgAction.class);
  @Autowired
  private UpImgService upImgService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpServletResponse response;
  @RequestMapping(value = "/headImgUpload", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public void headImgUpload(MultipartFile file)throws Exception {
    Map<String, Object> value = new HashMap<String, Object>();
    try {
      String url = upImgService.updateHead(file);
      logger.debug("图片路径{}",url);
      value.put("data", url);
      value.put("code", 0);
      value.put("msg", "图片上传成功");
    } catch (Exception e) {
      e.printStackTrace();
      value.put("code", 2000);
      value.put("msg", "图片上传失败");
    }
    FlushWriteUtil.flushWrite(response,JSONObject.toJSONString(value));
  }

}
