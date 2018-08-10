package scau.zxck.serviceImpl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.sys.UserInfoDao;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.sys.IUserLoginService;

import java.util.List;

@Service
public class UserLoginService implements IUserLoginService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return userInfoDao.list(conditions);
    }

    @Override
    public String add(UserInfo entity) throws BaseException {
        return userInfoDao.add(entity);
    }

    @Override
    public UserInfo findById(String id) throws BaseException {
        return userInfoDao.findById(id);
    }

    @Override
    public void updateById(UserInfo entity) throws BaseException {
        userInfoDao.updateById(entity);
    }

    @Override
    public UserInfo findByName(String name) throws BaseException {
        Conditions conditions=new Conditions();
        return (UserInfo)userInfoDao.find(conditions.eq("user_name",name));
    }

    @Override
    public void update(UserInfo entity, Conditions conditions) throws BaseException {
        userInfoDao.update(entity,conditions);
    }
}
