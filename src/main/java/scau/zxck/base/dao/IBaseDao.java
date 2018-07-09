package scau.zxck.base.dao;

import scau.zxck.base.dao.entity.Unique;
import scau.zxck.base.dao.mybatis.Callback;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.dao.utils.Page;
import scau.zxck.base.exception.BaseException;

import java.util.List;

public interface IBaseDao<T extends Unique> {

    /**
     * 新增对象，忽略value=null的值
     * (如果要添加的对象没有设置Id或者Id为空字符串或者是空格，则添加数据之前会调用 generateId()方法设置Id)
     *
     * @param entity 要新增的对象
     * @return 返回新增记录的主键
     */
    String add(T entity) throws BaseException;

    /**
     * 根据conditions条件进行更新，忽略entity中value=null的值以及主键Id的值。如果conditions为空，则视为没有限制条件
     *
     * @param entity    要持久化的对象
     * @param conditions 查询条件
     */
    void update(T entity, Conditions conditions) throws BaseException;

    /**
     * 根据entity中id的值作为查询条件进行更新，同时忽略entity中value=null的值。如果entity中的id为空，则报异常
     * @param entity
     * @throws BaseException
     */
    void updateById(T entity) throws BaseException;

    /**
     * 根据条件删除对象。conditions为null表示没有限制条件
     *
     * @param conditions 查询条件
     */
    void delete(Conditions conditions) throws BaseException;

    /**
     * 根据主键Id删除对象。
     * @param ids
     * @throws BaseException
     */
    void deleteByIds(Object... ids) throws BaseException;

    /**
     * 删除所有 慎用
     */
    void deleteAll() throws BaseException;

    /**
     * 根据查询对象conditions 查询一个对象，如果返回的结果多于一个对象将会抛出TooManyResultsException
     *
     * @param conditions 查询条件
     * @return
     */
    T find(Conditions conditions) throws BaseException;

    /**
     * 根据entity中value!=null的字段进行精确查询，忽略id属性。如果返回的结果多于一个对象将会抛出TooManyResultsException
     * @param entity
     * @return
     * @throws BaseException
     */
    T findByEntity(T entity) throws BaseException;

    /**
     * 通过实体主键id进行查询
     * @param id
     * @return
     * @throws BaseException
     */
    T findById(String id) throws BaseException;

    /**
     * 根据conditions对象 查询对象列表
     *
     * @param conditions 查询参数，如果为null则查询所有
     * @return 结果对象列表
     */
    <V> List<V> list(Conditions conditions) throws BaseException;
    <V> List<V> list(Conditions conditions, int offset, int pageSize) throws BaseException;
    /**
     * 根据conditions对象 查询对象列表，同时可指定返回的字段列表
     * @param conditions
     * @param propertyName
     * @param <V>
     * @return
     * @throws BaseException
     */
    <V> List<V> list(Conditions conditions, String... propertyName) throws BaseException;
    <V> List<V> list(Conditions conditions, int offset, int pageSize, String... propertyName) throws BaseException;
    /**
     * 根据entity对象中value!=null的属性进行查询
     * @param entity
     * @param <V>
     * @return
     * @throws BaseException
     */
    <V> List<V> listByEntity(T entity) throws BaseException;

    /**
     * 根据entity对象中value!=null的属性进行查询，
     * @param entity
     * @param <V>
     * @return
     * @throws BaseException
     */
    <V> List<V> listByEntity(T entity, String... propertyName) throws BaseException;

    /**
     * 查询所有记录列表
     *
     * @return 所有记录列表
     */
    <V> List<V> listAll() throws BaseException;

    /**
     * 根据查询条件进行分页查询
     * @param conditions
     * @param <V>
     * @return
     * @throws BaseException
     */
    <V> Page<V> page(Conditions conditions) throws BaseException;
    <V> Page<V> page(Conditions conditions, int offset, int pageSize) throws BaseException;

    /**
     * 根据查询条件进行分页查询，可指定返回字段值
     * @param conditions
     * @param <V>
     * @return
     * @throws BaseException
     */
    <V> Page<V> page(Conditions conditions, String... propertyName) throws BaseException;
    <V> Page<V> page(Conditions conditions, int offset, int pageSize, String... propertyName) throws BaseException;
    /**
     * 根据实体类中value!=null的属性进行查询
     * @param entity
     * @param <V>
     * @return
     * @throws BaseException
     */
    <V> Page<V> pageByEntity(T entity) throws BaseException;

    /**
     * 根据实体类中value!=null的属性进行查询，可指定返回字段值
     * @param entity
     * @param propertyName
     * @param <V>
     * @return
     * @throws BaseException
     */
    <V> Page<V> pageByEntity(T entity, String... propertyName) throws BaseException;

    /**
     * 根据条件查询记录数
     *
     * @param conditions
     */
    long count(Conditions conditions) throws BaseException;

    /**
     * 回调执行
     *
     * @param executor 查询参数
     * @return
     */
    <U> U execute(Callback<U> executor);

    /**
     * 执行更新语句
     *
     * @param id
     * @param sql
     */
    void executeUpdate(String id, String sql) throws BaseException;

    /**
     * 执行sql查询语句
     *
     * @param id
     * @param sql
     * @return
     */
    <V> List<V> executeList(String id, String sql) throws BaseException;
}
