package scau.zxck.serviceImpl.market;

<<<<<<< Updated upstream
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.LoginDao;
import scau.zxck.dao.market.UnionStaffDao;
import scau.zxck.entity.market.Login;
import scau.zxck.entity.market.UnionStaff;
import scau.zxck.service.market.ILoginService;
import scau.zxck.service.market.IUnionStaffService;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.LoginDao;
import scau.zxck.entity.market.Login;
import scau.zxck.service.market.ILoginService;

import java.util.List;
>>>>>>> Stashed changes


/**
 * Created by suruijia on 2016/1/29.
 */
@Service
public class LoginService implements ILoginService {
<<<<<<< Updated upstream
    private LoginDao loginDao=new LoginDao();
    @Override
    public Login findOne(String id) throws BaseException {
        return loginDao.findById(id);
    }

=======
    @Autowired
    private LoginDao loginDao;

    @Override
    public Login findOne(String id) throws BaseException {

        return loginDao.findById(id);
    }

    @Override
    public String add(Login entity) throws BaseException {
        return loginDao.add(entity);
    }
>>>>>>> Stashed changes
}
