package scau.zxck.service.sys;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.NodeInfo;
import scau.zxck.entity.sys.SystemUserInfo;

import java.util.List;

public interface ISystemUserService {
    String add(SystemUserInfo entity) throws BaseException;
    <V> List<V> list(Conditions conditions) throws BaseException;
    <V> List<V> listAll() throws BaseException;
    void deleteByEntity(SystemUserInfo entity) throws BaseException;
    void deleteByIds(Object... ids) throws BaseException;
    SystemUserInfo findById(String id) throws BaseException;
}
