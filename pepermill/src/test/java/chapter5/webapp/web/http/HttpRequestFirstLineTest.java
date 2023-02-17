package chapter5.webapp.web.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HttpRequestFirstLineTest {

	@Test
	@DisplayName("헤더의 첫 줄이 잘못된 형식일 경우 예외가 발생한다.")
	void throw_exception_when_invalid_header_first_line() {
		//given
		String firstLine = "not allowed string";

		//when, then
		Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> new HttpRequestFirstLine(firstLine)
		);
	}

	@Test
	@DisplayName("올바른 헤더 정보는 헤더 메소드, url를 저장한다.")
	void save_appropriate_header_format() {
		//given
		String httpMethod = "GET";
		String url = "127.0.0.1";
		String httpVersion = "HTTP/1.1";
		String firstLine = httpMethod + " " + url + " " + httpVersion;

		//when
		HttpRequestFirstLine result = new HttpRequestFirstLine(firstLine);
		HttpQueryParameters parameters = result.getHttpQueryParameters();

		//then
		Assertions.assertEquals(result.getHttpMethod().getValue(), httpMethod);
		Assertions.assertEquals(result.getUrl(), url);
		Assertions.assertEquals(parameters.isEmpty(), true);
	}

	@Test
	@DisplayName("올바른 헤더 정보는 헤더, 메소ㄷ, url, 쿼리 파라미터를 저장한다.")
	void save_appropriate_header_format_with_query_string() {
		//given
		String httpMethod = "GET";
		String host = "127.0.0.1";
		String httpVersion = "HTTP/1.1";
		String queryString = "?name1=value1&name2=value2";
		String url = host + queryString;
		String firstLine = httpMethod + " " + url + " " + httpVersion;

		//when
		HttpRequestFirstLine result = new HttpRequestFirstLine(firstLine);
		HttpQueryParameters parameters = result.getHttpQueryParameters();

		//then
		Assertions.assertEquals(result.getHttpMethod().getValue(), httpMethod);
		Assertions.assertEquals(result.getUrl(), url);
		Assertions.assertEquals(parameters.size(), 2);
	}
}