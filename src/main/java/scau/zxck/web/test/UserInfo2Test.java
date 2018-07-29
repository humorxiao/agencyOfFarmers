package scau.zxck.web.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.DeliveryAddress;
import scau.zxck.utils.ToJSONString;

public class UserInfo2Test {
  private String Goods_PK;

  public UserInfo2Test() {
  }

  public UserInfo2Test(String Goods_PK) {
    this.Goods_PK = Goods_PK;
  }

  public String getGoods_PK() {

    return Goods_PK;
  }

  public void setGoods_PK(String goods_PK) {
    Goods_PK = goods_PK;
  }
}
