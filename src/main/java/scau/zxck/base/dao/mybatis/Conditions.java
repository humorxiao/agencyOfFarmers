package scau.zxck.base.dao.mybatis;

import scau.zxck.base.utils.CharacterFilterUtil;

import java.util.Date;

/**
 * Created by lishunpeng on 2015/10/24.
 */
public class Conditions {
    private StringBuilder whereConditions;
    private int pageSize;
    private int orderbyCount;
    private boolean whereFlag;
    private boolean orderbyFlag;

    public Conditions(){
        pageSize = 10;
        orderbyCount = 0;
        whereFlag = false;
        orderbyFlag = false;
        whereConditions =  new StringBuilder();
    }

    /**
     * 等于"="比较符
     */
    public Conditions eq(String propertyName, Object value){
        addWhere();
        whereConditions.append(propertyName + " = " + filter(value));
        return this;
    }

    /**
     * 模糊"like"比较符
     */
    public Conditions like(String propertyName, Object value){
        addWhere();
        whereConditions.append(propertyName + " like " + filter(value));
        return this;
    }

    /**
     * 不等于"<>"比较符
     */
    public Conditions ne(String propertyName, Object value) {
        addWhere();
        whereConditions.append(propertyName + " <> " + filter(value));
        return this;
    }

    /**
     * 大于">"比较符
     */
    public Conditions gt(String propertyName, Object value) {
        addWhere();
        whereConditions.append(propertyName + " > " + filter(value));
        return this;
    }

    /**
     * 小于"<"比较符
     */
    public Conditions lt(String propertyName, Object value) {
        addWhere();
        whereConditions.append(propertyName + " < " + filter(value));
        return this;
    }

    /**
     * 小于等于"<="比较符
     */
    public Conditions le(String propertyName, Object value) {
        addWhere();
        whereConditions.append(propertyName + " <= " + filter(value));
        return this;
    }

    /**
     * 大于等于">="比较符
     */
    public Conditions ge(String propertyName, Object value) {
        addWhere();
        whereConditions.append(propertyName + " >= " + filter(value));
        return this;
    }

    /**
     * 间隔"between"比较符
     */
    public Conditions between(String propertyName, Object low, Object high) {
        addWhere();
        whereConditions.append(propertyName + " between " + filter(low) + " and " + filter(high));
        return this;
    }

    /**
     * 空 "is null"比较符
     */
    public Conditions isNull(String propertyName) {
        addWhere();
        whereConditions.append(propertyName + " is null ");
        return this;
    }

    /**
     * 非空"is not null"比较符
     */
    public Conditions isNotNull(String propertyName) {
        addWhere();
        whereConditions.append(propertyName + " is not null ");
        return this;
    }

    /**
     * limit分页符
     */
    public Conditions limit(int limit) {
        this.pageSize = limit;
        whereConditions.append(" LIMIT " + limit);
        return this;
    }
    /**
     * limit分页符
     */
    public Conditions limit(int start, int limit) {
        this.pageSize = limit;
        whereConditions.append(" LIMIT " + start + " , " + limit);
        return this;
    }

    /**
     * in操作符
     */
    public Conditions in(String propertyName, Object... keys) {
        addWhere();
        StringBuilder fragment = new StringBuilder();
        if (keys != null && keys.length > 0) {
            fragment.append(propertyName + " in (");
            int len = keys.length - 1;
            for (int i = 0; i < len; i++) {
                fragment.append(filter(keys[i]) + ",");
            }
            fragment.append(filter(keys[len]));
            fragment.append(")");
        }
        whereConditions.append(fragment.toString());
        return this;
    }

    /**
     * 左括号(
     */
    public Conditions leftBracket() {
        whereConditions.append(" ( ");
        return this;
    }

    /**
     * 右括号(
     */
    public Conditions rightBracket() {
        whereConditions.append(" ) ");
        return this;
    }

    /**
     * 与"And"逻辑操作符
     */
    public Conditions and() {
        whereConditions.append(" AND ");
        return this;
    }

    /**
     * 与"Or"逻辑操作符
     */
    public Conditions or() {
        whereConditions.append(" OR ");
        return this;
    }

    /**
     * ASC 逻辑操作符
     */
    public Conditions asc(String... propertyNames) {
        return orderby("ASC", propertyNames);
    }


    /**
     * DESC 逻辑操作符
     * ORDER BY 语句默认按照升序对记录进行排序。
     * 按照降序对记录进行排序，可以使用 DESC 关键字。
     */
    public Conditions desc(String... propertyNames) {
        return orderby("DESC", propertyNames);
    }

    private Conditions orderby(String type, String... propertyNames){
        if(propertyNames!= null && propertyNames.length > 0){
            addOrderBy();

            if(orderbyCount > 0){
                whereConditions.append(" , ");
            }

            int len = propertyNames.length - 1;
            for(int i = 0; i < len; i++){
                whereConditions.append(propertyNames[i] + ", ");
            }
            whereConditions.append(propertyNames[len] + " "+type+" ");
            orderbyCount++;
        }
        return this;
    }

    private void addWhere(){
        if(!whereFlag){
            whereConditions.append(" WHERE ");
            whereFlag = true;
        }
    }
    private void addOrderBy(){
        if(!orderbyFlag){
            whereConditions.append(" ORDER BY ");
            orderbyFlag = true;
        }
    }

    public void assignWhereSQL(String sql){
        sql = " WHERE " + sql.replace(",", " AND ");
        whereConditions.append(sql);
    }

    public String toWhereSQL() {
        return whereConditions.toString();
    }

    public int getPageSize() {
        return pageSize;
    }

    private static Object filter(Object value) {
        if (value instanceof String || value instanceof Date) {
            return CharacterFilterUtil.filter(value.toString());
        }
        return value;
    }
}
