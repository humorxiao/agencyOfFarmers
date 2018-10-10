package scau.zxck.entity.sys;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.DeliveryAddress;

@Table(name = "user_info")
public class UserInfo extends Unique {
  @Column(name = "user_password")
  private String user_password;
  @Column(name = "user_name")
  private String user_name;
  @Column(name = "user_cell")
  private String user_cell;
  @Column(name = "user_email")
  private String user_email;
  @Column(name = "user_sex")
  private int user_sex;
  @Column(name = "user_regtime")
  private String user_regtime;
  @Column(name = "user_realname")
  private String user_realname;
//  @Column(name = "user_id")
//  private String user_id;
  @Column(name = "user_mark")
  private String user_mark;
  @Column(name = "user_reserve_1")
  private String user_reserve_1;
  @Column(name = "user_reserve_2")
  private String user_reserve_2;
  @Column(name = "remark")
  private String remark;
  private CartInfo cart;
  private String newPassword;

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public UserInfo() {
  }

  public CartInfo getCart() {
    return cart;
  }

  public void setCart(CartInfo cart) {
    this.cart = cart;
  }

  public DeliveryAddress getDeliveryaddress() {
    return deliveryaddress;
  }

  public void setDeliveryaddress(DeliveryAddress deliveryaddress) {
    this.deliveryaddress = deliveryaddress;
  }

  private DeliveryAddress deliveryaddress;

  public String getUser_password() {
    return user_password;
  }

  public void setUser_password(String user_password) {
    this.user_password = user_password;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getUser_cell() {
    return user_cell;
  }

  public void setUser_cell(String user_cell) {
    this.user_cell = user_cell;
  }

  public String getUser_email() {
    return user_email;
  }

  public void setUser_email(String user_email) {
    this.user_email = user_email;
  }

  public int getUser_sex() {
    return user_sex;
  }

  public void setUser_sex(int user_sex) {
    this.user_sex = user_sex;
  }

  public String getUser_regtime() {
    return user_regtime;
  }

  public void setUser_regtime(String user_regtime) {
    this.user_regtime = user_regtime;
  }

  public String getUser_realname() {
    return user_realname;
  }

  public void setUser_realname(String user_realname) {
    this.user_realname = user_realname;
  }

//  public String getUser_id() {
//    return user_id;
//  }
//
//  public void setUser_id(String user_id) {
//    this.user_id = user_id;
//  }

  public String getUser_mark() {
    return user_mark;
  }

  public void setUser_mark(String user_mark) {
    this.user_mark = user_mark;
  }

  public String getUser_reserve_1() {
    return user_reserve_1;
  }

  public void setUser_reserve_1(String user_reserve_1) {
    this.user_reserve_1 = user_reserve_1;
  }

  public String getUser_reserve_2() {
    return user_reserve_2;
  }

  public void setUser_reserve_2(String user_reserve_2) {
    this.user_reserve_2 = user_reserve_2;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
