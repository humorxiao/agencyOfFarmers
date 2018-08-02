package scau.zxck.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class ReadJSON {
    @Autowired
    private HttpServletRequest request;
    private JSONObject readJson() throws Exception{
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        JSONObject data = JSONObject.parseObject(wholeStr);
        return data;
    }
    public static JSONObject readJSONStr() throws Exception{
        ReadJSON readJSON=new ReadJSON();
        return readJSON.readJson();
    }
}
