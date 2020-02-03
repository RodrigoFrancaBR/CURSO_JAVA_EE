package br.com.franca.dao.jdbc.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionJDBC {
	public Connection getConnection();

	public void close();

	public void commit() throws SQLException;

	public void rollback();
}
