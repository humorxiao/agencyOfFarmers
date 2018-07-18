package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.GoodsLogDao;
import scau.zxck.entity.market.GoodsLog;
import scau.zxck.service.market.IGoodsLogService;

import java.util.List;

@Service
public class GoodsLogService implements IGoodsLogService {
    @Autowired
    private GoodsLogDao goodsLogDao;

    @Override
    public <V> List<V> listAll() throws BaseException {
        return goodsLogDao.listAll();
    }

    @Override
    public String add(GoodsLog entity) throws BaseException {
        return goodsLogDao.add(entity);
    }
}
