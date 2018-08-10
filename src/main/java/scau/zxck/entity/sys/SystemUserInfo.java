package scau.zxck.entity.sys;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

@Table(name = "system_user_info")
public class SystemUserInfo extends Unique {
    @Column(name = "system_user_password")
    private String system_user_password;
    @Column(name = "system_user_name")
    private String system_user_name;
    @Column(name = "remark")
    private String remark;

    public String getSystem_user_password() {
        return system_user_password;
    }

    public void setSystem_user_password(String system_user_password) {
        this.system_user_password = system_user_password;
    }

    public String getSystem_user_name() {
        return system_user_name;
    }

    public void setSystem_user_name(String system_user_name) {
        this.system_user_name = system_user_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
