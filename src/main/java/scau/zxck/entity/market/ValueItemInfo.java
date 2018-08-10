package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;
import scau.zxck.entity.sys.SystemUserInfo;

@Table(name = "valueitem_info")
public class ValueItemInfo extends Unique {
    @Column(name = "note")
    private String note;
    @Column(name = "recordingtime")
    private String recordingtime;
    @Column(name = "value")
    private String value;
    @Column(name = "node_info_id")
    private String node_info_id;
    @Column(name = "type_info_id")
    private String type_info_id;
    @Column(name = "system_user_info_id")
    private String system_user_info_id;
    private SystemUserInfo user;
    private NodeInfo node;
    private TypeInfo type;

    public ValueItemInfo(String note, String recordingtime, String value, String node_info_id, String type_info_id, String system_user_info_id) {
        this.note = note;
        this.recordingtime = recordingtime;
        this.value = value;
        this.node_info_id = node_info_id;
        this.type_info_id = type_info_id;
        this.system_user_info_id = system_user_info_id;
    }

    public ValueItemInfo() {
    }

    public TypeInfo getType() {
        return type;
    }

    public void setType(TypeInfo type) {
        this.type = type;
    }

    public SystemUserInfo getUser() {
        return user;
    }

    public void setUser(SystemUserInfo user) {
        this.user = user;
    }

    public NodeInfo getNode() {
        return node;
    }

    public void setNode(NodeInfo node) {
        this.node = node;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRecordingtime() {
        return recordingtime;
    }

    public void setRecordingtime(String recordingtime) {
        this.recordingtime = recordingtime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNode_info_id() {
        return node_info_id;
    }

    public void setNode_info_id(String node_info_id) {
        this.node_info_id = node_info_id;
    }

    public String getType_info_id() {
        return type_info_id;
    }

    public void setType_info_id(String type_info_id) {
        this.type_info_id = type_info_id;
    }

    public String getSystem_user_info_id() {
        return system_user_info_id;
    }

    public void setSystem_user_info_id(String system_user_info_id) {
        this.system_user_info_id = system_user_info_id;
    }
}
