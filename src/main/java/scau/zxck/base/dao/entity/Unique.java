package scau.zxck.base.dao.entity;

import scau.zxck.base.dao.annotation.Column;

import java.io.Serializable;

/**
 * 主键标识抽象类。在数据库表中需要用到主键的，在对应的类中，必须要扩展此抽象类
 * Created by lishunpeng on 2015/10/24.
 */
public abstract class Unique implements Serializable {
    @Column(name = "id")
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
