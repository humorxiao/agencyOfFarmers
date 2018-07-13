package scau.zxck.service.market;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.Login;
import scau.zxck.entity.sys.AdminInfo;

/**
 * Created by suruijia on 2016/1/29.
 */
public interface IAdminInfoService {


    String add(AdminInfo entity) throws BaseException;

}
