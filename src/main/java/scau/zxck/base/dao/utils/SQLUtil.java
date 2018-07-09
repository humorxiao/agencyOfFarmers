package scau.zxck.base.dao.utils;

import scau.zxck.base.dao.constants.EntityColumnType;
import scau.zxck.base.dao.entity.EntityColumn;
import scau.zxck.base.exception.BaseException;
import scau.zxck.base.utils.CharacterFilterUtil;

import java.util.List;

/**
 * SQL工具类
 * Created by lishunpeng on 2015/11/12.
 */
public class SQLUtil {
    /**
     * 用于构造SQL语句<br/>
     * 内容为column_name='value' or column_name=number
     */
    protected static <T> String createQLByColumns(T entity, String... withoutColumnName) throws BaseException {
        List<EntityColumn> columns = EntityUtil.getColumns(entity, withoutColumnName);
        if (columns.size() != 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0, len = columns.size(); i < len; i++) {
                EntityColumnType type = columns.get(i).getType();
                builder.append(columns.get(i).getName()).append("=");
                if (EntityColumnType.STRING.equals(type) || EntityColumnType.DATE.equals(type)) {
                    builder.append(CharacterFilterUtil.filter(columns.get(i).getValue().toString()));
                } else {
                    builder.append(columns.get(i).getValue());
                }
                if (i < len - 1) {
                    builder.append(",");
                }
            }
            return builder.toString();
        }
        return "";
    }

    public static <T> String createQLByColumnsWithID(T entity) throws BaseException {
        return createQLByColumns(entity);
    }

    public static <T> String createQLByColumnsWithoutID(T entity) throws BaseException {
        return createQLByColumns(entity, "id");
    }
}
