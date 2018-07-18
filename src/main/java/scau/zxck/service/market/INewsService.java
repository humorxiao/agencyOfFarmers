package scau.zxck.service.market;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UnionNews;

import java.util.List;

public interface INewsService {
    <V> List<V> listAll() throws BaseException;
    String add(UnionNews entity) throws BaseException;
}
