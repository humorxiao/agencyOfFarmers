package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UnionStaff;

import java.util.List;

/**
 * Created by suruijia on 2016/1/29.
 */
public interface IUnionStaffService {

  <V> List<V> list(Conditions conditions) throws BaseException;

}
