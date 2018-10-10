package scau.zxck.web.test;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;


public class SystemUserInfotest{
  private String id;
    private String system_user_password;

    private String system_user_name;

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

  public void setSystem_user_username(String system_user_name) {
    this.system_user_name = system_user_name;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SystemUserInfotest( String id,String system_user_password, String system_user_name, String remark) {

    this.id = id;
    this.system_user_password = system_user_password;
    this.system_user_name = system_user_name;
    this.remark = remark;

  }
}
