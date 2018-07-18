package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;
import scau.zxck.entity.sys.UserInfo;

import java.sql.Timestamp;

@Table(name = "order_info")
public class OrderInfo extends Unique {
  @Column(name = "user_info_id")
  private String user_info_id;
  @Column(name = "order_id")
  private String order_id;
  @Column(name = "order_no")
  private String order_no;
  @Column(name = "goods_list")
  private String goods_list;
  @Column(name = "goods_num")
  private String goods_num;
  @Column(name = "goods_prices")
  private String goods_prices;
  @Column(name = "order_time")
  private String order_time;
  @Column(name = "order_ispay")
  private boolean order_ispay;
  @Column(name = "order_paytime")
  private String order_paytime;
  @Column(name = "order_payprice")
  private float order_payprice;
  @Column(name = "order_state")
  private int order_state;
  @Column(name = "order_tracknum")
  private String order_tracknum;
  @Column(name = "order_company")
  private String order_company;
  @Column(name = "order_website")
  private String order_website;
  @Column(name = "order_aftersale")
  private int order_aftersale;
  @Column(name = "order_reserve_1")
  private String order_reserve_1;
  @Column(name = "order_reserve_2")
  private String order_reserve_2;
  @Column(name = "remark")
  private String remark;

  private UserInfo userInfo;

  public String getUser_info_id() {
    return user_info_id;
  }

  public void setUser_info_id(String user_info_id) {
    this.user_info_id = user_info_id;
  }

  public String getOrder_tracknum() {
    return order_tracknum;
  }

  public void setOrder_tracknum(String order_tracknum) {
    this.order_tracknum = order_tracknum;
  }

  public String getOrder_company() {
    return order_company;
  }

  public void setOrder_company(String order_company) {
    this.order_company = order_company;
  }

  public String getOrder_website() {
    return order_website;
  }

  public void setOrder_website(String order_website) {
    this.order_website = order_website;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }


  public String getOrder_id() {
    return order_id;
  }

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }


  public String getOrder_no() {
    return order_no;
  }

  public void setOrder_no(String order_no) {
    this.order_no = order_no;
  }


  public String getGoods_list() {
    return goods_list;
  }

  public void setGoods_list(String goods_list) {
    this.goods_list = goods_list;
  }

  public String getGoods_num() {
    return goods_num;
  }

  public void setGoods_num(String goods_num) {
    this.goods_num = goods_num;
  }

  public String getGoods_prices() {
    return goods_prices;
  }

  public void setGoods_prices(String goods_prices) {
    this.goods_prices = goods_prices;
  }

  public String getOrder_time() {
    return order_time;
  }

  public void setOrder_time(String order_time) {
    this.order_time = order_time;
  }

  public boolean isOrder_ispay() {
    return order_ispay;
  }

  public void setOrder_ispay(boolean order_ispay) {
    this.order_ispay = order_ispay;
  }

  public String getOrder_paytime() {
    return order_paytime;
  }

  public void setOrder_paytime(String order_paytime) {
    this.order_paytime = order_paytime;
  }

  public float getOrder_payprice() {
    return order_payprice;
  }

  public void setOrder_payprice(float order_payprice) {
    this.order_payprice = order_payprice;
  }

  public int getOrder_state() {
    return order_state;
  }

  public void setOrder_state(int order_state) {
    this.order_state = order_state;
  }

  public int getOrder_aftersale() {
    return order_aftersale;
  }

  public void setOrder_aftersale(int order_aftersale) {
    this.order_aftersale = order_aftersale;
  }

  public String getOrder_reserve_1() {
    return order_reserve_1;
  }

  public void setOrder_reserve_1(String order_reserve_1) {
    this.order_reserve_1 = order_reserve_1;
  }

  public String getOrder_reserve_2() {
    return order_reserve_2;
  }

  public void setOrder_reserve_2(String order_reserve_2) {
    this.order_reserve_2 = order_reserve_2;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }



}
