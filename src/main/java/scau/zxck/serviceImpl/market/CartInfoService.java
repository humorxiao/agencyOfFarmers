package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.CartInfoDao;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.service.market.ICartInfoService;

@Service
public class CartInfoService implements ICartInfoService{
    @Autowired
    private CartInfoDao cartInfoDao;
    @Override
    public String add(CartInfo entity) throws BaseException {
        return cartInfoDao.add(entity);
    }
}
