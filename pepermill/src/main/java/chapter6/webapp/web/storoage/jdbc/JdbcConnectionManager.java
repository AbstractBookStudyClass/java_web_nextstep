package chapter6.webapp.web.storoage.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class JdbcConnectionManager {

	private static final String DATABASE_DRIVER = "database.driver";
	private static final String DATABASE_URL = "database.url";
	private static final String DATABASE_USER = "database.username";
	private static final String DATABASE_PASSWORD = "database.password";

	private final DataSourceProperties properties;

	public JdbcConnectionManager(final DataSourceProperties properties) {
		this.properties = properties;
	}

	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(properties.getValue(DATABASE_DRIVER));
		dataSource.setUrl(properties.getValue(DATABASE_URL));
		dataSource.setUsername(properties.getValue(DATABASE_USER));
		dataSource.setPassword(properties.getValue(DATABASE_PASSWORD));
		return dataSource;
	}

	public Connection getConnection() {
		try {
			return getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("커넥션을 얻을 수 없습니다.");
		}
	}
}
