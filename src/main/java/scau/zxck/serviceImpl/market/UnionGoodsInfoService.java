package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.UnionGoodsInfoDao;
import scau.zxck.dao.market.UnionInfoDao;
import scau.zxck.entity.market.UnionInfo;
import scau.zxck.service.market.IUnionGoodsInfoService;

import java.util.List;


/**
 * Created by suruijia on 2016/1/29.
 */
@Service
public class UnionGoodsInfoService implements IUnionGoodsInfoService {
    @Autowired
    private UnionGoodsInfoDao unionGoodsInfoDao;
    @Override
    public void delete(String id) throws BaseException{
        unionGoodsInfoDao.deleteByIds(id);
    }

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return unionGoodsInfoDao.list(conditions);
    }
}
