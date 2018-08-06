package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.SystemUserLogDao;
import scau.zxck.entity.market.SystemUserLog;
import scau.zxck.entity.sys.SystemUserInfo;
import scau.zxck.service.market.ISystemLogService;
import scau.zxck.service.sys.ISystemUserService;

import java.util.List;

@Service
public class SystemUserLogService implements ISystemLogService {
    @Autowired
    private SystemUserLogDao systemUserLogDao;

    @Override
    public String add(SystemUserLog entity) throws BaseException {
        return systemUserLogDao.add(entity);
    }

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return systemUserLogDao.list(conditions);
    }

    @Override
    public <V> List<V> listAll() throws BaseException {
        return systemUserLogDao.listAll();
    }

    @Override
    public void deleteByEntity(SystemUserLog entity) throws BaseException {
        systemUserLogDao.deleteByEntity(entity);
    }

    @Override
    public void deleteByIds(Object... ids) throws BaseException {
        systemUserLogDao.deleteByIds(ids);
    }

    @Override
    public SystemUserLog findById(String id) throws BaseException {
        return systemUserLogDao.findById(id);
    }

    @Override
    public void updateById(SystemUserLog entity) throws BaseException {
        systemUserLogDao.updateById(entity);
    }
}
