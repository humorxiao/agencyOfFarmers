package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.UserCollectionDao;
import scau.zxck.entity.market.UserCollection;
import scau.zxck.service.market.IUserCollectService;

import java.util.List;

@Service
public class UserCollectService implements IUserCollectService {
    @Autowired
    private UserCollectionDao userCollectionDao;

    @Override
    public void deleteByIds(Object... ids) throws BaseException {
        userCollectionDao.deleteByIds(ids);
    }

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return userCollectionDao.list(conditions);
    }

    @Override
    public String add(UserCollection entity) throws BaseException {
        return userCollectionDao.add(entity);
    }
}
