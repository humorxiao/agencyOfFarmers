package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

import java.sql.Date;

@Table(name = "union_info")
public class UnionInfo extends Unique {
  @Column(name = "union_name")
  private String union_name;
  @Column(name = "union_master")
  private String union_master;
  @Column(name = "union_license")
  private String union_license;
  @Column(name = "union_address")
  private String union_address;
  @Column(name = "union_establish")
  private String union_establish;

  public UnionInfo() {
  }

  public UnionInfo(String union_name, String union_master, String union_license, String union_address, String union_establish, int union_asset, String union_tele, String union_cell, String union_email, char union_mark, String remark) {

    this.union_name = union_name;
    this.union_master = union_master;
    this.union_license = union_license;
    this.union_address = union_address;
    this.union_establish = union_establish;
    this.union_asset = union_asset;
    this.union_tele = union_tele;
    this.union_cell = union_cell;
    this.union_email = union_email;
    this.union_mark = union_mark;
    this.remark = remark;
  }

  @Override
  public String toString() {
    return "UnionInfo{" +
            "union_name='" + union_name + '\'' +
            ", union_master='" + union_master + '\'' +
            ", union_license='" + union_license + '\'' +
            ", union_address='" + union_address + '\'' +
            ", union_establish=" + union_establish +
            ", union_asset=" + union_asset +
            ", union_tele='" + union_tele + '\'' +
            ", union_cell='" + union_cell + '\'' +
            ", union_email='" + union_email + '\'' +
            ", union_mark=" + union_mark +
            ", remark='" + remark + '\'' +
            '}';
  }

  @Column(name = "union_asset")
  private int union_asset;
  @Column(name = "union_tele")
  private String union_tele;
  @Column(name = "union_cell")
  private String union_cell;
  @Column(name = "union_email")
  private String union_email;
  @Column(name = "union_mark")
  private char union_mark;
  @Column(name = "reamrk")
  private String remark;

  public String getUnion_name() {
    return union_name;
  }

  public void setUnion_name(String union_name) {
    this.union_name = union_name;
  }

  public String getUnion_master() {
    return union_master;
  }

  public void setUnion_master(String union_master) {
    this.union_master = union_master;
  }

  public String getUnion_license() {
    return union_license;
  }

  public void setUnion_license(String union_license) {
    this.union_license = union_license;
  }

  public String getUnion_address() {
    return union_address;
  }

  public void setUnion_address(String union_address) {
    this.union_address = union_address;
  }


  public String getUnion_establish() {
    return union_establish;
  }


  public void setUnion_establish(String union_establish) {
    this.union_establish = union_establish;
  }

  public int getUnion_asset() {
    return union_asset;
  }

  public void setUnion_asset(int union_asset) {
    this.union_asset = union_asset;
  }

  public String getUnion_tele() {
    return union_tele;
  }

  public void setUnion_tele(String union_tele) {
    this.union_tele = union_tele;
  }

  public String getUnion_cell() {
    return union_cell;
  }

  public void setUnion_cell(String union_cell) {
    this.union_cell = union_cell;
  }

  public String getUnion_email() {
    return union_email;
  }

  public void setUnion_email(String union_email) {
    this.union_email = union_email;
  }

  public char getUnion_mark() {
    return union_mark;
  }

  public void setUnion_mark(char union_mark) {
    this.union_mark = union_mark;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }



}
