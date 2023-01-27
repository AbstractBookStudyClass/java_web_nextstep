package chapter3.webapp.web;

public enum HttpStatusCode {
	OK("OK", "200"),
	FOUND("Found", "302");

	private final String httpStatusDescription;
	private final String httpStatusCode;

	HttpStatusCode(final String httpStatusDescription, final String httpStatusCode) {
		this.httpStatusDescription = httpStatusDescription;
		this.httpStatusCode = httpStatusCode;
	}

	public String getHttpStatusDescription() {
		return httpStatusDescription;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}
}
