package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

@Table(name = "union_goods_info")
public class UnionGoodsInfo extends Unique {
    @Column(name = "union_info_id")
    private String union_info_id;
    @Column(name = "goods_info_id")
    private String goods_info_id;

    public UnionGoodsInfo() {
    }

    public UnionGoodsInfo(String union_info_id, String goods_info_id, String union_name, String goods_name, float goods_price, String union_master, String union_cell, int goods_type, int goods_num) {

        this.union_info_id = union_info_id;
        this.goods_info_id = goods_info_id;
        this.union_name = union_name;
        this.goods_name = goods_name;
        this.goods_price = goods_price;
        this.union_master = union_master;
        this.union_cell = union_cell;
        this.goods_type = goods_type;
        this.goods_num = goods_num;
    }

    @Column(name = "union_name")
    private String union_name;
    @Column(name = "goods_name")
    private String goods_name;
    @Column(name = "goods_price")
    private float goods_price;
    @Column(name = "union_master")
    private String union_master;
    @Column(name = "union_cell")
    private String union_cell;
    @Column(name = "goods_type")
    private int goods_type;
    @Column(name = "goods_num")
    private int goods_num;

    public String getUnion_info_id() {
        return union_info_id;
    }

    public void setUnion_info_id(String union_info_id) {
        this.union_info_id = union_info_id;
    }

    public String getGoods_info_id() {
        return goods_info_id;
    }

    public void setGoods_info_id(String goods_info_id) {
        this.goods_info_id = goods_info_id;
    }

    public String getUnion_name() {
        return union_name;
    }

    public void setUnion_name(String union_name) {
        this.union_name = union_name;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public float getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(float goods_price) {
        this.goods_price = goods_price;
    }

    public String getUnion_master() {
        return union_master;
    }

    public void setUnion_master(String union_master) {
        this.union_master = union_master;
    }

    public String getUnion_cell() {
        return union_cell;
    }

    public void setUnion_cell(String union_cell) {
        this.union_cell = union_cell;
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
}
