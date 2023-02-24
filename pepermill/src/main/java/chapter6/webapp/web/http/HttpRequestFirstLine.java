package chapter6.webapp.web.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 요청 라인을 별도의 클래스로 분리한다.
 * http method, url path, parameters를 담고 있는 클래스.
 * 요청이 올바른 형식인지 검증하는 로직이 존재
 *
 * chapter4에서는 RequestMapper 클래스가 헤더, url, parameter 모두 담당했다.
 * 기존 구현의 단점 : get parameter과 post parameter 코드 중복이 발생 -> get과 post 파라미터 테스트 코드 작성이 불편함.
 * 해결 : 클래스로 분리하여 테스트가 용이하다.
 */
public class HttpRequestFirstLine {

	private static final Logger log = LoggerFactory.getLogger(HttpRequestFirstLine.class);

	private HttpMethod httpMethod;
	private String url;
	private HttpQueryParameters httpQueryParameters;

	/**
	 * 요청 라인이 들어오면 적절한 요청인지 확인한다.
	 * 요청 라인에서 http method와 url를 파싱한다.
	 * url에 parameter가 존재한다면 파싱하여 저장한다.
	 *
	 * @param line
	 */
	public HttpRequestFirstLine(String line) {
		log.debug("request line : {}", line);
		String[] header = line.split(" ");
		validateHeader(header);
		httpMethod = HttpMethod.valueOf(header[0]);
		url = header[1];

		if (httpMethod.isPost()) {
			return ;
		}

		int parameterIndex = header[1].indexOf("?");
		httpQueryParameters = HttpQueryParameters.parseQueryString(header[1].substring(parameterIndex + 1));
	}

	private void validateHeader(final String[] header) {
		if (header.length != 3) {
			throw new IllegalArgumentException("잘못된 요청 정보입니다.");
		}
	}

	public String getParameterValue(final String key) {
		return httpQueryParameters.getValue(key);
	}

	public HttpQueryParameters getHttpQueryParameters() {
		return this.httpQueryParameters;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public String getUrl() {
		return url;
	}

	public boolean isPost() {
		return httpMethod.isPost();
	}
}
