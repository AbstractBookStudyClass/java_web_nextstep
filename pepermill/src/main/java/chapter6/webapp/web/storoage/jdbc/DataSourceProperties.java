package chapter6.webapp.web.storoage.jdbc;

import java.util.HashMap;
import java.util.Map;

public class DataSourceProperties {

	private static final String DATABASE_DRIVER = "database.driver";
	private static final String DATABASE_URL = "database.url";
	private static final String DATABASE_USER = "database.username";
	private static final String DATABASE_PASSWORD = "database.password";

	private static final String H2_DRIVER = "org.h2.Driver";
	private static final String H2_URL = "jdbc:h2:tcp://localhost/~/test";
	private static final String H2_USER = "sa";
	private static final String H2_PASSWORD = "password";

	private final Map<String, String> properties;

	private DataSourceProperties(final Map<String, String> properties) {
		this.properties = properties;
	}

	public static DataSourceProperties createTestDataSourceProperties() {
		Map<String, String> map = new HashMap<>();
		map.put(DATABASE_DRIVER, H2_DRIVER);
		map.put(DATABASE_URL, H2_URL);
		map.put(DATABASE_USER, H2_USER);
		map.put(DATABASE_PASSWORD, H2_PASSWORD);

		return new DataSourceProperties(map);
	}

	public String getValue(String key) {
		return properties.get(key);
	}

	public void setProperties(String key, String value) {
		properties.put(key, value);
	}
}
