package chapter5.webapp.web.http;

import java.util.Arrays;

public enum HttpMethod {

	GET("GET"),
	POST("POST"),
	;

	private final String value;

	HttpMethod(final String value) {
		this.value = value;
	}

	public static HttpMethod from(String value) {
		return Arrays.stream(values())
			.filter(httpMethod -> httpMethod.getValue().equals(value))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메서드입니다."));
	}

	public boolean isPost() {
		return this == POST;
	}

	public String getValue() {
		return value;
	}
}
