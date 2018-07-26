package scau.zxck.web.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.DeliveryAddress;
import scau.zxck.utils.ToJSONString;

public class UserInfoActionTest {
    @Test
    public void test() throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        String s= ToJSONString.toJSON(mapper.writeValueAsString(new UserInfo2Test("100000#100001#100002#100003#100004#100005")));
        System.out.println(s);
    }

}
