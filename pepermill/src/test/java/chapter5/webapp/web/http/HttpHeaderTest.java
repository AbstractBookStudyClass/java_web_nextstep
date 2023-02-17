package chapter5.webapp.web.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HttpHeaderTest {

	@Test
	@DisplayName("올바른 형식의 헤더가 아닐 경우 예외가 발생한다.")
	void throw_exception_when_illegal_header_format() {
		//given
		String headerLine = "this&$ not_allowed";

		//when, then
		Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> HttpHeader.parseHeaderLine(headerLine))
		;
	}

	@Test
	@DisplayName("헤더 정보를 파싱해서 저장한다.")
	void save_headerLine_into_header() {
		//given
		String headerKey = "Cookie";
		String Delimiter = ": ";
		String headerValue = "login=true";
		String headerLine = headerKey + Delimiter + headerValue;

		//when
		HttpHeader httpHeader = HttpHeader.parseHeaderLine(headerLine);

		//then
		Assertions.assertEquals(httpHeader.getKey(), headerKey);
		Assertions.assertEquals(httpHeader.getValue(), headerValue);
	}
}