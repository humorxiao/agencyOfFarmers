package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:43
 */
@Controller
@RequestMapping("/")
public class DeleteFileAction {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    @RequestMapping(value = "deleteFile",method = RequestMethod.POST)
    public void deleteFile(HttpServletResponse response) throws Exception{
        String r = "{\"status\":\"\",\"msg\":\"\"}";//返回的字符串
        request.setCharacterEncoding("utf-8");
        JSONObject data= ReadJSONUtil.readJSONStr(request);
        String paths=data.get("filePath").toString();
        String[] path=paths.split(";");
        for(int i=0;i<path.length;i++){
            File file = new File(session.getServletContext().getRealPath("/upload/")+path[i]);
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        }
        r = "{\"status\":\"1\",\"msg\":\"删除成功\"}";
        FlushWriteUtil.flushWrite(response,r);
    }
}
