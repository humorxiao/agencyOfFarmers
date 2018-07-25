package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

import java.sql.Timestamp;

@Table(name = "sign_in_log")
public class SignInLog extends Unique {
  public SignInLog() {
  }

  public SignInLog(boolean signin_isadmin, String user_info_id, String admin_info_id, String signin_time, String signin_reserve_1, String signin_reserve_2, String remark) {

    this.signin_isadmin = signin_isadmin;
    this.user_info_id = user_info_id;
    this.admin_info_id = admin_info_id;
    this.signin_time = signin_time;
    this.signin_reserve_1 = signin_reserve_1;
    this.signin_reserve_2 = signin_reserve_2;
    this.remark = remark;
  }

  @Column(name = "signin_isadmin")

  private boolean signin_isadmin;
  @Column(name = "user_info_id")
  private String user_info_id;
  @Column(name = "admin_info_id")
  private String admin_info_id;
  @Column(name = "signin_time")
  private String signin_time;
  @Column(name = "signin_reserve_1")
  private String signin_reserve_1;
  @Column(name = "signin_reserve_2")
  private String signin_reserve_2;
  @Column(name = "remark")
  private String remark;

  public String getUser_info_id() {
    return user_info_id;
  }

  public void setUser_info_id(String user_info_id) {
    this.user_info_id = user_info_id;
  }

  public String getAdmin_info_id() {
    return admin_info_id;
  }

  public void setAdmin_info_id(String admin_info_id) {
    this.admin_info_id = admin_info_id;
  }

  public boolean isSignin_isadmin() {
    return signin_isadmin;
  }

  public void setSignin_isadmin(boolean signin_isadmin) {
    this.signin_isadmin = signin_isadmin;
  }

  public String getSignin_time() {
    return signin_time;
  }

  public void setSignin_time(String signin_time) {
    this.signin_time = signin_time;
  }

  public String getSignin_reserve_1() {
    return signin_reserve_1;
  }

  public void setSignin_reserve_1(String signin_reserve_1) {
    this.signin_reserve_1 = signin_reserve_1;
  }

  public String getSignin_reserve_2() {
    return signin_reserve_2;
  }

  public void setSignin_reserve_2(String signin_reserve_2) {
    this.signin_reserve_2 = signin_reserve_2;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


}
