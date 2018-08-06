package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.NodeInfo;
import scau.zxck.entity.market.ValueItemInfo;

import java.util.List;

public interface IValueItemService {
    String add(ValueItemInfo entity) throws BaseException;
    <V> List<V> list(Conditions conditions) throws BaseException;
    <V> List<V> listAll() throws BaseException;
    void deleteByEntity(ValueItemInfo entity) throws BaseException;
    void deleteByIds(Object... ids) throws BaseException;
    ValueItemInfo findById(String id) throws BaseException;
}
