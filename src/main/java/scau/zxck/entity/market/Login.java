package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

/**
 * 友情链接表 Created by suruijia on 2016/1/29.
 */
@Table(name = "login")
public class Login extends Unique {
  /**
   * 友情链接名
   */
  @Column(name = "username")
  private String username;
  /**
   * 链接
   */
  @Column(name = "password")
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}
