package scau.zxck.service.sys;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.sys.UserInfo;

import java.util.List;

public interface IUserLoginService {
    <V> List<V> list(Conditions conditions) throws BaseException;
     String add(UserInfo entity)throws BaseException;
    UserInfo findById(String id) throws BaseException;
    void update(UserInfo entity, Conditions conditions) throws BaseException;
}
