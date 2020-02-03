package br.com.franca.dao.jdbc.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.franca.dao.jdbc.interfaces.ConnectionJDBC;

public class ConnectionMYSQL implements ConnectionJDBC {

	private Connection connection;

	public ConnectionMYSQL() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		Properties properties = new Properties();

		properties.put("user", "root");

		properties.put("password", "root");

		this.connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/db_curso?autoReconnect=true&useSSL=false", properties);

		this.connection.setAutoCommit(false);

	}

	@Override
	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException ex) {
				Logger.getLogger(ConnectionMYSQL.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	@Override
	public void commit() throws SQLException {
		this.connection.commit();
		this.close();
	}

	@Override
	public void rollback() {
		if (this.connection != null) {
			try {
				this.connection.rollback();
			} catch (SQLException ex) {
				Logger.getLogger(ConnectionMYSQL.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				this.close();
			}
		}
	}

}
