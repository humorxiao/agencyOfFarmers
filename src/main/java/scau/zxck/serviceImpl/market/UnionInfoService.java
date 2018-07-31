package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.UnionInfoDao;
import scau.zxck.dao.market.UnionStaffDao;
import scau.zxck.entity.market.UnionInfo;
import scau.zxck.service.market.IUnionInfoService;

import java.util.List;


/**
 * Created by suruijia on 2016/1/29.
 */
@Service
public class UnionInfoService implements IUnionInfoService {
    @Autowired
    private UnionInfoDao unionInfoDao;

    @Override
    public UnionInfo findOne(String id) throws BaseException {
        return unionInfoDao.findById(id);
    }

    @Override
    public void updateUnionInfo(UnionInfo unionInfo) throws BaseException {
        unionInfoDao.updateById(unionInfo);
    }

    @Override
    public void deleteUnionInfo(String id) throws BaseException {
        unionInfoDao.deleteByIds(id);
    }
    @Override
    public void update(UnionInfo unionInfo,Conditions condition )throws BaseException
    {
        unionInfoDao.update(unionInfo,condition);
    }

    @Override
    public void delete(Conditions conditions) throws BaseException {

    }

    @Override
    public String addUnionInfo(UnionInfo unionInfo) throws BaseException {
        return unionInfoDao.add(unionInfo);
    }

    @Override
    public List<UnionInfo> listUnionInfo() throws BaseException {
        return unionInfoDao.listAll();
    }

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return unionInfoDao.list(conditions);
    }


}
