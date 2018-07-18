package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

import java.sql.Date;

@Table(name = "union_staff")
public class UnionStaff extends Unique {
  @Column(name = "union_info_id")
  private String union_info_id;
  @Column(name = "staff_name")
  private String staff_name;
  @Column(name = "staff_sex")
  private int staff_sex;
  @Column(name = "staff_birthday")
  private String staff_birthday;
  @Column(name = "staff_address")
  private String staff_address;
  @Column(name = "staff_phone")
  private String staff_phone;
  @Column(name = "staff_id")
  private String staff_id;
  @Column(name = "staff_email")
  private String staff_email;
  @Column(name = "remark")
  private String remark;

  private UnionInfo unioninfo;


  public UnionInfo getUnioninfo() {
    return unioninfo;
  }

  public void setUnioninfo(UnionInfo unioninfo) {
    this.unioninfo = unioninfo;
  }

  public String getStaff_name() {
    return staff_name;
  }

  public void setStaff_name(String staff_name) {
    this.staff_name = staff_name;
  }

  public int getStaff_sex() {
    return staff_sex;
  }

  public void setStaff_sex(int staff_sex) {
    this.staff_sex = staff_sex;
  }

  public String getStaff_birthday() {
    return staff_birthday;
  }

  public void setStaff_birthday(String staff_birthday) {
    this.staff_birthday = staff_birthday;
  }

  public String getStaff_address() {
    return staff_address;
  }

  public void setStaff_address(String staff_address) {
    this.staff_address = staff_address;
  }

  public String getStaff_phone() {
    return staff_phone;
  }

  public void setStaff_phone(String staff_phone) {
    this.staff_phone = staff_phone;
  }

  public String getStaff_id() {
    return staff_id;
  }

  public void setStaff_id(String staff_id) {
    this.staff_id = staff_id;
  }

  public String getStaff_email() {
    return staff_email;
  }

  public void setStaff_email(String staff_email) {
    this.staff_email = staff_email;
  }

  public String getUnion_info_id() {
    return union_info_id;
  }

  public void setUnion_info_id(String union_info_id) {
    this.union_info_id = union_info_id;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
