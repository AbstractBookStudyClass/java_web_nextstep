package chapter6.webapp.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter6.webapp.web.annotation.GetMapping;
import chapter6.webapp.web.http.HttpRequest;
import chapter6.webapp.web.http.HttpResponse;

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
	}
}
