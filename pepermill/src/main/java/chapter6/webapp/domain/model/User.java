package chapter6.webapp.domain.model;

import java.util.Objects;

public class User {

	private Integer userId;
	private String userName;
	private String password;

	public User(final Integer userId, final String userName, final String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public User(final String userName, final String password) {
		this.userName = userName;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(final Integer userId) {
		this.userId = userId;
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
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final User user = (User)o;
		return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName)
			&& Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userName, password);
	}

	@Override
	public String toString() {
		return "User{" +
			"userId=" + userId +
			", userName='" + userName + '\'' +
			", password='" + password + '\'' +
			'}';
	}
}
