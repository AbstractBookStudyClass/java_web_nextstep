package chapter6.webapp.web.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 여러 헤더 정보를 가지는 클래스 (일급 객체 적용)
 *
 */
public class HttpHeaders {

	private final Map<String, HttpHeader> headers;

	private HttpHeaders(final Map<String, HttpHeader> headers) {
		this.headers = headers;
	}

	public static HttpHeaders parseHeaderLine(final List<String> inputLines) {
		Map<String, HttpHeader> map = new HashMap<>();
		int parseIndex = parseHeader(inputLines);
		for (String headerLine : inputLines.subList(0, parseIndex)) {
			HttpHeader header = HttpHeader.parseHeaderLine(headerLine);
			map.put(header.getKey(), header);
		}
		return new HttpHeaders(map);
	}

	public static HttpHeaders emptyHeaders() {
		Map<String, HttpHeader> map = new HashMap<>();
		return new HttpHeaders(map);
	}

	private static int parseHeader(final List<String> inputLines) {
		int index = inputLines.indexOf("");
		if (index == -1) {
			return inputLines.size();
		}
		return 0;
	}

	public String getHeaderValue(final String key) {
		HttpHeader httpHeader = this.headers.get(key);
		if (httpHeader == null) {
			return null;
		}
		return httpHeader.getValue();
	}

	public String getHeaderValueOrDefault(final String key) {
		HttpHeader httpHeader = this.headers.get(key);
		if (httpHeader == null) {
			return "0";
		}
		return httpHeader.getValue();
	}

	public Map<String, HttpHeader> getHeaders() {
		return this.headers;
	}

	public Set<Map.Entry<String, HttpHeader>> getEntries() {
		return getHeaders().entrySet();
	}
}
