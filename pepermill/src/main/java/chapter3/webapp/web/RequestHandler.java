package chapter3.webapp.web;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter3.webapp.domain.UserController;
import chapter3.webapp.domain.UserService;
import chapter3.webapp.domain.repository.MemoryRepository;
import chapter3.webapp.domain.repository.SessionRepository;

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

		// 요청은 새로운 스레드를 생성한다.
		// 요청 수 만큼 클래스가 생성된다. -> 개선 필요.
		RequestMapper requestMapper = new RequestMapper();
		ViewResolver viewResolver = new ViewResolver();
		// 하드 코딩함. 객체를 찾아서 주입해주거나 자동 생성해주는 것이 필요함.
		UserController userController = new UserController(new UserService(new MemoryRepository(), new SessionRepository()));
		MethodHandler methodHandler = new MethodHandler(UserController.class, userController);

		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
			// Mapper에게 inputstream을 넘겨 map으로 변환된 결과를 받는다.
			requestMap = requestMapper.createRequestHeaderMap(in);

			// 보다 보편적인 기능을 위해서는 View라는 클래스를 통해서 전달한다.
			// 예를 들어, 상태코드 변경, 헤더 변경 등 다양한 기능을 위해서 추가할 필요가 있다.
			// View 클래스에서 url를 가져와 해당 html 문서를 전달한다.

			// get, post http 메소드에 따라 다른 요청 처리
			View view = methodHandler.createResponseView(requestMap);
			byte[] document = viewResolver.getView(view);

			// 처리된 결과를 전달한다.
			DataOutputStream dos = new DataOutputStream(out);
			sendView(dos, document);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void sendView(DataOutputStream dos, byte[] document) {
		try {
			dos.write(document, 0, document.length);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
