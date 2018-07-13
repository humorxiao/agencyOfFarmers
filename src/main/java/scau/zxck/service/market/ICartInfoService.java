package scau.zxck.service.market;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.CartInfo;

public interface ICartInfoService {
    String add(CartInfo entity) throws BaseException;
}
