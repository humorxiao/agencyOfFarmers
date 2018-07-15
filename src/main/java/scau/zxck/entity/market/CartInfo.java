package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

import javax.naming.Name;

@Table(name = "cart_info")
public class CartInfo extends Unique {
    @Column(name = "good_list")
    private String goods_list;
    @Column(name = "goods_num")
    private String goods_num;
    @Column(name = "cart_reserve_1")
    private String cart_reserve_1;
    @Column(name = "cart_reserve_2")
    private String cart_reserve_2;
    @Column(name = "remark")
    private String remark;

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

    public String getCart_reserve_1() {
        return cart_reserve_1;
    }

    public void setCart_reserve_1(String cart_reserve_1) {
        this.cart_reserve_1 = cart_reserve_1;
    }

    public String getCart_reserve_2() {
        return cart_reserve_2;
    }

    public void setCart_reserve_2(String cart_reserve_2) {
        this.cart_reserve_2 = cart_reserve_2;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
