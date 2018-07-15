package scau.zxck.service.market;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.DeliveryAddress;
import scau.zxck.entity.market.UserCollection;

public interface IDeliveryAddressService {
    String add(DeliveryAddress entity) throws BaseException;
}
