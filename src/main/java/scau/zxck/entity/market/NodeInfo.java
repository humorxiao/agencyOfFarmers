package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;
@Table(name = "node_info")
public class NodeInfo extends Unique {
    @Column(name = "east_west")
    private String east_west;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "nodeName")
    private String nodeName;
    @Column(name = "note")
    private String note;
    @Column(name = "south_north")
    private String south_north;

    public String getEast_west() {
        return east_west;
    }

    public void setEast_west(String east_west) {
        this.east_west = east_west;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSouth_north() {
        return south_north;
    }

    public void setSouth_north(String south_north) {
        this.south_north = south_north;
    }
}
