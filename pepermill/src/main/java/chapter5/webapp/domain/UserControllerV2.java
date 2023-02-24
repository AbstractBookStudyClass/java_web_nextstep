package chapter5.webapp.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter5.webapp.web.annotation.GetMapping;
import chapter5.webapp.web.http.HttpRequest;
import chapter5.webapp.web.http.HttpResponse;

public class UserControllerV2 {

	private static final Logger log = LoggerFactory.getLogger(UserControllerV2.class);

	private final UserService userService;

	public UserControllerV2(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public void home(HttpRequest request, HttpResponse response) {
		log.debug("controllev2 is called");
		response.setContentType("text/html");
	}
}
