package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

@Table(name = "goods_info")
public class GoodsInfo extends Unique {
    private String goods_PK;
  @Column(name = "goods_name")
  private String goods_name;
  @Column(name = "goods_type")
  private int goods_type;
  @Column(name = "goods_num")
  private int goods_num;
  @Column(name = "goods_price")
  private float goods_price;
  @Column(name = "goods_mark")
  private char goods_mark;
  @Column(name = "goods_show")
  private char goods_show;
  @Column(name = "goods_picture")
  private String goods_picture;
  @Column(name = "goods_season")
  private int goods_season;
  @Column(name = "goods_blossom")
  private String goods_blossom;
  @Column(name = "goods_fruit")
  private String goods_fruit;
  @Column(name = "goods_mature")
  private String goods_mature;
  @Column(name = "goods_expiration")
  private String goods_expiration;
  @Column(name = "goods_reserve_1")
  private String goods_reserve_1;
  @Column(name = "goods_reserve_2")
  private String goods_reserve_2;
  @Column(name = "remark")
  private String remark;

<<<<<<< HEAD
    public GoodsInfo(char goods_mark) {
        this.goods_mark = goods_mark;
    }
=======
  public GoodsInfo() {
  }

  public GoodsInfo(String goods_name, int goods_type, int goods_num, float goods_price, char goods_mark, char goods_show, String goods_picture, int goods_season, String goods_blossom, String goods_fruit, String goods_mature, String goods_expiration, String goods_reserve_1, String goods_reserve_2, String remark) {
    this.goods_name = goods_name;
    this.goods_type = goods_type;
    this.goods_num = goods_num;
    this.goods_price = goods_price;
    this.goods_mark = goods_mark;
    this.goods_show = goods_show;
    this.goods_picture = goods_picture;
    this.goods_season = goods_season;
    this.goods_blossom = goods_blossom;
    this.goods_fruit = goods_fruit;
    this.goods_mature = goods_mature;
    this.goods_expiration = goods_expiration;
    this.goods_reserve_1 = goods_reserve_1;
    this.goods_reserve_2 = goods_reserve_2;
    this.remark = remark;
  }
>>>>>>> 785363630ee1a153e9bb320a4195d3d6983177bf

    public GoodsInfo(int goods_type) {
        this.goods_type = goods_type;
    }

    public String getGoods_PK() {
        return goods_PK;
    }

    public void setGoods_PK(String goods_PK) {
        this.goods_PK = goods_PK;
    }
//
//    public GoodsInfo(String goods_PK) {
//        this.goods_PK = goods_PK;
//    }

    public GoodsInfo(String goods_name) {
        this.goods_name = goods_name;
    }

    public GoodsInfo() {
    }

   public String getGoods_name() {
    return goods_name;
  }

  public void setGoods_name(String goods_name) {
    this.goods_name = goods_name;
  }

    public int getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(int goods_type) {
        this.goods_type = goods_type;
    }

    public int getGoods_num() {
    return goods_num;
  }

  public void setGoods_num(int goods_num) {
    this.goods_num = goods_num;
  }

  public float getGoods_price() {
    return goods_price;
  }

  public void setGoods_price(float goods_price) {
    this.goods_price = goods_price;
  }

  public char getGoods_mark() {
    return goods_mark;
  }

  public void setGoods_mark(char goods_mark) {
    this.goods_mark = goods_mark;
  }

  public char getGoods_show() {
    return goods_show;
  }

  public void setGoods_show(char goods_show) {
    this.goods_show = goods_show;
  }

  public String getGoods_picture() {
    return goods_picture;
  }

  public void setGoods_picture(String goods_picture) {
    this.goods_picture = goods_picture;
  }

  public int getGoods_season() {
    return goods_season;
  }

  public void setGoods_season(int goods_season) {
    this.goods_season = goods_season;
  }

  public String getGoods_blossom() {
    return goods_blossom;
  }

  public void setGoods_blossom(String goods_blossom) {
    this.goods_blossom = goods_blossom;
  }

  public String getGoods_fruit() {
    return goods_fruit;
  }

  public void setGoods_fruit(String goods_fruit) {
    this.goods_fruit = goods_fruit;
  }

  public String getGoods_mature() {
    return goods_mature;
  }

  public void setGoods_mature(String goods_mature) {
    this.goods_mature = goods_mature;
  }

  public String getGoods_expiration() {
    return goods_expiration;
  }

  public void setGoods_expiration(String goods_expiration) {
    this.goods_expiration = goods_expiration;
  }

  public String getGoods_reserve_1() {
    return goods_reserve_1;
  }

  public void setGoods_reserve_1(String goods_reserve_1) {
    this.goods_reserve_1 = goods_reserve_1;
  }

  public String getGoods_reserve_2() {
    return goods_reserve_2;
  }

  public void setGoods_reserve_2(String goods_reserve_2) {
    this.goods_reserve_2 = goods_reserve_2;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


}
