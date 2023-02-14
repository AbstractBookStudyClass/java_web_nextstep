package chapter6.webapp.web.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Http 요청을 처리하는 클래스
 * chapter 4 구현은 handler에 map을 선언해서 헤더 값과 파라미터 값을 저장했다.
 * chapter 4 구현의 단점 : 요청 정보를 모두 map에만 담아야 하고 추가적인 기능 확장이 불가능하다.
 * 해결 방법 : 별도의 요청 클래스를 만들어 처리할 수 있도록 한다.
 */
public class HttpRequest {

	private HttpRequestFirstLine requestLine;
	private HttpHeaders httpHeaders;
	private HttpQueryParameters httpQueryParameters;
	private String body;

	public HttpRequest(BufferedReader reader) throws IOException {
		this.requestLine = new HttpRequestFirstLine(reader.readLine());
		this.httpHeaders = HttpHeaders.parseHeaderLine(readHeaders(reader));
		int contentLength = Integer.parseInt(httpHeaders.getHeaderValueOrDefault("Content-Length"));
		this.body = readBody(reader, contentLength);
		this.httpQueryParameters = getQueryParameters(this.body);
	}

	private HttpQueryParameters getQueryParameters(final String body) {
		if (getHttpMethod().isPost()) {
			return HttpQueryParameters.parseQueryString(body);
		}
		return requestLine.getHttpQueryParameters();
	}

	private List<String> readHeaders(BufferedReader reader) throws IOException {
		List<String> headers = new ArrayList<>();
		String line;
		while (!"".equals(line = reader.readLine())) {
			headers.add(line);
		}
		return headers;
	}

	private String readBody(BufferedReader reader, int length) throws IOException {
		char[] body = new char[length];
		reader.read(body, 0, length);
		return String.valueOf(body);
	}

	public HttpMethod getHttpMethod() {
		return requestLine.getHttpMethod();
	}

	public String getUrl() {
		return requestLine.getUrl();
	}

	public String getHttpHeader(final String key) {
		return httpHeaders.getHeaderValue(key);
	}

	public String getHttpCookie() {
		return httpHeaders.getHeaderValue("Cookie");
	}

	public HttpQueryParameters getHttpQueryParameters() {
		return httpQueryParameters;
	}

	public String getBody() {
		return body;
	}
}
