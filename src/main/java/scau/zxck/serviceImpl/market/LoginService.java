package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.LoginDao;
import scau.zxck.entity.market.Login;
import scau.zxck.service.market.ILoginService;

import java.util.List;


/**
 * Created by suruijia on 2016/1/29.
 */
@Service
public class LoginService implements ILoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public Login findOne(String id) throws BaseException {
        return loginDao.findById(id);
    }

}
