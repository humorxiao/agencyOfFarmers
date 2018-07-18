package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.OrderInfoDao;
import scau.zxck.entity.market.OrderInfo;
import scau.zxck.service.market.IOrderInfoService;

import java.util.List;

@Service
public class OrderInfoService implements IOrderInfoService {
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Override
    public OrderInfo find(Conditions conditions) throws BaseException {
        return orderInfoDao.find(conditions);
    }

    @Override
    public void updateById(OrderInfo entity) throws BaseException {
       orderInfoDao.updateById(entity);
    }

    @Override
    public OrderInfo findById(String id) throws BaseException {
        return orderInfoDao.findById(id);
    }

    @Override
    public String add(OrderInfo entity) throws BaseException {
        return orderInfoDao.add(entity);
    }

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return orderInfoDao.list(conditions);
    }
}
