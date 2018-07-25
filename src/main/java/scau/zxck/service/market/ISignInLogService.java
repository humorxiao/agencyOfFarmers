package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.SignInLog;

import java.util.List;

public interface ISignInLogService {
    <V> List<V> list(Conditions conditions) throws BaseException;

    String add(SignInLog entity) throws BaseException;

}
