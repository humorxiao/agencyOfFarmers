package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.UnionNewsDao;
import scau.zxck.service.market.IUnionNewsService;

import java.util.List;

@Service
public class UnionNewsService implements IUnionNewsService {
    @Autowired
    private UnionNewsDao unionNewsDao;

    @Override
    public <V> List<V> listAll() throws BaseException {
        return unionNewsDao.listAll();
    }

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return unionNewsDao.list(conditions);
    }
}
