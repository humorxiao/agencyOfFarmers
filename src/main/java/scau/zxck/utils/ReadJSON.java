package scau.zxck.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public class ReadJSON {
//    @Autowired
//    private HttpServletRequest request;
    public JSONObject readJson(HttpServletRequest request) throws Exception{
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        JSONObject data = JSONObject.parseObject(wholeStr);
        return data;
    }
//    public static JSONObject readJSONStr() throws Exception{
//        ReadJSON readJSON=new ReadJSON();
//        return readJSON.readJson();
//    }
}
