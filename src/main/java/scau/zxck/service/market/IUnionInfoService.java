package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;

import java.util.List;

public interface IUnionInfoService {
    <V> List<V> list(Conditions conditions) throws BaseException;
    void delete (Conditions conditions) throws BaseException;
}
