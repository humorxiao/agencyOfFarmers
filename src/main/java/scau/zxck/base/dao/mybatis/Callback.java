package scau.zxck.base.dao.mybatis;

import java.sql.Connection;

public interface Callback<T> {

	T execute(Connection connection);
}
