package scau.zxck.serviceImpl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.sys.SystemUserInfoDao;
import scau.zxck.entity.sys.SystemUserInfo;
import scau.zxck.service.sys.ISystemUserService;

import java.util.List;
@Service
public class SystemUserService implements ISystemUserService {
    @Autowired
    private SystemUserInfoDao systemUserInfoDao;

    @Override
    public String add(SystemUserInfo entity) throws BaseException {
        return systemUserInfoDao.add(entity);
    }

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return systemUserInfoDao.list(conditions);
    }

    @Override
    public <V> List<V> listAll() throws BaseException {
        return systemUserInfoDao.listAll();
    }

    @Override
    public void deleteByEntity(SystemUserInfo entity) throws BaseException {
        systemUserInfoDao.deleteByEntity(entity);
    }

    @Override
    public void deleteByIds(Object... ids) throws BaseException {
        systemUserInfoDao.deleteByIds(ids);
    }

    @Override
    public SystemUserInfo findById(String id) throws BaseException {
        return systemUserInfoDao.findById(id);
    }
}
