package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.UnionNewsDao;
import scau.zxck.entity.market.UnionNews;
import scau.zxck.service.market.INewsService;

import java.util.List;

@Service
public class NewsService implements INewsService {
    @Autowired
    private UnionNewsDao unionNewsDao;
    @Override
    public <V> List<V> listAll() throws BaseException {
        return unionNewsDao.listAll();
    }

    @Override
    public String add(UnionNews entity) throws BaseException {
        return unionNewsDao.add(entity);
    }
}
