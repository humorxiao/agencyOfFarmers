package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

@Table(name = "delivery_address")
public class DeliveryAddress extends Unique {
  @Column(name = "deliv_cell")
  private String deliv_cell;
  @Column(name = "deliv_name")
  private String deliv_name;
  @Column(name = "deliv_address")
  private String deliv_address;
  @Column(name = "deliv_zipcode")
  private String deliv_zipcode;
  @Column(name = "remark")
  private String remark;

  public String getDeliv_cell() {
    return deliv_cell;
  }

  public DeliveryAddress() {
  }

  public DeliveryAddress(String deliv_cell, String deliv_name, String deliv_address, String deliv_zipcode, String remark) {

    this.deliv_cell = deliv_cell;
    this.deliv_name = deliv_name;
    this.deliv_address = deliv_address;
    this.deliv_zipcode = deliv_zipcode;
    this.remark = remark;
  }

  public void setDeliv_cell(String deliv_cell) {
    this.deliv_cell = deliv_cell;
  }

  public String getDeliv_name() {
    return deliv_name;
  }

  public void setDeliv_name(String deliv_name) {
    this.deliv_name = deliv_name;
  }

  public String getDeliv_address() {
    return deliv_address;
  }

  public void setDeliv_address(String deliv_address) {
    this.deliv_address = deliv_address;
  }

  public String getDeliv_zipcode() {
    return deliv_zipcode;
  }

  public void setDeliv_zipcode(String deliv_zipcode) {
    this.deliv_zipcode = deliv_zipcode;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
