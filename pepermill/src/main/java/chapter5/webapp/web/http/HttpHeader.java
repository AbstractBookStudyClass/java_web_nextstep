package chapter5.webapp.web.http;

import java.util.Objects;

/**
 * 단일 헤더 정보를 가지는 클래스. (일급 객체 적용)
 * 헤더는 key-value로 구성된다.
 */
public class HttpHeader {

	private static final String DELIMITER = ": ";
	private static final int HEADER_KEY_INDEX = 0;
	private static final int HEADER_VALUE_INDEX = 1;
	private static final int VALID_HEADER_SIZE = 2;

	private final String key;
	private final String value;

	public HttpHeader(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * (헤더): (헤더정보) 형태로 들어오는 문자열을 key - value 형태로 분리하여 저장한다.
	 *
	 * @param headerLine 헤더 정보
	 * @return HttpHeader
	 */
	public static HttpHeader parseHeaderLine(String headerLine) {
		String[] splitHeader = headerLine.split(DELIMITER);
		validateHeader(splitHeader);
		return new HttpHeader(splitHeader[HEADER_KEY_INDEX], splitHeader[HEADER_VALUE_INDEX]);
	}

	private static void validateHeader(String[] splitHeader) {
		if (splitHeader.length != VALID_HEADER_SIZE) {
			throw new IllegalArgumentException("헤더 정보가 올바르지 않습니다.");
		}
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final HttpHeader that = (HttpHeader)o;
		return Objects.equals(key, that.key) && Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value);
	}
}
