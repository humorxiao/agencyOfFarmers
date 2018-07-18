package scau.zxck.service.market;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UnionInfo;

import java.util.List;

/**
 * Created by suruijia on 2016/1/29.
 */
public interface IUnionInfoService {

    UnionInfo findOne(String id) throws BaseException;

    void updateUnionInfo(UnionInfo unionStaff) throws BaseException;

    void deleteUnionInfo(String id) throws BaseException;

    String addUnionInfo(UnionInfo unionInfo) throws BaseException;

    List<UnionInfo> listUnionInfo() throws BaseException;
    void update(UnionInfo unionInfo,Conditions condition )throws BaseException;
}
