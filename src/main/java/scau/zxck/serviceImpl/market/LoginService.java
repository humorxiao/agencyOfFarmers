package scau.zxck.serviceImpl.market;

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


/**
 * Created by suruijia on 2016/1/29.
 */
@Service
public class LoginService implements ILoginService {
    private LoginDao loginDao=new LoginDao();
    @Override
    public Login findOne(String id) throws BaseException {
        return loginDao.findById(id);
    }

}
