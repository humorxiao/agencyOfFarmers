package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsInfo;

import java.util.List;

public interface IGoodsInfoService {
    <V> List<V> list(Conditions conditions) throws BaseException;
    GoodsInfo findById(String id) throws BaseException;
    <V> List<V> listAll() throws BaseException;
    void updateById(GoodsInfo entity) throws BaseException;

}
