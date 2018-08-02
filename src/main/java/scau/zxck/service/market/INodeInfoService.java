package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.NodeInfo;

import java.util.List;

public interface INodeInfoService {
    String add(NodeInfo entity) throws BaseException;
    <V> List<V> list(Conditions conditions) throws BaseException;
    <V> List<V> listAll() throws BaseException;
    void deleteByEntity(NodeInfo entity) throws BaseException;
    void deleteByIds(Object... ids) throws BaseException;
    NodeInfo findById(String id) throws BaseException;
}
