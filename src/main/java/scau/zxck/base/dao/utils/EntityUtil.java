package scau.zxck.base.dao.utils;

import scau.zxck.base.constants.ErrorCode;
import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.constants.EntityColumnType;
import scau.zxck.base.dao.entity.EntityColumn;
import scau.zxck.base.exception.BaseException;
import scau.zxck.base.utils.DateUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实体类工具类，用于查找字段和值
 * Created by lishunpeng on 2015/11/12.
 */
public class EntityUtil {
    private static final Map<String, Field> FIELD_CACHA_MAP = new ConcurrentHashMap<String, Field>();

    private static Field getField(Class<?> clazz, PropertyDescriptor pd) throws Exception {
        String key = clazz.getName() + "_" + pd.getName();
        Field field = FIELD_CACHA_MAP.get(key);
        if (field == null) {// 这个方法不加锁，初始化并发也没关系，无非多put几次
            //如果当前类找不到变量，则从父类找，大部分情况都是当前类能找到
            try {
                field = clazz.getDeclaredField(pd.getName());
            } catch (NoSuchFieldException e) {
                Class<?> cls = clazz.getSuperclass();
                if (cls != null) {
                    while (true) {
                        if (cls == null) {
                            break;
                        }
                        try {
                            field = cls.getDeclaredField(pd.getName());
                            break;
                        } catch (NoSuchFieldException ex) {
                            cls = cls.getSuperclass();
                        }
                    }
                }
            }
            if (field == null) {
                throw new NoSuchFieldException();
            }
            FIELD_CACHA_MAP.put(key, field);
        }
        return field;
    }

    public static <T> List<EntityColumn> getColumns(T entity, String... withoutColumnName) throws BaseException {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            List<EntityColumn> result = new ArrayList<EntityColumn>();
            for (PropertyDescriptor pd : pds) {
                Method method = pd.getReadMethod();

                if (method == null || pd.getWriteMethod() == null) { // ignore read-only fields
                    continue;
                }

                Object value = method.invoke(entity);
                if (value == null) {
                    continue;
                }

                Field field = getField(entity.getClass(), pd);

                Column column = field.getAnnotation(Column.class);
                if (column == null) {
                    continue;
                }
                String name = column.name();

                //过滤主键字段
                if (withoutColumnName != null) {
                    boolean isFinded = false;
                    for (String columnName : withoutColumnName) {
                        if (name.equals(columnName)) {
                            isFinded = true;
                            break;
                        }
                    }
                    if (isFinded) {
                        continue;
                    }
                }

                String type = field.getGenericType().toString();
                // 如果属性是String类型
                if ("class java.lang.String".equals(type)) {
                    result.add(new EntityColumn(name, value, EntityColumnType.STRING));
                } else if ("class java.util.Date".equals(type)) {
                    result.add(new EntityColumn(name, DateUtil.getSimpleDate((Date) value), EntityColumnType.DATE));
                } else {
                    result.add(new EntityColumn(name, value, EntityColumnType.OTHER));
                }
            }
            return result;
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.ENTITY_FIELD_ERROR);
        }
    }
}
