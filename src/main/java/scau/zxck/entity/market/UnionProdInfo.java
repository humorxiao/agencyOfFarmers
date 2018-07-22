package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

import java.io.Serializable;
import java.sql.Date;

@Table(name = "union")
public class UnionProdInfo extends Unique implements Serializable {
  @Column(name = "goods_info_id")
  private String goods_info_id;
  @Column(name = "have_area")
  private int have_area;

  public UnionProdInfo() {
  }

  public UnionProdInfo(String goods_info_id, int have_area, int have_num, String have_yield, String have_time, String have_price, char union_goods_mark, String remark) {
    this.goods_info_id = goods_info_id;
    this.have_area = have_area;
    this.have_num = have_num;
    this.have_yield = have_yield;
    this.have_time = have_time;
    this.have_price = have_price;
    this.union_goods_mark = union_goods_mark;
    this.remark = remark;
  }

  @Column(name = "have_num")
  private int have_num;
  @Column(name = "have_yield")
  private String have_yield;
  @Column(name = "have_time")
  private String have_time;
  @Column(name = "have_price")
  private String have_price;
  @Column(name = "union_goods_mark")
  private char union_goods_mark;
  @Column(name = "remark")
  private String remark;

  private UnionInfo unioninfo;

  private GoodsInfo goodsinfo;

  public String getGoods_info_id() {
    return goods_info_id;
  }

  public void setGoods_info_id(String goods_info_id) {
    this.goods_info_id = goods_info_id;
  }

  public UnionInfo getUnioninfo() {
    return unioninfo;
  }

  public void setUnioninfo(UnionInfo unioninfo) {
    this.unioninfo = unioninfo;
  }

  public GoodsInfo getGoodsinfo() {
    return goodsinfo;
  }

  public void setGoodsinfo(GoodsInfo goodsinfo) {
    this.goodsinfo = goodsinfo;
  }

  public int getHave_area() {
    return have_area;
  }

  public void setHave_area(int have_area) {
    this.have_area = have_area;
  }

  public int getHave_num() {
    return have_num;
  }

  public void setHave_num(int have_num) {
    this.have_num = have_num;
  }

  public String getHave_yield() {
    return have_yield;
  }

  public void setHave_yield(String have_yield) {
    this.have_yield = have_yield;
  }

  public String getHave_time() {
    return have_time;
  }

  public void setHave_time(String have_time) {
    this.have_time = have_time;
  }

  public String getHave_price() {
    return have_price;
  }

  public void setHave_price(String have_price) {
    this.have_price = have_price;
  }

  public char getUnion_goods_mark() {
    return union_goods_mark;
  }

  public void setUnion_goods_mark(char union_goods_mark) {
    this.union_goods_mark = union_goods_mark;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }



}
