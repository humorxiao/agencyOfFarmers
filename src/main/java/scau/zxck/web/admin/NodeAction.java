package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.NodeInfo;
import scau.zxck.service.market.INodeInfoService;
import scau.zxck.utils.ReadJSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class NodeAction {
    @Autowired
    private INodeInfoService nodeInfoService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "addNode", method = RequestMethod.POST)
    public void addNode(HttpServletResponse response) throws Exception {
        String jsonStr;
        String r = "";
        JSONObject data = ReadJSON.readJSONStr(request);
        String nodepk = data.get("nodepk").toString();
        double longitude = Double.valueOf(data.get("longitude").toString());
        double latitude = Double.valueOf(data.get("latitude").toString());
        String w_e = data.get("e_w").toString();
        String s_n = data.get("n_s").toString();
        String note = data.get("note").toString();
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setEast_west(w_e);
        nodeInfo.setLatitude(latitude);
        nodeInfo.setLongitude(longitude);
        nodeInfo.setNote(note);
        nodeInfo.setSouth_north(s_n);
        nodeInfo.setId(nodepk);
        PrintWriter out = response.getWriter();
        try {
            nodeInfoService.add(nodeInfo);
            r = "{\"status\":\"1\",\"msg\":\"添加结点成功\"}";
            out.flush();
            out.write(r);
            out.flush();
        } catch (Exception e) {
            r = "{\"status\":\"0\",\"msg\":\"添加结点失败\"}";
            out.flush();
            out.write(r);
            out.flush();
        }
    }

    @RequestMapping(value = "allNodes", method = RequestMethod.POST)
    public void allNodes(HttpServletResponse response) throws Exception {
        StringBuffer r = new StringBuffer();
        r.append("{\"status\":\"1\",\"NodeList\":[");//返回的字符串
        List list = nodeInfoService.listAll();
        for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
            NodeInfo n = (NodeInfo) iter.next();
            r.append("{\"nodeid\":\"" + n.getId() + "\",\"longitude\":\"");
            r.append(String.valueOf(n.getLongitude()));
            r.append("\",\"latitude\":\"");
            r.append(String.valueOf(n.getLatitude()));
            r.append("\",\"note\":\"" + n.getNote()
                    + "\",\"hemisphere\":\"" + n.getEast_west() + " " + n.getSouth_north()
                    + "\"},");
        }
        if(list.size()!=0){
            r.deleteCharAt(r.length()-1);//去掉最后一个“,”
        }
        r.append("]}");
        PrintWriter out=response.getWriter();
        out.flush();
        out.write(r.toString());
        out.flush();
    }
    @RequestMapping(value = "deleteNode",method = RequestMethod.POST)
    public void deleteNode(HttpServletResponse response)throws Exception{
        String r = "{\"status\":\"\",\"msg\":\"\"}";//返回的字符串
        request.setCharacterEncoding("utf-8");
        JSONObject data=ReadJSON.readJSONStr(request);
        String nodeid=data.get("nodeid").toString();
        nodeInfoService.deleteByIds(nodeid);
        r = "{\"status\":\"1\",\"msg\":\"删除结点成功\"}";
        PrintWriter out=response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }
    @RequestMapping(value = "getNode",method = RequestMethod.POST)
    public void getNode(HttpServletResponse response) throws Exception{
        StringBuffer r = new StringBuffer();//返回的字符串
        request.setCharacterEncoding("utf-8");
        JSONObject data=ReadJSON.readJSONStr(request);
        String nodeid=data.get("nodeid").toString();
        NodeInfo n=nodeInfoService.findById(nodeid);
        if(n!=null){
            r.append("{\"status\":\"1\",\"nodeid\":\"" + n.getId()+ "\",\"longitude\":\"");
            r.append(String.valueOf(n.getLongitude()));
            r.append("\",\"latitude\":\"");
            r.append(String.valueOf(n.getLatitude()));
            r.append("\",\"note\":\"" + n.getNote()
                    + "\",\"hemisphere\":\"" + n.getEast_west()+" "+n.getSouth_north()
                    + "\"}");
        }else{
            r.append("{\"status\":\"0\"}");
        }
        PrintWriter out=response.getWriter();
        out.flush();
        out.write(r.toString());
        out.flush();
    }
}
