package scau.zxck.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.*;
import scau.zxck.dao.sys.AdminInfoDao;
import scau.zxck.dao.sys.UserInfoDao;
import scau.zxck.entity.market.*;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.entity.sys.UserInfo;

import java.sql.Date;
import java.sql.Timestamp;

public class DataManagerUtil {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private AdminInfoDao adminInfoDao;
    @Autowired
    private CartInfoDao cartInfoDao;
    @Autowired
    private GoodsInfoDao goodsInfoDao;
    @Autowired
    private GoodsLogDao goodsLogDao;
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private SignInLogDao signInLogDao;
    @Autowired
    private UnionGoodsInfoDao unionGoodsInfoDao;
    @Autowired
    private UnionInfoDao unionInfoDao;
    @Autowired
    private UnionNewsDao unionNewsDao;
    @Autowired
    private UnionProdInfoDao unionProdInfoDao;
    @Autowired
    private UnionStaffDao unionStaffDao;
    @Autowired
    private UserCollectionDao userCollectionDao;
    @Autowired
    private UserCommentsDao userCommentsDao;
    @Autowired
    private DeliveryAddressDao deliveryAddressDao;
    public boolean insertData(String tablename, JSONObject json) throws BaseException {
        Object o = new Object();
        if (tablename.equals("UserInfo")) {
            UserInfo temp = new UserInfo();
            temp.setUser_password(json.get("User_Password").toString());
            temp.setUser_name(json.get("User_Name").toString());
            temp.setUser_cell(json.get("User_Cell").toString());
            temp.setUser_email(json.get("User_Email").toString());
            temp.setUser_sex((int) Integer.parseInt(json.get("User_Sex").toString()));
            temp.setUser_regtime(Timestamp.valueOf(json.get("User_RegTime").toString()).toString());
            temp.setUser_realname(json.get("User_Realname").toString());
            temp.setUser_id(json.get("User_ID").toString());

            temp.setCart(new CartInfo());
            temp.setDeliveryaddress(new DeliveryAddress());

            o = temp;
            return DataInsertUtil.insert(userInfoDao,o);
        }



        if (tablename.equals("AdminInfo")) {
            AdminInfo temp = new AdminInfo();
            temp.setAdmin_password(json.get("Admin_Password").toString());
            temp.setAdmin_name(json.get("Admin_Name").toString());
            temp.setAdmin_cell(json.get("Admin_Cell").toString());
            temp.setAdmin_email(json.get("Admin_Email").toString());

            o = temp;
            return  DataInsertUtil.insert(adminInfoDao,o);
        }
        if (tablename.equals("CartInfo")) {
            CartInfo temp = new CartInfo();
            temp.setId(json.get("Cart_PK").toString());
            temp.setGoods_list("");
            temp.setGoods_num("");

            o = temp;
            return DataInsertUtil.insert(cartInfoDao,o);
        }
        if (tablename.equals("DeliveryAddress")) {
            DeliveryAddress temp = new DeliveryAddress();
            temp.setId(json.get("Deliv_PK").toString());
            temp.setDeliv_cell("");
            temp.setDeliv_name("");
            temp.setDeliv_address("");
            temp.setDeliv_zipcode("");

            o = temp;
            return DataInsertUtil.insert(deliveryAddressDao,o);
        }
        if (tablename.equals("GoodsInfo")) {
            GoodsInfo temp = new GoodsInfo();
            temp.setGoods_name(json.get("Goods_Name").toString());
            temp.setGoods_type((int) Integer.parseInt(json.get("Goods_Type").toString()));
            temp.setGoods_num((int) Integer.parseInt(json.get("Goods_Num").toString()));
            temp.setGoods_price((float) Float.parseFloat(json.get("Goods_Price").toString()));
            temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
            temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
            temp.setGoods_picture(json.get("Goods_Picture").toString());
            temp.setGoods_season((int) Integer.parseInt(json.get("Goods_Season").toString()));
            temp.setGoods_blossom(json.get("Goods_Blossom").toString());
            temp.setGoods_fruit(json.get("Goods_Fruit").toString());
            temp.setGoods_mature(json.get("Goods_Mature").toString());
            temp.setGoods_expiration(json.get("Goods_Expiration").toString());
            temp.setGoods_expiration(json.get("Goods_Reserve_1").toString());
            o = temp;
            DataInsertUtil.insert(goodsInfoDao,o);
        }
        if (tablename.equals("GoodsLog")) {
            GoodsLog temp = new GoodsLog();
            temp.setId(json.get("Goods_PK").toString());
            temp.setGoods_in((int) Integer.parseInt(json.get("Goods_In").toString()));
            temp.setGoods_out((int) Integer.parseInt(json.get("Goods_Out").toString()));
            temp.setGoods_pricechange((float) Float.parseFloat(json.get("Goods_PriceChange").toString()));
            temp.setGl_time(Timestamp.valueOf(json.get("GL_Time").toString()));

            o = temp;
            return DataInsertUtil.insert(goodsLogDao,o);
        }
        if (tablename.equals("OrderInfo")) {
            OrderInfo temp = new OrderInfo();
            temp.setUser_info_id(json.get("User_PK").toString());
            temp.setOrder_id(json.get("Order_ID").toString());
            temp.setOrder_no(json.get("Order_No").toString());
            temp.setGoods_list(json.get("Goods_List").toString());
            temp.setGoods_num(json.get("Goods_Num").toString());
            temp.setGoods_prices(json.get("Goods_Prices").toString());
            temp.setOrder_time(Timestamp.valueOf(json.get("Order_Time").toString()));
            temp.setOrder_ispay((boolean) json.get("Order_IsPay"));
            temp.setOrder_paytime(Timestamp.valueOf(json.get("Order_PayTime").toString()));
            temp.setOrder_payprice((float) Float.parseFloat(json.get("Order_PayPrice").toString()));
            temp.setOrder_state((int) Integer.parseInt(json.get("Order_State").toString()));
            temp.setOrder_tracknum(json.get("Order_TrackNum").toString());
            temp.setOrder_company(json.get("Order_Company").toString());
            temp.setOrder_website(json.get("Order_Website").toString());
            temp.setOrder_aftersale((int) Integer.parseInt(json.get("Order_Aftersale").toString()));
            temp.setOrder_reserve_1(json.get("Order_Reserve_1").toString());
            temp.setUserInfo(new UserInfo());

            o = temp;
            return DataInsertUtil.insert(orderInfoDao,o);
        }
        if (tablename.equals("SignInLog")) {

            SignInLog temp = new SignInLog();
            temp.setSignin_isadmin((boolean) json.get("SignIn_IsAdmin"));


            temp.setUser_info_id(json.get("User_PK").toString());
            temp.setAdmin_info_id(json.get("Admin_PK").toString());
            temp.setSignin_time(Timestamp.valueOf(json.get("SignIn_Time").toString()));


            o = temp;
            return DataInsertUtil.insert(signInLogDao,o);
        }
        if (tablename.equals("SignInLogUser")) {
            SignInLog temp = new SignInLog();
            temp.setSignin_isadmin((boolean) json.get("SignIn_IsAdmin"));
            temp.setUser_info_id(json.get("User_PK").toString());
            temp.setSignin_time(Timestamp.valueOf(json.get("SignIn_Time").toString()));
            o = temp;
            return DataInsertUtil.insert(signInLogDao,o);
        }
        if (tablename.equals("SignInLogAdmin")) {

            SignInLog temp = new SignInLog();
            temp.setSignin_isadmin((boolean) json.get("SignIn_IsAdmin"));
            temp.setAdmin_info_id(json.get("Admin_PK").toString());
            temp.setSignin_time(Timestamp.valueOf(json.get("SignIn_Time").toString()));
            o = temp;
            return DataInsertUtil.insert(signInLogDao,o);
        }



        if (tablename.equals("UnionInfo")) {
            UnionInfo temp = new UnionInfo();
            temp.setUnion_name(json.get("Union_Name").toString());
            temp.setUnion_master(json.get("Union_Master").toString());
            temp.setUnion_license(json.get("Union_License").toString());
            temp.setUnion_address(json.get("Union_Address").toString());
            temp.setUnion_establish(Date.valueOf(json.get("Union_Establish").toString()));
            temp.setUnion_asset((int) Integer.parseInt(json.get("Union_Asset").toString()));
            temp.setUnion_tele(json.get("Union_Tele").toString());
            temp.setUnion_cell(json.get("Union_Cell").toString());
            temp.setUnion_email(json.get("Union_Email").toString());
            char c = json.get("Union_Mark").toString().charAt(0);
            temp.setUnion_mark(c);

            o = temp;
            return DataInsertUtil.insert(unionInfoDao,o);
        }
        if (tablename.equals("UnionNews")) {
            UnionNews temp = new UnionNews();
            temp.setNews_title(json.get("News_Title").toString());
            temp.setNews_text(json.get("News_Text").toString());
            temp.setNews_time(Timestamp.valueOf(json.get("News_Time").toString()));
            temp.setNews_mark((int) Integer.parseInt(json.get("News_Mark").toString()));

            o = temp;
            return DataInsertUtil.insert(unionNewsDao,o);
        }
//     if(tablename.equals("UnionGoodsInfo")){
//     UnionGoodsInfo temp = new UnionGoodsInfo();
//     temp.setUnion_info_id(json.get("Union_PK").toString());
//     temp.setGoods_info_id(json.get("Goods_PK").toString());
//     temp.setHave_area((int)Integer.parseInt(json.get("Have_Area").toString()));
//     temp.setHave_num((int)Integer.parseInt(json.get("Have_Num").toString()));
//     temp.setHave_yield(json.get("Have_Yield").toString());
//     temp.setHave_time(json.get("Have_Time").toString());
//     temp.setHave_price(json.get("Have_Price").toString());
//
//     o = temp;
//     }
        if (tablename.equals("UnionStaff")) {
            UnionStaff temp = new UnionStaff();
            temp.setId(json.get("Union_PK").toString());
            temp.setStaff_name(json.get("Staff_Name").toString());
            temp.setStaff_sex((int) Integer.parseInt(json.get("Staff_Sex").toString()));
            temp.setStaff_birthday(Date.valueOf(json.get("Staff_Birthday").toString()));
            temp.setStaff_address(json.get("Staff_Address").toString());
            temp.setStaff_phone(json.get("Staff_Phone").toString());
            temp.setStaff_id(json.get("Staff_ID").toString());
            temp.setStaff_email(json.get("Staff_Email").toString());

            temp.setUnioninfo(new UnionInfo());

            o = temp;
            return DataInsertUtil.insert(unionStaffDao,o);
        }
        if (tablename.equals("UserCollection")) {
            UserCollection temp = new UserCollection();
            temp.setId(json.get("User_PK").toString());
            temp.setGoods_info_id(json.get("Goods_PK").toString());
            temp.setCollect_time(Timestamp.valueOf(json.get("Collect_Time").toString()));

            temp.setUserinfo(new UserInfo());
            temp.setGoodsinfo(new GoodsInfo());

            o = temp;
            return DataInsertUtil.insert(userCollectionDao,o);
        }
        if (tablename.equals("UserComments")) {
            UserComments temp = new UserComments();
            temp.setUser_info_id(json.get("User_PK").toString());
            temp.setGoods_info_id(json.get("Goods_PK").toString());
            temp.setComm_rank((int) Integer.parseInt(json.get("Comm_Rank").toString()));
            temp.setComm_text(json.get("Comm_Text").toString());
            temp.setComm_time(Timestamp.valueOf(json.get("Comm_Time").toString()));

            temp.setUserinfo(new UserInfo());
            temp.setGoodsinfo(new GoodsInfo());

            o = temp;
            return DataInsertUtil.insert(userCollectionDao, o);
        }
        return true;
    }
}
