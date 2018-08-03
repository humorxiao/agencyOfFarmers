package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;
import java.util.Date;
//@Table(name="valueItem")
public class ValueItem extends Unique {
	/**
	 *
	 */
	private static final long serialVersionUID = -6655803704662547005L;
	private int itemid=1;
	private String value;
	private String note;
	private Date recordingtime;



	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getRecordingtime() {
		return recordingtime;
	}
	public void setRecordingtime(Date recordingtime) {
		this.recordingtime = recordingtime;
	}



}
