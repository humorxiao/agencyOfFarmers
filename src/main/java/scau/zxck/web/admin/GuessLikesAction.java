package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.market.UserCollection;
import scau.zxck.service.market.IUserCollectService;
import scau.zxck.service.market.IGoodsInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class GuessLikesAction {
    @Autowired
    private IUserCollectService userCollectService;
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "guessYouLike", method = RequestMethod.POST)
    public String guessYouLike(String jsonStr) throws Exception {
        JSONArray jsonArray=new JSONArray();
        String r = "";
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        jsonStr=wholeStr;
        JSONObject data = JSONObject.parseObject(jsonStr);
        Conditions conditions = new Conditions();
        List list =
                userCollectService.list(conditions.eq("user_info_id", data.get("User_PK").toString()));
        ArrayList<UserCollection> arrayList=new ArrayList<>();
        if(list.size()>=4){
            for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();){
                arrayList.add((UserCollection)iter.next());
            }
            for(int i=0;i<arrayList.size();i++){
                JSONObject temp=new JSONObject();
                UserCollection userCollection=arrayList.get(i);
                String goods_pk=userCollection.getGoods_info_id();
                GoodsInfo goodsInfo=goodsInfoService.findById(goods_pk);
                temp.put("Goods_PK", goodsInfo.getId());
                temp.put("Goods_Name", goodsInfo.getGoods_name());
                temp.put("Goods_Type", goodsInfo.getGoods_type());
                temp.put("Goods_Num", goodsInfo.getGoods_num());
                temp.put("Goods_Price", goodsInfo.getGoods_price());
                temp.put("Goods_Mark", goodsInfo.getGoods_mark());
                temp.put("Goods_Show", goodsInfo.getGoods_show());
                temp.put("Goods_Picture", goodsInfo.getGoods_picture());
                jsonArray.add(temp);
            }
            r=jsonArray.toString();
        }
        else {
            int mark=1;
            Conditions conditions1=new Conditions();
            List list1=goodsInfoService.list(conditions1.eq("goods_show",mark));
            for(int i=0;i<4;i++){
                GoodsInfo goodsInfo=(GoodsInfo)list1.get(i);
                JSONObject temp=new JSONObject();
                temp.put("Goods_PK", goodsInfo.getId());
                temp.put("Goods_Name", goodsInfo.getGoods_name());
                temp.put("Goods_Type", goodsInfo.getGoods_type());
                temp.put("Goods_Num", goodsInfo.getGoods_num());
                temp.put("Goods_Price", goodsInfo.getGoods_price());
                temp.put("Goods_Mark", goodsInfo.getGoods_mark());
                temp.put("Goods_Show", goodsInfo.getGoods_show());
                temp.put("Goods_Picture", goodsInfo.getGoods_picture());
                temp.put("Goods_Reverse_1",goodsInfo.getGoods_reserve_1());
                jsonArray.add(temp);
            }
            r=jsonArray.toString();
        }
        System.out.println(r);
        return "success";
    }
}
