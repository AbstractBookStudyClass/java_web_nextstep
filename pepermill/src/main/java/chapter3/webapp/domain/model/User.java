package chapter3.webapp.domain.model;

import java.util.Objects;

public class User {

	private String userName;
	private String password;

	public User(final String userName, final String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
			"userName='" + userName + '\'' +
			", password='" + password + '\'' +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final User user = (User)o;
		return Objects.equals(userName, user.userName) && Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, password);
	}
}
