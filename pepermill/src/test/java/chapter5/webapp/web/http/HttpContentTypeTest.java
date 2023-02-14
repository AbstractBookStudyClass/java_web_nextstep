package chapter5.webapp.web.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class HttpContentTypeTest {

	@Test
	@DisplayName("Content-Type은 존재하지 않는 type일 경우 text/html을 기본으로 생성한다.")
	void create_default_content_type() {
		//given
		String notSupportedType = "";

		//when
		HttpContentType httpContentType = HttpContentType.fromType(notSupportedType);

		//then
		Assertions.assertEquals(httpContentType, HttpContentType.TEXT_HTML);
	}

	@ParameterizedTest
	@EnumSource(HttpContentType.class)
	@DisplayName("지원하는 content-type을 생성한다.")
	void create_supported_content_type(HttpContentType httpContentType) {
		//given
		String type = httpContentType.getType();

		//when
		HttpContentType expected = HttpContentType.fromType(type);

		//then
		Assertions.assertEquals(expected, httpContentType);
	}
}