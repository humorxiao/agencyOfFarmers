package scau.zxck.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.serviceImpl.market.AdminInfoService;

/**
 * Created by suruijia on 2016/2/6.
 */
@Controller
@RequestMapping("/")
public class AdminInfoAction {
    @Autowired
   private AdminInfoService adminInfoService;


    /**
     * 获取分类
     *
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "login1", method = RequestMethod.POST)
    public String login(String admin_name, String admin_password,String admin_cell,String admin_email) throws BaseException {
        AdminInfo adminInfo=new AdminInfo();
        adminInfo.setAdmin_name(admin_name);
        adminInfo.setAdmin_password(admin_password);
        adminInfo.setAdmin_cell(admin_cell);
        adminInfo.setAdmin_email(admin_email);
        adminInfoService.add(adminInfo);
        return "success";
    }


}
