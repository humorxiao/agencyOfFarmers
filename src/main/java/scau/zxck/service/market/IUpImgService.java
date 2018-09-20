package scau.zxck.service.market;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:44
 */
public interface IUpImgService {
  String updateHead(MultipartFile file)throws Exception;
}
