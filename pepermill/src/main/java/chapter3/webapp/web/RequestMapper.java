package chapter3.webapp.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapper {

	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	public Map<String, String> createRequestHeaderMap(final InputStream in) throws IOException {
		Map<String, String> requestMap = new HashMap<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String line = reader.readLine();
		if (line == null) {
			throw new RuntimeException("헤더가 존재하지 않습니다.");
		}
		String[] header = line.split(" ");
		log.debug(header[0]);
		requestMap.put("Method", header[0]);

		// url과 함께 딸려오는 파라미터를 분리할 필요가 있다.
		String[] splitHeader = header[1].split("\\?");
		requestMap.put("Url", splitHeader[0]);

		while(!"".equals(line = reader.readLine())) {
			String[] parsedString = line.split(" ", 2);
			String key = parsedString[0].replace(":", "");
			String value = parsedString[1];
			requestMap.put(key, value);
		}

		// get으로 오는 form은 어떻게 담을 것인가?
		// post는 body로 오는데 어떻게 담을 것인가?
		// 헤더나 바디에 담겨 있을 뿐, 둘 다 key-value 형태의 값을 포함하는 것은 변함이 없다.
		if (splitHeader.length > 1) {
			parseParameters(requestMap, splitHeader[1]);
		}
		if ((requestMap.get("Method").equals("POST"))) {
			parseBody(requestMap, reader);
		}
		return requestMap;
	}

	private void parseParameters(final Map<String, String> requestMap, final String s) {
		requestMap.put("Parameters", s);
	}

	private void parseBody(final Map<String, String> requestMap, final BufferedReader reader) throws IOException {
		int contentLength = Integer.parseInt(requestMap.get("Content-Length"));
		char[] body = new char[contentLength];
		reader.read(body, 0, contentLength);
		requestMap.put("Parameters", String.valueOf(body));
	}
}
