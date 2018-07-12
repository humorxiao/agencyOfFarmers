package scau.zxck.service.market;

import java.util.List;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.Login;
import scau.zxck.entity.market.UnionStaff;

/**
 * Created by suruijia on 2016/1/29.
 */
public interface ILoginService {

    Login findOne(String id) throws BaseException;

}
