package chapter3.webapp.web;

public class RequestForm {
	private String name;
	private String password;

	public RequestForm() {}

	public RequestForm(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}
