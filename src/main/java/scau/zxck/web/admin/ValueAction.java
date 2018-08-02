package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.NodeInfo;
import scau.zxck.entity.market.ValueItemInfo;
import scau.zxck.entity.sys.SystemUserInfo;
import scau.zxck.service.market.IValueItemService;
import scau.zxck.utils.ReadJSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("/")
public class ValueAction {
    @Autowired
    private IValueItemService valueItemService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "addValue", method = RequestMethod.POST)
    public void addValue(HttpServletResponse response) throws Exception {
        String jsonStr;
        String r = "";
        JSONObject data = ReadJSON.readJSONStr();
        String value = data.get("value").toString();
        String type_info_id = data.get("typeid").toString();
        String node_info_id = data.get("nodeid").toString();
        String recordingtime = data.get("recordingtime").toString();
        String note = data.get("note").toString();
        String system_info_id = session.getAttribute("loginUser").toString();
        ValueItemInfo valueItemInfo = new ValueItemInfo();
        valueItemInfo.setNode_info_id(node_info_id);
        valueItemInfo.setNote(note);
        valueItemInfo.setRecordingtime((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).parse(recordingtime).toString());
        valueItemInfo.setSystem_user_info_id(system_info_id);
        valueItemInfo.setType_info_id(type_info_id);
        PrintWriter out = response.getWriter();
        try {
            valueItemService.add(valueItemInfo);
            r = "{\"status\":\"1\",\"msg\":\"添加成功\",\"useridname\":\"" + valueItemInfo.getUser().getId()
                    + valueItemInfo.getUser().getName() + "\",\"typename\":\"" + valueItemInfo.getType().getName()
                    + "\",\"recordingtime\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(valueItemInfo.getRecordingtime()) +
                    "\"}";
            out.flush();
            out.write(r);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            r = "{\"status\":\"1\",\"msg\":\"添加失败\",\"useridname\":\"" + valueItemInfo.getUser().getId()
                    + valueItemInfo.getUser().getName() + "\",\"typename\":\"" + valueItemInfo.getType().getName()
                    + "\",\"recordingtime\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(valueItemInfo.getRecordingtime()) +
                    "\"}";
            out.flush();
            out.write(r);
            out.flush();
        }
    }

    @RequestMapping(value = "allValues", method = RequestMethod.POST)
    public void allValues(HttpServletResponse response) throws Exception {
        StringBuffer r = new StringBuffer();
        r.append("{\"status\":\"1\",\"ValueList\":[");//返回的字符串
        List list = valueItemService.listAll();
        for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
            ValueItemInfo n = (ValueItemInfo) iter.next();
            r.append("{\"itemid\":\"" + String.valueOf(n.getId()) + "\",\"value\":\"");
            r.append(n.getValue());
            r.append("\",\"nodeid\":\"");
            r.append(n.getNode().getId());
            r.append("\",\"note\":\"" + n.getNote()
                    + "\",\"typename\":\"" + n.getType().getName()
                    + "\",\"recordingtime\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(n.getRecordingtime())
                    + "\",\"useridname\":\"" + n.getUser().getId() + " " + n.getUser().getName()
                    + "\"},");
        }
        if (list.size() != 0) {
            r.deleteCharAt(r.length() - 1);//去掉最后一个“,”
        }
        r.append("]}");
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r.toString());
        out.flush();
    }

    @RequestMapping(value = "deleteValueItem", method = RequestMethod.POST)
    public void deleteValueItem(HttpServletResponse response) throws Exception {
        String r = "{\"status\":\"\",\"msg\":\"\"}";//返回的字符串
        JSONObject data = ReadJSON.readJSONStr();
        String id = data.get("itemid").toString();
        SystemUserInfo user = (SystemUserInfo) session.getAttribute("loginUser");
        valueItemService.deleteByIds(id);
        r = "{\"status\":\"1\",\"msg\":\"删除成功\"}";
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }

    @RequestMapping(value = "getMyValues", method = RequestMethod.POST)
    public void getMyValues(HttpServletResponse response) throws Exception {
        JSONObject data = ReadJSON.readJSONStr();
        System.out.println("test2");
        StringBuffer r = new StringBuffer();//返回的字符串
        r.append("{\"status\":\"1\",\"ValueList\":[");
        request.setCharacterEncoding("utf-8");
        int currentpage = 1;
        int rows = Integer.parseInt(data.get("rows").toString());
        if (data.get("page") != null) {
            currentpage = Integer.parseInt(data.get("page").toString());
        }
        String starttime = data.get("starttime").toString();
        String endtime = data.get("endtime").toString();
        String typeid = data.get("typeid").toString();
        String nodeid = data.get("nodeid").toString();
        String begin = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).parse(starttime).toString();
        String end = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).parse(endtime).toString();
        SystemUserInfo user = (SystemUserInfo) session.getAttribute("loginUser");
        List list = null;
        List list1 = null;
        int count = 0;
        if (user != null) {//管理员
            Conditions conditions = new Conditions();
            /**
             * String hql="select en from ValueItem en where en.recordingtime between :begin and :end and"
             * 				+ " en.type.typeid=:typeid"
             * 				+ " and en.node.nodeid=:nodeid"
             * 				+ " order by en.recordingtime asc";
             */
            list = valueItemService.list(conditions.between("recordingtime", begin, end).and().eq("type_info_id", typeid).and().eq("node_info_id", nodeid).limit((currentpage - 1) * rows, rows).asc("recordingtime"));
            /**
             * String hql="select count(*) from ValueItem en where en.recordingtime between :begin and :end and"
             * 				+ " en.type.typeid=:typeid"
             * 				+ " and en.node.nodeid=:nodeid";
             */
            Conditions conditions1 = new Conditions();
            list1 = valueItemService.list(conditions1.between("recordingtime", begin, end).and().eq("type_info_id", typeid).and().eq("node_info_id", nodeid));
            if (list1 != null && list1.size() == 1) {
                count = (int) list1.get(0);
            } else count = 0;
        }
        for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
            ValueItemInfo valueItemInfo = (ValueItemInfo) iter.next();
            r.append("{\"itemid\":\"" + String.valueOf(valueItemInfo.getId()) + "\",\"value\":\"");
            r.append(valueItemInfo.getValue());
            r.append("\",\"nodeid\":\"");
            r.append(valueItemInfo.getNode().getId());
            r.append("\",\"note\":\"" + valueItemInfo.getNote()
                    + "\",\"typename\":\"" + valueItemInfo.getType().getName()
                    + "\",\"recordingtime\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(valueItemInfo.getRecordingtime())
                    + "\",\"useridname\":\"" + valueItemInfo.getUser().getId() + " " + valueItemInfo.getUser().getName()
                    + "\"},");
        }
        if (list.size() != 0) {
            r.deleteCharAt(r.length() - 1);//去掉最后一个“,”
        }
        r.append("],");
        int pages;
        if (count % rows == 0) {
            pages = count / rows;
        } else {
            pages = count / rows + 1;
        }
        r.append("\"pages\":\"" + pages + "\"}");
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r.toString());
        out.flush();
    }

    @RequestMapping(value = "getValueItem", method = RequestMethod.POST)
    public void getValueItem(HttpServletResponse response) throws Exception {
        StringBuffer r = new StringBuffer();//返回的字符串
        request.setCharacterEncoding("utf-8");
        JSONObject data = ReadJSON.readJSONStr();
        String id = data.get("itemid").toString();
        ValueItemInfo n = valueItemService.findById(id);
        if (n != null) {
            r.append("{\"status\":\"1\",\"itemid\":\"" + String.valueOf(n.getId()) + "\",\"value\":\"");
            r.append(n.getValue());
            r.append("\",\"nodeid\":\"");
            r.append(n.getNode().getId());
            r.append("\",\"note\":\"" + n.getNote()
                    + "\",\"typename\":\"" + n.getType().getName()
                    + "\",\"recordingtime\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(n.getRecordingtime())
                    + "\",\"useridname\":\"" + n.getUser().getId() + " " + n.getUser().getName()
                    + "\"}");
        } else {
            r.append("{\"status\":\"0\"}");
        }
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r.toString());
        out.flush();
    }

    @RequestMapping(value = "searchAllValuesByType", method = RequestMethod.POST)
    public void searchAllValuesByType(HttpServletResponse response) throws Exception {
        System.out.println("test2");
        JSONObject data = ReadJSON.readJSONStr();
        StringBuffer r = new StringBuffer();//返回的字符串
        r.append("{\"status\":\"1\",\"ValueList\":[");
        request.setCharacterEncoding("utf-8");
        int currentpage = 1;
        int rows = Integer.parseInt(data.get("rows").toString());
        String typeid = data.get("typeid").toString();
        if (data.get("page") != null) {
            currentpage = Integer.parseInt(data.get("page").toString());
        }
        Conditions conditions = new Conditions();
        List list = valueItemService.list(conditions.eq("type_info_id", typeid).limit((currentpage - 1) * rows, rows));
        for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
            ValueItemInfo n = (ValueItemInfo) iter.next();
            r.append("{\"itemid\":\"" + String.valueOf(n.getId()) + "\",\"value\":\"");
            r.append(n.getValue());
            r.append("\",\"nodeid\":\"");
            r.append(n.getNode().getId());
            r.append("\",\"note\":\"" + n.getNote()
                    + "\",\"typename\":\"" + n.getType().getName()
                    + "\",\"recordingtime\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(n.getRecordingtime())
                    + "\",\"useridname\":\"" + n.getUser().getId() + " " + n.getUser().getName()
                    + "\"},");
        }
        if (list.size() != 0) {
            r.deleteCharAt(r.length() - 1);//去掉最后一个“,”
        }
        r.append("],");
        Conditions conditions1 = new Conditions();
        List list1 = valueItemService.list(conditions1.eq("type_info_id", typeid));
        int count;
        if (list1 != null && list1.size() == 1) {
            count = (int) list1.get(0);
        } else count = 0;
        int pages;
        if (count % rows == 0) {
            pages = count / rows;
        } else {
            pages = count / rows + 1;
        }
        r.append("\"pages\":\"" + pages + "\"}");
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r.toString());
        out.flush();
    }

    @RequestMapping(value = "searchValues", method = RequestMethod.POST)
    public void searchValues(HttpServletResponse response) throws Exception {
        System.out.println("test2");
        StringBuffer r = new StringBuffer();//返回的字符串
        r.append("{\"status\":\"1\",\"ValueList\":[");
        request.setCharacterEncoding("utf-8");
        JSONObject data = ReadJSON.readJSONStr();
        int currentpage = 1;
        int rows = 0;
        int count = 0;
        String starttime = data.get("starttime").toString();
        String endtime = data.get("endtime").toString();
        String typeid = data.get("typeid").toString();
        String nodeid = data.get("nodeid").toString();
        Date begin = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).parse(starttime);
        Date end = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).parse(endtime);
        List list = null;
        List list1=null;
        if (data.get("page") != null && Integer.parseInt(data.get("page").toString()) != 0) {
            currentpage = Integer.parseInt(data.get("page").toString());
            rows = Integer.parseInt(data.get("rows").toString());
            Conditions conditions = new Conditions();
            list = valueItemService.list(conditions.eq("type_info_id", typeid).and().eq("node_info_id", nodeid).between("recordingtime", begin, end));
            if (list != null && list.size() == 1) {
                count = (int) list.get(0);
            } else count = 0;
//            count=(int)uService.getCount(typeid, nodeid, begin, end);
//            list = uService.search(typeid, nodeid, begin, end, currentpage, rows);
            list1 = valueItemService.list(conditions.between("recordingtime", begin, end).and().eq("type_info_id", typeid).and().eq("node_info_id", nodeid).limit((currentpage - 1) * rows, rows).asc("recordingtime"));
        } else {
            currentpage = 1;
//            count = (int) uService.getCount(typeid, nodeid, begin, end);
            Conditions conditions = new Conditions();
            list = valueItemService.list(conditions.eq("type_info_id", typeid).and().eq("node_info_id", nodeid).between("recordingtime", begin, end));
            if (list != null && list.size() == 1) {
                count = (int) list.get(0);
            } else count = 0;
            rows = count;
//            list = uService.search(typeid, nodeid, begin, end);
            list1 = valueItemService.list(conditions.between("recordingtime", begin, end).and().eq("type_info_id", typeid).and().eq("node_info_id", nodeid).asc("recordingtime"));
        }
        for (int i = 0; list1 != null && i < list1.size(); i++) {
            ValueItemInfo n =( ValueItemInfo) list.get(i);
            r.append("{\"itemid\":\"" + String.valueOf(n.getId()) + "\",\"value\":\"");
            r.append(n.getValue());
            r.append("\",\"nodeid\":\"");
            r.append(n.getNode().getId());
            r.append("\",\"note\":\"" + n.getNote()
                    + "\",\"typename\":\"" + n.getType().getName()
                    + "\",\"recordingtime\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(n.getRecordingtime())
                    + "\",\"useridname\":\"" + n.getUser().getId() + " " + n.getUser().getName()
                    + "\"},");
        }
        if (list.size() != 0) {
            r.deleteCharAt(r.length() - 1);//去掉最后一个“,”
        }
        r.append("],");
        int pages;
        if (count % rows == 0) {
            pages = count / rows;
        } else {
            pages = count / rows + 1;
        }
        r.append("\"pages\":\"" + pages + "\"}");
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r.toString());
        out.flush();
    }
}
