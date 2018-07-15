package scau.zxck.service.market;

import org.springframework.beans.factory.annotation.Autowired;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.CartInfo;

public interface ICartInfoService {
    CartInfo findById(String id) throws BaseException;
}
