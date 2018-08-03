package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.ValueItemDao;
import scau.zxck.entity.market.NodeInfo;
import scau.zxck.entity.market.ValueItemInfo;
import scau.zxck.service.market.IValueItemService;

import java.util.List;

@Service
public class ValueItemService implements IValueItemService {
    @Autowired
    private ValueItemDao valueItemDao;
    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return valueItemDao.list(conditions);
    }

    @Override
    public void deleteByEntity(ValueItemInfo entity) throws BaseException {
        valueItemDao.deleteByEntity(entity);
    }

    @Override
    public ValueItemInfo findById(String id) throws BaseException {
        return valueItemDao.findById(id);
    }

    @Override
    public void deleteByIds(Object... ids) throws BaseException {
        valueItemDao.deleteByIds(ids);
    }

    @Override
    public <V> List<V> listAll() throws BaseException {
        return valueItemDao.listAll();
    }

    @Override
    public String add(ValueItemInfo entity) throws BaseException {
        return valueItemDao.add(entity);
    }
}
