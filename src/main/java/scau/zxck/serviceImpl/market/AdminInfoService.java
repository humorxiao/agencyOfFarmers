package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.LoginDao;
import scau.zxck.dao.sys.AdminInfoDao;
import scau.zxck.entity.market.Login;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.service.market.IAdminInfoService;
import scau.zxck.service.market.ILoginService;


/**
 * Created by suruijia on 2016/1/29.
 */
@Service
public class AdminInfoService implements IAdminInfoService {
    @Autowired
    private AdminInfoDao adminInfoDao;


    @Override
    public String add(AdminInfo entity) throws BaseException {
        return adminInfoDao.add(entity);
    }
}
