package chapter6.webapp.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter6.webapp.domain.model.User;
import chapter6.webapp.web.annotation.GetMapping;
import chapter6.webapp.web.annotation.PostMapping;
import chapter6.webapp.web.http.HttpQueryParameters;
import chapter6.webapp.web.http.HttpRequest;
import chapter6.webapp.web.http.HttpResponse;
import chapter6.webapp.web.http.HttpStatusCode;

public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public void home(HttpRequest request, HttpResponse response) {
		log.debug("controller is called");
		response.setContentType("text/html");
		response.setHtmlLocation("/index.html");
	}

	@GetMapping("/css/styles.css")
	public void cssPage(HttpRequest request, HttpResponse response) {
		response.setCookie("text/css");
		response.setHtmlLocation("/css/styles.css");
	}

	@GetMapping("/user/login.html")
	public void loginPage(HttpRequest request, HttpResponse response) {
		response.setContentType("text/html");
		response.setHtmlLocation("/user/login.html");
	}

	@GetMapping("/user/login-fail.html")
	public void loginFailPage(HttpRequest request, HttpResponse response) {
		response.setContentType("text/html");
		response.setHtmlLocation("/user/login-fail.html");
	}

	@GetMapping("/user/form.html")
	public void createUserPage(HttpRequest request, HttpResponse response) {
		response.setContentType("text/html");
		response.setHtmlLocation("/user/form.html");
	}

	@GetMapping("/favicon.ico")
	public void favicon(HttpRequest request, HttpResponse response) {
		response.setContentType("text/html");
		response.setHtmlLocation("/favicon.ico");
	}

	@PostMapping("/user/login")
	public void login(HttpRequest request, HttpResponse response) {
		HttpQueryParameters httpQueryParameters = request.getHttpQueryParameters();
		User user = userService.login(
			httpQueryParameters.getValue("name"), httpQueryParameters.getValue("password")
		);
		if (user != null) {
			response.setContentType("text/html");
			response.setHttpStatusCode(HttpStatusCode.FOUND);
			response.setHttpHeader("Location", "http://localhost:8080/index.html");
		} else {
			response.setContentType("text/html");
			response.setHttpStatusCode(HttpStatusCode.FOUND);
			response.setHttpHeader("Location", "http://localhost:8080/user/login-fail.html");
		}
	}
}
