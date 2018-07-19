package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

import java.sql.Timestamp;

@Table(name = "goods_log")
public class GoodsLog extends Unique {
  @Column(name = "goods_info_id")
  private String goods_info_id;
  @Column(name = "goods_in")
  private int goods_in;
  @Column(name = "goods_out")
  private int goods_out;
  @Column(name = "goods_pricechange")
  private float goods_pricechange;
  @Column(name = "gl_time")
  private String gl_time;
  @Column(name = "gl_reserve_1")
  private String gl_reserve_1;
  @Column(name = "gl_reserve_2")
  private String gl_reserve_2;
  @Column(name = "remark")
  private String remark;
  private GoodsInfo goodsinfo;

  public GoodsInfo getGoodsinfo() {
    return goodsinfo;
  }

  public void setGoodsinfo(GoodsInfo goodsinfo) {
    this.goodsinfo = goodsinfo;
  }

  public String getGoods_info_id() {
    return goods_info_id;
  }

  public void setGoods_info_id(String goods_info_id) {
    this.goods_info_id = goods_info_id;
  }

  public int getGoods_in() {
    return goods_in;
  }

  public void setGoods_in(int goods_in) {
    this.goods_in = goods_in;
  }

  public int getGoods_out() {
    return goods_out;
  }

  public void setGoods_out(int goods_out) {
    this.goods_out = goods_out;
  }

  public float getGoods_pricechange() {
    return goods_pricechange;
  }

  public void setGoods_pricechange(float goods_pricechange) {
    this.goods_pricechange = goods_pricechange;
  }

  public String getGl_time() {
    return gl_time;
  }

  public void setGl_time(String gl_time) {
    this.gl_time = gl_time;
  }

  public String getGl_reserve_1() {
    return gl_reserve_1;
  }

  public void setGl_reserve_1(String gl_reserve_1) {
    this.gl_reserve_1 = gl_reserve_1;
  }

  public String getGl_reserve_2() {
    return gl_reserve_2;
  }

  public void setGl_reserve_2(String gl_reserve_2) {
    this.gl_reserve_2 = gl_reserve_2;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
