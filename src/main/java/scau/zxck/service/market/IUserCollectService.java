package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UserCollection;

import java.util.List;

public interface IUserCollectService {
    String add(UserCollection entity) throws BaseException;
    void deleteByIds(Object... ids) throws BaseException;
    <V> List<V> list(Conditions conditions) throws BaseException;
}
