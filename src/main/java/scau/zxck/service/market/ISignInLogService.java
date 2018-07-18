package scau.zxck.service.market;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.SignInLog;

public interface ISignInLogService {
    String add(SignInLog entity) throws BaseException;

}
