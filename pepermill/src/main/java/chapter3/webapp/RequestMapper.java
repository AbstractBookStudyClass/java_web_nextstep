package chapter3.webapp;

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
		requestMap.put("Url", header[1]);

		while(!"".equals(line = reader.readLine())) {
			String[] parsedString = line.split(" ", 2);
			String key = parsedString[0].replace(":", "");
			String value = parsedString[1];
			requestMap.put(key, value);
		}

		return requestMap;
	}
}
