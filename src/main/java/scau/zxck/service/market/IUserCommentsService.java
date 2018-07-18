package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UserCollection;
import scau.zxck.entity.market.UserComments;

import java.util.List;

public interface IUserCommentsService {
    String add(UserComments entity) throws BaseException;
    <V> List<V> list(Conditions conditions) throws BaseException;
    void delete(Conditions conditions) throws BaseException;
    void deleteByIds(Object... ids) throws BaseException;
}
