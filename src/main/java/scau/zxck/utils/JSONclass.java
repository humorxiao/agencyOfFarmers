package scau.zxck.utils;

import com.alibaba.fastjson.JSON;

public class JSONclass {
    public static String jsonStr(Object object){
        String str=JSON.toJSON(object).toString();
       return str;
    }
}
