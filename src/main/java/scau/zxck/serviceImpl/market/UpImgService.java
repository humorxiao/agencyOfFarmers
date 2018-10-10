package scau.zxck.serviceImpl.market;

import scau.zxck.service.market.IUpImgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import scau.zxck.utils.OSSClientUtil;

/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:45
 */
@Service
public class UpImgService implements IUpImgService {
  public static final Logger logger = LoggerFactory.getLogger(UpImgService.class);
  @Override
  public String updateHead(MultipartFile file) throws Exception {
    if (file == null || file.getSize() <= 0) {
      throw new Exception("file不能为空");
    }
    OSSClientUtil ossClient=new OSSClientUtil();
    String name = ossClient.uploadImg2Oss(file);
    String imgUrl = ossClient.getImgUrl(name);
    String[] split = imgUrl.split("\\?");
    return split[0];
  }

}
