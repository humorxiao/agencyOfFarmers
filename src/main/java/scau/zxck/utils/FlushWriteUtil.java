package scau.zxck.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class FlushWriteUtil {
    public static void flushWrite(HttpServletResponse response,String r)throws Exception{
        PrintWriter out=response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }
}
