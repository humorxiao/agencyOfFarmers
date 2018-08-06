package scau.zxck.serviceImpl.market;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.CartInfoDao;

import scau.zxck.dao.market.UserkeDao;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.Userke;
import scau.zxck.entity.sys.User;
import scau.zxck.service.market.ICartInfoService;

import scau.zxck.service.market.IUserkeService;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/spring.xml"})
public class UserkeService implements IUserkeService {
    @Autowired
    private UserkeDao userkeDao;
    @Override

    public Userke findById(String id) throws BaseException {
        return userkeDao.findById(id);
    }
    @Test
public void keke()
{
  System.out.println("jsadfh");
}

}
