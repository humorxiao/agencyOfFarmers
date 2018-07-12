package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;
import scau.zxck.entity.sys.UserInfo;

import java.sql.Timestamp;

@Table(name = "user_collection")
public class UserCollection extends Unique {
  @Column(name = "user_info_id")
  private String user_info_id;
  @Column(name = "goods_info_id")
  private String goods_info_id;
  @Column(name = "collect_time")
  private Timestamp collect_time;
  @Column(name = "collect_reserve_1")
  private String collect_reserve_1;
  @Column(name = "collect_reserve_2")
  private String collect_reserve_2;
  @Column(name = "remark")
  private String remark;


  private UserInfo userinfo;

  private GoodsInfo goodsinfo;


  public UserInfo getUserinfo() {
    return userinfo;
  }

  public void setUserinfo(UserInfo userinfo) {
    this.userinfo = userinfo;
  }

  public GoodsInfo getGoodsinfo() {
    return goodsinfo;
  }

  public void setGoodsinfo(GoodsInfo goodsinfo) {
    this.goodsinfo = goodsinfo;
  }

  public String getUser_info_id() {
    return user_info_id;
  }

  public void setUser_info_id(String user_info_id) {
    this.user_info_id = user_info_id;
  }

  public String getGoods_info_id() {
    return goods_info_id;
  }

  public void setGoods_info_id(String goods_info_id) {
    this.goods_info_id = goods_info_id;
  }

  public Timestamp getCollect_time() {
    return collect_time;
  }

  public void setCollect_time(Timestamp collect_time) {
    this.collect_time = collect_time;
  }

  public String getCollect_reserve_1() {
    return collect_reserve_1;
  }

  public void setCollect_reserve_1(String collect_reserve_1) {
    this.collect_reserve_1 = collect_reserve_1;
  }

  public String getCollect_reserve_2() {
    return collect_reserve_2;
  }

  public void setCollect_reserve_2(String collect_reserve_2) {
    this.collect_reserve_2 = collect_reserve_2;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
