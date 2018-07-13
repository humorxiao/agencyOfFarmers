package scau.zxck.service.market;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
import java.util.List;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.Login;
import scau.zxck.entity.market.UnionStaff;
=======
=======
>>>>>>> Stashed changes
import scau.zxck.base.dao.utils.Page;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.Link;
import scau.zxck.entity.market.Login;

import java.util.List;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

/**
 * Created by suruijia on 2016/1/29.
 */
public interface ILoginService {

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    Login findOne(String id) throws BaseException;
=======

    Login findOne(String id) throws BaseException;
    String add(Login entity) throws BaseException;
>>>>>>> Stashed changes
=======

    Login findOne(String id) throws BaseException;
    String add(Login entity) throws BaseException;
>>>>>>> Stashed changes

}
