package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.entity.market.NodeInfo;
import scau.zxck.service.market.INodeInfoService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:43
 */
@Controller
@RequestMapping("/")
public class NodeAction {
    @Autowired
    private INodeInfoService nodeInfoService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "addNode", method = RequestMethod.POST)
    public void addNode(HttpServletResponse response) throws Exception {
        String r = "";
        JSONObject data = ReadJSONUtil.readJSONStr(request);
        String nodepk = data.get("nodeid").toString();
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
        try {
            nodeInfoService.add(nodeInfo);
            r = "{\"status\":\"1\",\"msg\":\"添加结点成功\"}";
            FlushWriteUtil.flushWrite(response,r);
        } catch (Exception e) {
            r = "{\"status\":\"0\",\"msg\":\"添加结点失败\"}";
            FlushWriteUtil.flushWrite(response,r);
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
        FlushWriteUtil.flushWrite(response,r.toString());
    }
    @RequestMapping(value = "deleteNode",method = RequestMethod.POST)
    public void deleteNode(HttpServletResponse response)throws Exception{
        String r = "{\"status\":\"\",\"msg\":\"\"}";//返回的字符串
        request.setCharacterEncoding("utf-8");
        JSONObject data= ReadJSONUtil.readJSONStr(request);
        String nodeid=data.get("nodeid").toString();
        nodeInfoService.deleteByIds(nodeid);
        r = "{\"status\":\"1\",\"msg\":\"删除结点成功\"}";
        FlushWriteUtil.flushWrite(response,r);
    }
    @RequestMapping(value = "getNode",method = RequestMethod.POST)
    public void getNode(HttpServletResponse response) throws Exception{
        StringBuffer r = new StringBuffer();//返回的字符串
        request.setCharacterEncoding("utf-8");
        JSONObject data= ReadJSONUtil.readJSONStr(request);
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
        FlushWriteUtil.flushWrite(response,r.toString());
    }
}
