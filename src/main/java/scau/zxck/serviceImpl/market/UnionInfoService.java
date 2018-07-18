package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.UnionInfoDao;
import scau.zxck.service.market.IUnionInfoService;

import java.util.List;

@Service
public class UnionInfoService implements IUnionInfoService {
    @Autowired
    private UnionInfoDao unionInfoDao;
    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return unionInfoDao.list(conditions);
    }
}
