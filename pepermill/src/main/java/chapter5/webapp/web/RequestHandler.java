package chapter5.webapp.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter5.webapp.domain.UserControllerV2;
import chapter5.webapp.domain.UserService;
import chapter5.webapp.domain.repository.UserRepository;
import chapter5.webapp.web.http.HttpRequest;
import chapter5.webapp.web.http.HttpResponse;
import chapter5.webapp.web.storoage.SessionStorage;

public class RequestHandler extends Thread {

	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	static UserRepository userRepository = new UserRepository();
	static SessionStorage sessionStorage = new SessionStorage();

	private Socket connection;

	public RequestHandler(final Socket connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		log.debug("Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());

		ViewResolver viewResolver = new ViewResolver();
		UserControllerV2 userController = new UserControllerV2(new UserService(userRepository, sessionStorage));
		MethodHandler methodHandler = new MethodHandler(UserControllerV2.class, userController);

		try (InputStream in = connection.getInputStream();
			 OutputStream out = connection.getOutputStream();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

			HttpRequest request = new HttpRequest(reader);
			HttpResponse response = HttpResponse.defaultHttpResponse();

			methodHandler.invokeMethod(request, response);

			viewResolver.sendView(response, out);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
