package scau.zxck.utils;

import scau.zxck.base.dao.BaseDao;
import scau.zxck.base.dao.entity.Unique;
import scau.zxck.base.exception.BaseException;

public class DataInsertUtil {
    public static boolean insert(BaseDao dao,Object entity){
        try {
            dao.add((Unique) entity);
            return true;
        } catch (BaseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
