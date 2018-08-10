package scau.zxck.serviceImpl.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.NodeInfoDao;
import scau.zxck.entity.market.NodeInfo;
import scau.zxck.service.market.INodeInfoService;

import java.util.List;

@Service
public class NodeInfoService implements INodeInfoService {
    @Autowired
    private NodeInfoDao nodeInfoDao;

    @Override
    public <V> List<V> list(Conditions conditions) throws BaseException {
        return nodeInfoDao.list(conditions);
    }

    @Override
    public NodeInfo findById(String id) throws BaseException {
        return nodeInfoDao.findById(id);
    }

    @Override
    public void deleteByIds(Object... ids) throws BaseException {
        nodeInfoDao.deleteByIds(ids);
    }

    @Override
    public void deleteByEntity(NodeInfo entity) throws BaseException {
        nodeInfoDao.deleteByEntity(entity);
    }

    @Override
    public <V> List<V> listAll() throws BaseException {
        return nodeInfoDao.listAll();
    }

    @Override
    public String add(NodeInfo entity) throws BaseException {
        return nodeInfoDao.add(entity);
    }
}
