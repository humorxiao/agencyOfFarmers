package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

/***
 * 功能：数据类型的实体Bean,用于生成和进行数据类型表的各项操作
 * @author 林莹莹
 *
 */

//@Table(name="type")
public class ValueType extends Unique {

	private static final long serialVersionUID = 8346067969985426082L;
	private String typeid;
	private String typename;


	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}



}
