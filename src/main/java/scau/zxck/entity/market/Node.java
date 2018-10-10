package scau.zxck.entity.market;
import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

/***
 * 功能：地点定位表的实体Bean,用于生成和进行地址定位表的各项操作
 * @author 林莹莹
 *
 */

//@Table(name="node")
public class Node extends Unique{
	/**
	 *
	 */
	private static final long serialVersionUID = -5126316067572721459L;
	private String nodeid;
	private String nodeName;
	private double longitude;//经度
	private String east_west;
	private double latitude;//纬度
	private String south_north;
	private String note;

	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getEast_west() {
		return east_west;
	}
	public void setEast_west(String east_west) {
		this.east_west = east_west;
	}

	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getSouth_north() {
		return south_north;
	}
	public void setSouth_north(String south_north) {
		this.south_north = south_north;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}





}
