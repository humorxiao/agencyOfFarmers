package scau.zxck.service.sys;

import java.util.List;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.sys.UserInfo;

public interface IUserRegisterService {
    <V> List<V> list(Conditions conditions) throws BaseException;
     String add(UserInfo entity)throws BaseException;
}
