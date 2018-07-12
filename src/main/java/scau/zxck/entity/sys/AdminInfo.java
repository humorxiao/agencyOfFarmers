package scau.zxck.entity.sys;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

@Table(name = "admin_info")
public class AdminInfo extends Unique {
  @Column(name = "admin_password")
  private String admin_password;
  @Column(name = "admin_name")
  private String admin_name;
  @Column(name = "admin_cell")
  private String admin_cell;
  @Column(name = "admin_email")
  private String admin_email;
  @Column(name = "remark")
  private String remark;

  public String getAdmin_password() {
    return admin_password;
  }

  public void setAdmin_password(String admin_password) {
    this.admin_password = admin_password;
  }

  public String getAdmin_name() {
    return admin_name;
  }

  public void setAdmin_name(String admin_name) {
    this.admin_name = admin_name;
  }

  public String getAdmin_cell() {
    return admin_cell;
  }

  public void setAdmin_cell(String admin_cell) {
    this.admin_cell = admin_cell;
  }

  public String getAdmin_email() {
    return admin_email;
  }

  public void setAdmin_email(String admin_email) {
    this.admin_email = admin_email;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
