package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.OrderInfo;

import java.util.List;

public interface IOrderInfoService {
    OrderInfo find(Conditions conditions) throws BaseException;
    <V> List<V> list(Conditions conditions) throws BaseException;
    String add(OrderInfo entity) throws BaseException;
    OrderInfo findById(String id) throws BaseException;
    void updateById(OrderInfo entity) throws BaseException;
}
