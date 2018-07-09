package scau.zxck.base.dao.entity;

import scau.zxck.base.dao.constants.EntityColumnType;

/**
 * Created by lishunpeng on 2015/11/12.
 */
public class EntityColumn {
    private String name;
    private Object value;
    private EntityColumnType type;

    public EntityColumn(String name, Object value, EntityColumnType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public EntityColumnType getType() {
        return type;
    }
}
