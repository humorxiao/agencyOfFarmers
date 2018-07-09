package scau.zxck.base.dao.entity;

import java.io.Serializable;

public class SQLAdapter implements Serializable {

	private static final long serialVersionUID = 6579208034424787216L;

	private String sql;

	public SQLAdapter() {

	}

	public SQLAdapter(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
