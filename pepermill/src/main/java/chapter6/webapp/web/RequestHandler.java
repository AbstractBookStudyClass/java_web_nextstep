package chapter6.webapp.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter6.webapp.domain.UserController;
import chapter6.webapp.domain.UserService;
import chapter6.webapp.domain.repository.UserJdbcRepository;
import chapter6.webapp.domain.repository.UserRepository;
import chapter6.webapp.web.http.HttpRequest;
import chapter6.webapp.web.http.HttpResponse;
import chapter6.webapp.web.storoage.SessionStorage;
import chapter6.webapp.web.storoage.jdbc.DataSourceProperties;
import chapter6.webapp.web.storoage.jdbc.JdbcConnectionManager;

public class RequestHandler extends Thread {

	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	static JdbcConnectionManager jdbcConnectionManager = new JdbcConnectionManager(
		DataSourceProperties.createTestDataSourceProperties()
	);
	static UserRepository userRepository = new UserJdbcRepository(jdbcConnectionManager);
	static SessionStorage sessionStorage = new SessionStorage();

	private Socket connection;

	public RequestHandler(final Socket connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		log.debug("Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());

		ViewResolver viewResolver = new ViewResolver();
		UserController userController = new UserController(new UserService(userRepository, sessionStorage));
		MethodHandler methodHandler = new MethodHandler(UserController.class, userController);

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
