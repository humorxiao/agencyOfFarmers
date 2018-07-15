package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.DeliveryAddressDao;
import scau.zxck.entity.market.DeliveryAddress;
import scau.zxck.service.market.IDeliveryAddressService;
@Service
public class DeliveryAddressService implements IDeliveryAddressService {
    @Autowired
    private DeliveryAddressDao deliveryAddressDao;
    @Override
    public String add(DeliveryAddress entity) throws BaseException {
        return deliveryAddressDao.add(entity);
    }
}
