package scau.zxck.service.market;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UserCollection;

public interface IUserCollectService {
    String add(UserCollection entity) throws BaseException;
    void deleteByIds(Object... ids) throws BaseException;
}
