package chapter6.webapp.web.http;

public enum HttpStatusCode {
	OK("OK", "200"),
	FOUND("Found", "302"),
	FORBIDDEN("Forbidden", "403");

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
