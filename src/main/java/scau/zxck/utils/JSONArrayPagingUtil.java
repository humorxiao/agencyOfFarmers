package scau.zxck.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONArrayPagingUtil {
    public static JSONArray JSONArrayPaging(JSONArray arr, JSONObject json) {
        JSONArray temparr = new JSONArray();
        JSONObject firstjson = new JSONObject();

        firstjson.put("Size", arr.size());

        if (arr.size() < json.getIntValue("NumPerPage")) {
            firstjson.put("PageNum", 1);
        } else {
            if (arr.size() % json.getIntValue("NumPerPage") == 0) {
                firstjson.put("PageNum", arr.size() / json.getIntValue("NumPerPage"));
            } else {
                firstjson.put("PageNum", (arr.size() / json.getIntValue("NumPerPage")) + 1);
            }
        }
        firstjson.put("NowPage", json.getIntValue("Page"));
        firstjson.put("NumPerPage", json.getIntValue("NumPerPage"));

        temparr.add(firstjson);
        for (int i = (json.getIntValue("Page") - 1) * json.getIntValue("NumPerPage"); i < arr
                .size(); i++) {
            temparr.add(arr.get(i));
            if (i >= json.getIntValue("Page") * json.getIntValue("NumPerPage") - 1)
                break;
        }

        return temparr;
    }
}
