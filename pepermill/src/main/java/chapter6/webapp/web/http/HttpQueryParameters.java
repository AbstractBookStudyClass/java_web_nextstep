package chapter6.webapp.web.http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 쿼리 파라미터를 저장하는 클래스
 */
public class HttpQueryParameters {

	private final Map<String, String> parameters;

	private HttpQueryParameters(final Map<String, String> parameters) {
		this.parameters = parameters;
	}

	/**
	 * key1=value1&key2=value2를 key-value로 파싱하여 저장한다.
	 *
	 * @param queryString
	 * @return
	 */
	public static HttpQueryParameters parseQueryString(final String queryString) {
		if (queryString == null || queryString.isBlank()) {
			return new HttpQueryParameters(new HashMap<>());
		}
		String[] queryParameters = queryString.split("&");
		Map<String, String> map = Arrays.stream(queryParameters)
			.map(splitParameters())
			.filter(validateQueryParameters())
			.collect(Collectors.toMap(e -> e[0], e -> e[1]));

		return new HttpQueryParameters(map);
	}

	private static Function<String, String[]> splitParameters() {
		return p -> p.split("=", 2);
	}

	private static Predicate<String[]> validateQueryParameters() {
		return s -> s.length == 2;
	}

	public String getValue(String key) {
		return parameters.get(key);
	}

	public Map<String, String> getParameters() {
		return this.parameters;
	}

	public boolean isEmpty() {
		return parameters.isEmpty();
	}

	public int size() {
		return parameters.size();
	}
}
