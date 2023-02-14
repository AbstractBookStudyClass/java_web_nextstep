package chapter6.webapp.web.http;

import java.util.Arrays;

public enum HttpContentType {

	TEXT_HTML("text/html", "text"),
	TEXT_CSS("text/css", "css"),
	;

	private final String contentType;
	private final String type;

	HttpContentType(final String contentType, final String type) {
		this.contentType = contentType;
		this.type = type;
	}

	/**
	 * Content-type을 전달한다. 만약 없는 값일 경우, text/html을 전달한다.
	 *
	 * @param type
	 * @return
	 */
	public static HttpContentType fromType(String type) {
		return Arrays.stream(values())
			.filter(httpContentType -> httpContentType.getType().equals(type))
			.findAny()
			.orElse(TEXT_HTML);
	}

	public String getType() {
		return type;
	}

	public String getContentType() {
		return contentType;
	}
}
