package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;
import scau.zxck.entity.sys.SystemUserInfo;

@Table(name = "system_user_log")
public class SystemUserLog extends Unique {
    @Column(name = "leave_time")
    private String leave_time;
    @Column(name = "login_time")
    private String login_time;
    @Column(name = "system_user_info_id")
    private String system_user_info_id;
    private SystemUserInfo systemUserInfo;

    public SystemUserInfo getSystemUserInfo() {
        return systemUserInfo;
    }

    public void setSystemUserInfo(SystemUserInfo systemUserInfo) {
        this.systemUserInfo = systemUserInfo;
    }

    public String getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(String leave_time) {
        this.leave_time = leave_time;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getSystem_user_info_id() {
        return system_user_info_id;
    }

    public void setSystem_user_info_id(String system_user_info_id) {
        this.system_user_info_id = system_user_info_id;
    }
}
