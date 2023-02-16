package chapter6.webapp.web.http;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 응답 헤더와 바디 정보를 담고 있는 클래스
 * 바디는 없을 수도 있다.
 */
public class HttpResponse {

	private static final Logger log = LoggerFactory.getLogger(HttpResponse.class);

	private static final String HTTP_1_1 = "HTTP/1.1";
	private static final String EMPTY_BODY = "";
	private static final String DEFAULT_HTML = "";
	private static final String DELIMITER = "\r\n";
	private static final String WHITE_SPACE = " ";

	private HttpStatusCode httpStatusCode;
	private HttpHeaders httpHeaders;
	private String body;
	private String htmlLocation;

	public static HttpResponse defaultHttpResponse() {
		return new HttpResponse(HttpStatusCode.OK, HttpHeaders.emptyHeaders(), EMPTY_BODY, DEFAULT_HTML);
	}

	private HttpResponse(final HttpStatusCode httpStatusCode,
						 final HttpHeaders httpHeaders,
		 				 final String body,
						 final String htmlLocation) {
		this.httpStatusCode = httpStatusCode;
		this.httpHeaders = httpHeaders;
		this.body = body;
		this.htmlLocation = htmlLocation;
	}

	public String getResponse() {
		String header = getHeaderString();
		return String.join(DELIMITER,
			HTTP_1_1 + WHITE_SPACE + httpStatusCode.getHttpStatusCode() + WHITE_SPACE +httpStatusCode.getHttpStatusDescription(),
			header + DELIMITER, "");
	}

	private String getHeaderString() {
		return httpHeaders.getEntries()
			.stream()
			.map(e -> e.getKey() + ": " + e.getValue().getValue())
			.collect(Collectors.joining(DELIMITER));
	}

	public void setCookie(final String value) {
		setHttpHeader("Set-cookie", value);
	}

	public void setContentType(final String value) {
		setHttpHeader("Content-Type", value);
	}

	public void setHtmlLocation(final String htmlLocation) {
		this.htmlLocation = htmlLocation;
	}

	public void setHttpHeader(final String key, final String value) {
		Map<String, HttpHeader> headers = this.httpHeaders.getHeaders();
		HttpHeader httpHeader = new HttpHeader(key, value);
		headers.put(key, httpHeader);
	}

	public void setBody(final String body) {
		this.body = body;
	}

	public void setHttpStatusCode(final HttpStatusCode statusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public HttpStatusCode getHttpStatusCode() {
		return httpStatusCode;
	}

	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

	public String getBody() {
		return body;
	}

	public String getHtmlLocation() {
		return htmlLocation;
	}
}
