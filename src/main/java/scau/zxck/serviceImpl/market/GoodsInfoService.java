package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.GoodsInfoDao;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.service.market.IGoodsInfoService;

import java.util.List;
@Service
public class GoodsInfoService implements IGoodsInfoService {
    @Autowired
    private GoodsInfoDao goodsInfoDao;
    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return goodsInfoDao.list(conditions);
    }

    @Override
    public <V> List<V> listAll() throws BaseException {
        return goodsInfoDao.listAll();
    }

    @Override
    public GoodsInfo findById(String id) throws BaseException {
        return goodsInfoDao.findById(id);
    }
}
