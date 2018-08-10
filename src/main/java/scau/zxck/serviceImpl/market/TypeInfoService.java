package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.TypeInfoDao;
import scau.zxck.entity.market.TypeInfo;
import scau.zxck.service.market.ITypeInfoService;

import java.util.List;

@Service
public class TypeInfoService implements ITypeInfoService {
    @Autowired
    private TypeInfoDao typeInfoDao;
    @Override
    public String add(TypeInfo entity) throws BaseException {
        return typeInfoDao.add(entity);
    }

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return typeInfoDao.list(conditions);
    }

    @Override
    public <V> List<V> listAll() throws BaseException {
        return typeInfoDao.listAll();
    }

    @Override
    public void deleteByEntity(TypeInfo entity) throws BaseException {
        typeInfoDao.deleteByEntity(entity);
    }

    @Override
    public void deleteByIds(Object... ids) throws BaseException {
        typeInfoDao.deleteByIds(ids);
    }

    @Override
    public TypeInfo findById(String id) throws BaseException {
        return typeInfoDao.findById(id);
    }

    @Override
    public void updateById(TypeInfo entity) throws BaseException {
        typeInfoDao.updateById(entity);
    }
}
