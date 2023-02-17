package chapter5.webapp.web.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class HttpMethodTest {

	@Test
	@DisplayName("지원하지 않는 http 메소드는 예외가 발생한다.")
	void throw_exception_when_http_is_not_existed() {
		//given
		String httpMethod = "";

		//when, then
		Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> HttpMethod.from(httpMethod)
		);
	}

	@ParameterizedTest
	@EnumSource(HttpMethod.class)
	@DisplayName("지원하는 http 메소드는 Http Method를 생성한다.")
	void create_http_method_when_supported_method(HttpMethod httpMethod) {
		//when
		HttpMethod expected = HttpMethod.from(httpMethod.getValue());

		//then
		Assertions.assertEquals(expected.getValue(), httpMethod.getValue());
	}
}