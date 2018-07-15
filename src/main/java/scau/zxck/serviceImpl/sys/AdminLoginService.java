package scau.zxck.serviceImpl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.sys.AdminInfoDao;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.sys.IAdminLoginService;

import java.util.List;
@Service
public class AdminLoginService implements IAdminLoginService {
    @Autowired
    private AdminInfoDao adminInfoDao;
    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return adminInfoDao.list(conditions);
    }

    @Override
    public String add(AdminInfo entity) throws BaseException {
        return adminInfoDao.add(entity);
    }

    @Override
    public AdminInfo findByEntity(AdminInfo entity) throws BaseException {
        return adminInfoDao.findByEntity(entity);
    }
}
