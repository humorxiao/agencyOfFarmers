package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.SystemUserLog;

import java.util.List;

public interface ISystemLogService {
    String add(SystemUserLog entity) throws BaseException;
    <V> List<V> list(Conditions conditions) throws BaseException;
    <V> List<V> listAll() throws BaseException;
    void deleteByEntity(SystemUserLog entity) throws BaseException;
    void deleteByIds(Object... ids) throws BaseException;
    SystemUserLog findById(String id) throws BaseException;
    void updateById(SystemUserLog entity) throws BaseException;
}
