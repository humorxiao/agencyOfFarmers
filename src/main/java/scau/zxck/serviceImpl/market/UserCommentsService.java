package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.UserCommentsDao;
import scau.zxck.entity.market.UserCollection;
import scau.zxck.entity.market.UserComments;
import scau.zxck.service.market.IUserCommentsService;

import java.util.List;

@Service
public class UserCommentsService implements IUserCommentsService {
    @Autowired
    private UserCommentsDao userCommentsDao;
    @Override
    public String add(UserComments entity) throws BaseException {
        return userCommentsDao.add(entity);
    }

    @Override
    public void deleteByIds(Object... ids) throws BaseException {
        userCommentsDao.deleteByIds(ids);
    }

    @Override
    public void delete(Conditions conditions) throws BaseException {
        userCommentsDao.delete(conditions);
    }

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return userCommentsDao.list(conditions);
    }
}
