package scau.zxck.base.dao;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import scau.zxck.base.constants.ErrorCode;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.SQLAdapter;
import scau.zxck.base.dao.entity.Unique;
import scau.zxck.base.dao.mybatis.Callback;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.dao.utils.Generator;
import scau.zxck.base.dao.utils.Page;
import scau.zxck.base.dao.utils.SQLUtil;
import scau.zxck.base.exception.BaseException;
import scau.zxck.base.utils.StringUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDao<T extends Unique> extends SqlSessionDaoSupport implements IBaseDao<T> {
    protected String namespace;
    protected Class<?> entityClass;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public BaseDao() {
        entityClass = getGenericClass(getClass());
    }

    public String add(T entity) throws BaseException {
        if (entity == null) {
            throw new BaseException(ErrorCode.ENTITY_NULL_POINTER);
        }
        if (StringUtil.isEmpty(entity.getId())) {
            entity.setId(Generator.INSTANCE.generateId());
        }
        StringBuilder builder = new StringBuilder();
        try {
            builder.append("INSERT INTO ")
                    .append(getTableName(entity.getClass())).append(" SET ")
                    .append(SQLUtil.createQLByColumnsWithID(entity));

            getSqlSession().insert(getMapperId("add"), new SQLAdapter(builder.toString()));
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.DB_STORE_ERROR);
        }
        return entity.getId();
    }

    public void update(T entity, Conditions conditions) throws BaseException {
        if (entity == null) {
            throw new BaseException(ErrorCode.ENTITY_NULL_POINTER);
        }
        String fragment = "";
        if (conditions != null) {
            fragment = conditions.toWhereSQL();
        }
        StringBuilder builder = new StringBuilder();
        try {
            builder.append("UPDATE ")
                    .append(getTableName(entity.getClass()))
                    .append(" SET ")
                    .append(SQLUtil.createQLByColumnsWithoutID(entity))
                    .append(fragment);

            getSqlSession().update(getMapperId("update"), new SQLAdapter(builder.toString()));
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.DB_STORE_ERROR);
        }
    }

    public void updateById(T entity) throws BaseException {
        if (entity == null) {
            throw new BaseException(ErrorCode.ENTITY_NULL_POINTER);
        }
        if (StringUtil.isEmpty(entity.getId())) {
            throw new BaseException(ErrorCode.ENTITY_ID_NULL_POINTER);
        }
        Conditions conditions = new Conditions();
        conditions.eq("id", entity.getId());
        this.update(entity, conditions);
    }

    public void delete(Conditions conditions) throws BaseException {
        String fragment = "";
        if (conditions != null) {
            fragment = conditions.toWhereSQL();
        }
        StringBuilder builder = new StringBuilder();
        try {
            builder.append("DELETE FROM ")
                    .append(getTableName(entityClass))
                    .append(fragment);

            getSqlSession().delete(getMapperId("delete"), new SQLAdapter(builder.toString()));
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.DB_STORE_ERROR);
        }
    }

    public void deleteByIds(Object... ids) throws BaseException {
        if(ids == null || ids.length < 1){
            throw new BaseException(ErrorCode.PARAMETER_NULL_POINTER);
        }
        Conditions conditions = new Conditions();
        conditions.in("id", ids);
        this.delete(conditions);
    }

    public void deleteByEntity(T entity) throws BaseException {
        if (entity == null) {
            throw new BaseException(ErrorCode.ENTITY_NULL_POINTER);
        }
        String whereSQL  = SQLUtil.createQLByColumnsWithoutID(entity);
        Conditions conditions = new Conditions();
        conditions.assignWhereSQL(whereSQL);
        this.delete(conditions);
    }

    public void deleteAll() throws BaseException {
        this.delete(null);
    }

    public T find(Conditions conditions) throws BaseException {
        StringBuilder builder = new StringBuilder();
        String fragment = "";
        if (conditions != null) {
            fragment = conditions.toWhereSQL();
        }
        builder.append("SELECT * ");
        builder.append(" FROM ")
                .append(getTableName((entityClass)))
                .append(fragment);
        try {
            return getSqlSession().selectOne(getMapperId("find"), new SQLAdapter(builder.toString()));
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.DB_STORE_ERROR);
        }
    }

    public T findByEntity(T entity) throws BaseException {
        if (entity == null) {
            throw new BaseException(ErrorCode.ENTITY_NULL_POINTER);
        }
        String whereSQL  = SQLUtil.createQLByColumnsWithoutID(entity);
        Conditions conditions = new Conditions();
        conditions.assignWhereSQL(whereSQL);
        return find(conditions);
    }

    public T findById(String id) throws BaseException {
        if(StringUtil.isEmpty(id)){
            throw new BaseException(ErrorCode.PARAMETER_NULL_POINTER);
        }
        Conditions conditions = new Conditions();
        conditions.eq("id", id);
        return find(conditions);
    }

    public <V> List<V> list(Conditions conditions) throws BaseException {
        return list(conditions, new String[]{});
    }
    public <V> List<V> list(Conditions conditions, int offset, int pageSize) throws BaseException {
        return list(conditions, offset, pageSize, new String[]{});
    }

    public <V> List<V> list(Conditions conditions, int offset, int pageSize, String... propertyName) throws BaseException {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        if (propertyName == null || propertyName.length < 1) {
            builder.append("*");
        } else {
            int len = propertyName.length - 1;
            for (int i = 0; i < len; i++) {
                builder.append(propertyName[i] + ",");
            }
            builder.append(propertyName[len]);
        }

        String fragment = "";
        if (conditions != null) {
            fragment = conditions.toWhereSQL();
        }

        builder.append(" FROM ")
                .append(getTableName((entityClass)))
                .append(fragment);
        builder.append(" LIMIT ")
                .append(offset)
                .append(",")
                .append(pageSize);
        try {
            return getSqlSession().selectList(getMapperId("list"), new SQLAdapter(builder.toString()));
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.DB_STORE_ERROR);
        }
    }

    public <V> List<V> list(Conditions conditions, String... propertyName) throws BaseException {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        if (propertyName == null || propertyName.length < 1) {
            builder.append("*");
        } else {
            int len = propertyName.length - 1;
            for (int i = 0; i < len; i++) {
                builder.append(propertyName[i] + ",");
            }
            builder.append(propertyName[len]);
        }

        String fragment = "";
        if (conditions != null) {
            fragment = conditions.toWhereSQL();
        }

        builder.append(" FROM ")
                .append(getTableName((entityClass)))
                .append(fragment);
        try {
            return getSqlSession().selectList(getMapperId("list"), new SQLAdapter(builder.toString()));
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.DB_STORE_ERROR);
        }
    }

    public <V> List<V> listByEntity(T entity) throws BaseException {
        return listByEntity(entity, new String[]{});
    }

    public <V> List<V> listByEntity(T entity, String... propertyName) throws BaseException {
        if (entity == null) {
            throw new BaseException(ErrorCode.ENTITY_NULL_POINTER);
        }
        String whereSQL  = SQLUtil.createQLByColumnsWithoutID(entity);
        Conditions conditions = new Conditions();
        conditions.assignWhereSQL(whereSQL);
        return list(conditions, propertyName);
    }

    public <V> List<V> listAll() throws BaseException {
        return list(null);
    }

    public <V> Page<V> page(Conditions conditions) throws BaseException {
        return page(conditions, new String[]{});
    }
    public <V> Page<V> page(Conditions conditions, int offset, int pageSize) throws BaseException {
        return page(conditions, offset, pageSize, new String[]{});
    }
    public <V> Page<V> page(Conditions conditions, String... propertyName) throws BaseException {
        long count = count(conditions);
        List<V> results = list(conditions, propertyName);
        return new Page(count, results, conditions.getPageSize());
    }
    public <V> Page<V> page(Conditions conditions, int offset, int pageSize, String... propertyName) throws BaseException {
        long count = count(conditions);
        List<V> results = list(conditions, offset, pageSize, propertyName);
        return new Page(count, results, pageSize);
    }

    public <V> Page<V> pageByEntity(T entity) throws BaseException {
        return pageByEntity(entity, new String[]{});
    }
    public <V> Page<V> pageByEntity(T entity, int offset, int pageSize) throws BaseException {
        return pageByEntity(entity, offset, pageSize, new String[]{});
    }
    public <V> Page<V> pageByEntity(T entity, String... propertyName) throws BaseException {
        if (entity == null) {
            throw new BaseException(ErrorCode.ENTITY_NULL_POINTER);
        }
        String whereSQL  = SQLUtil.createQLByColumnsWithoutID(entity);
        Conditions conditions = new Conditions();
        conditions.assignWhereSQL(whereSQL);
        return page(conditions, propertyName);
    }
    public <V> Page<V> pageByEntity(T entity, int offset, int pageSize, String... propertyName) throws BaseException {
        if (entity == null) {
            throw new BaseException(ErrorCode.ENTITY_NULL_POINTER);
        }
        String whereSQL  = SQLUtil.createQLByColumnsWithoutID(entity);
        Conditions conditions = new Conditions();
        conditions.assignWhereSQL(whereSQL);
        return page(conditions, offset, pageSize, propertyName);
    }

    public long count(Conditions conditions) throws BaseException {
        StringBuilder builder = new StringBuilder();
        String fragment = "";
        if (conditions != null) {
            fragment = conditions.toWhereSQL();
        }
        builder.append("SELECT COUNT(*) ");
        builder.append(" FROM ")
                .append(getTableName((entityClass)))
                .append(fragment);
        try {
            Object value = getSqlSession().selectOne(getMapperId("count"), new SQLAdapter(builder.toString()));
            if (value instanceof Long) {
                return (Long) value;
            }
            return 0;
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.DB_STORE_ERROR);
        }
    }

    @Override
    public <U> U execute(Callback<U> executor) {
        return executor.execute(getSqlSession().getConnection());
    }

    @Override
    public void executeUpdate(String id, String sql) throws BaseException {
        try {
            getSqlSession().update(getMapperId(id), new SQLAdapter(sql));
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.DB_STORE_ERROR);
        }
    }

    @Override
    public <V> List<V> executeList(String id, String sql) throws BaseException {
        try {
            return getSqlSession().selectList(getMapperId(id), new SQLAdapter(sql));
        } catch (Exception e) {
            throw new BaseException(e, ErrorCode.DB_STORE_ERROR);
        }
    }



    protected String getTableName(Class<?> entityClass) {
        return entityClass.getAnnotation(Table.class).name();
    }

    /**
     * 将SQL Mapping命名空间与给定的id名组合在一起。
     *
     * @param id id名
     * @return 组合了SqlMapping命名空间后的完整SqlMapping名
     */
    protected String getMapperId(String id) {
        return getDefaultNamespace() + SQLNAME_SEPARATOR + id;
    }

    protected String getDefaultNamespace() {
        if (StringUtil.isNotEmpty(namespace)) {
            return namespace;
        }
        namespace = getClass().getName();
        return namespace;
    }

    public static final String SQLNAME_SEPARATOR = ".";

    public static Class<?> getGenericClass(Class<?> clazz) {
        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            return ((Class<?>) p[0]);
        }
        return null;
    }
}
