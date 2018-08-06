package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.dao.market.GoodsInfoDao;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.utils.FlushWriteUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class CheckLoginRankAction {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "checkLoginRank", method = RequestMethod.POST)
//  @Test
    public void checkLoginRank(String jsonStr, HttpServletResponse response) throws Exception {
        JSONObject data = JSONObject.parseObject(jsonStr);
        String r = "";
        if (session.getAttribute("isAdmin") != null) {
            if ((boolean) session.getAttribute("isAdmin")) {
                r = "{\"status\":2}";//管理员
            } else {
                r = "{\"status\":1}";//用户
            }
        } else {
            r = "{\"status\":0}";//游客
        }
        FlushWriteUtil.flushWrite(response,r);
    }
}

