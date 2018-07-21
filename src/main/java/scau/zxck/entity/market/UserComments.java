package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;
import scau.zxck.entity.sys.UserInfo;

import java.sql.Timestamp;

@Table(name = "user_comments")
public class UserComments extends Unique {
  @Column(name = "user_info_id")
  private String user_info_id;
  @Column(name = "goods_info_id")
  private String goods_info_id;
  @Column(name = "comm_rank")
  private int comm_rank;
  @Column(name = "comm_text")
  private String comm_text;
  @Column(name = "comm_time")
  private String comm_time;
  @Column(name = "comm_reserve_1")
  private String comm_reserve_1;
  @Column(name = "comm_reserve_2")
  private String comm_reserve_2;
  @Column(name = "remark")
  private String remark;

  private UserInfo userinfo;

  public UserComments() {
  }

  public UserComments(String user_info_id, String goods_info_id, int comm_rank, String comm_text, String comm_time, String comm_reserve_1, String comm_reserve_2, String remark) {

    this.user_info_id = user_info_id;
    this.goods_info_id = goods_info_id;
    this.comm_rank = comm_rank;
    this.comm_text = comm_text;
    this.comm_time = comm_time;
    this.comm_reserve_1 = comm_reserve_1;
    this.comm_reserve_2 = comm_reserve_2;
    this.remark = remark;
  }

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

  public int getComm_rank() {
    return comm_rank;
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

  public void setComm_rank(int comm_rank) {
    this.comm_rank = comm_rank;
  }

  public String getComm_text() {
    return comm_text;
  }

  public void setComm_text(String comm_text) {
    this.comm_text = comm_text;
  }

  public String getComm_time() {
    return comm_time;
  }

  public void setComm_time(String  comm_time) {
    this.comm_time = comm_time;
  }

  public String getComm_reserve_1() {
    return comm_reserve_1;
  }

  public void setComm_reserve_1(String comm_reserve_1) {
    this.comm_reserve_1 = comm_reserve_1;
  }

  public String getComm_reserve_2() {
    return comm_reserve_2;
  }

  public void setComm_reserve_2(String comm_reserve_2) {
    this.comm_reserve_2 = comm_reserve_2;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
