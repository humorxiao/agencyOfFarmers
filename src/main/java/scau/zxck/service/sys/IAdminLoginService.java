package scau.zxck.service.sys;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.entity.sys.UserInfo;

import java.util.List;

public interface IAdminLoginService {
     <V> List<V> list(Conditions conditions) throws BaseException;
     String add(AdminInfo entity)throws BaseException;
     AdminInfo findByEntity(AdminInfo entity) throws BaseException;
}
