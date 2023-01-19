package chapter3.webapp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler extends Thread {

	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	private Socket connection;
	private Map<String, String> requestMap;

	public RequestHandler(final Socket connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		log.debug("Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());

		// 요청은 스레드 수 만큼 들어온다
		// 요청 수 만큼 클래스가 생성된다. -> 개선 필요.
		RequestMapper requestMapper = new RequestMapper();
		ViewResolver viewResolver = new ViewResolver();

		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
			// Mapper에게 inputstream을 넘겨 map으로 변환된 결과를 받는다.
			requestMap = requestMapper.createRequestHeaderMap(in);

			// map에서 헤더를 읽어서 요청에 맞게 처리한다.
			byte[] body = viewResolver.getView(requestMap.get("Url"));

			// 처리된 결과를 전달한다.
			DataOutputStream dos = new DataOutputStream(out);
			response200Header(dos, body.length);
			responseBody(dos, body);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
			dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void responseBody(DataOutputStream dos, byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
