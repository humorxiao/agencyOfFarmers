package scau.zxck.entity.sys;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

@Table(name = "system_user_info")
public class SystemUserInfo extends Unique {
    @Column(name = "system_user_password")
    private String password;
    @Column(name = "system_user_name")
    private String name;
    @Column(name = "remark")
    private String remark;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
