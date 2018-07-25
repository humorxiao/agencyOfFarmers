package scau.zxck.service.market;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsLog;

import java.util.List;

public interface IGoodsLogService {
    String add(GoodsLog entity) throws BaseException;
    <V> List<V> listAll() throws BaseException;
}
