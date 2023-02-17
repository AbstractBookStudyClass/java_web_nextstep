package chapter5.webapp.web.http;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

class HttpQueryParametersTest {

	@ParameterizedTest
	@NullSource
	@DisplayName("쿼리가 null일 경우, 비어있는 쿼리 맵은 비어 있다.")
	void return_empty_query_parameters_when_query_string_is_empty(String query) {
		//when
		HttpQueryParameters httpQueryParameters = HttpQueryParameters.parseQueryString(query);
		Map<String, String> expected = httpQueryParameters.getParameters();

		//then
		Assertions.assertEquals(expected.isEmpty(), true);
	}

	@ParameterizedTest
	@EmptySource
	@DisplayName("쿼리가 공백일 경우, 비어있는 쿼리 맵은 비어 있다.")
	void return_empty_query_parameters_when_query_string_is_blank(String query) {
		//when
		HttpQueryParameters httpQueryParameters = HttpQueryParameters.parseQueryString(query);
		Map<String, String> expected = httpQueryParameters.getParameters();

		//then
		Assertions.assertEquals(expected.isEmpty(), true);
	}
}