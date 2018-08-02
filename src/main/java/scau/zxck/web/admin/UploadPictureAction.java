package scau.zxck.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/")
public class UploadPictureAction {
    @RequestMapping(value = "uploadPicture",method = RequestMethod.POST)
    public void uploadPicture(MultipartFile image) throws Exception{
        String path="D:/";
        image.transferTo(new File(path+image.getOriginalFilename()));
    }
}
