package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.TypeInfo;

import java.util.List;

public interface ITypeInfoService {
    String add(TypeInfo entity) throws BaseException;
    <V> List<V> list(Conditions conditions) throws BaseException;
    <V> List<V> listAll() throws BaseException;
    void deleteByEntity(TypeInfo entity) throws BaseException;
    void deleteByIds(Object... ids) throws BaseException;
    TypeInfo findById(String id) throws BaseException;
    void updateById(TypeInfo entity) throws BaseException;
}
