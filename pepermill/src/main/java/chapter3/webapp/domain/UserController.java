package chapter3.webapp.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter3.webapp.web.annotation.GetMapping;
import chapter3.webapp.web.annotation.PostMapping;
import chapter3.webapp.domain.model.User;
import chapter3.webapp.web.HttpStatusCode;
import chapter3.webapp.web.RequestForm;
import chapter3.webapp.web.View;

public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public View home() {
		View view = new View();
		view.setHttpStatusCode(HttpStatusCode.OK.getHttpStatusCode());
		view.setUrl("/index.html");
		logger.debug("home method called");
		return view;
	}

	@GetMapping("/user/login.html")
	public View loginPage() {
		View view = new View();
		view.setHttpStatusCode(HttpStatusCode.OK.getHttpStatusCode());
		view.setLocation("/user/login.html");
		logger.debug("return user login page");
		return view;
	}

	@GetMapping("/user/login-fail.html")
	public View loginFailPage() {
		View view = new View();
		view.setHttpStatusCode(HttpStatusCode.OK.getHttpStatusCode());
		view.setHttpStatusDescription(HttpStatusCode.OK.getHttpStatusDescription());
		view.setLocation("/user/login-fail.html");
		logger.debug("return user login page");
		return view;
	}

	@PostMapping("/user/login")
	public View login(RequestForm form) {
		User user = userService.login(form.getName(), form.getPassword());
		View view = new View();
		if (user != null) {
			view.setHttpStatusCode(HttpStatusCode.FOUND.getHttpStatusCode());
			view.setHttpStatusDescription(HttpStatusCode.FOUND.getHttpStatusDescription());
			view.setLocation("/index.html");
			view.setCookie("login=true");
			logger.debug("user {}, password {} login", user.getUserName(), user.getPassword());
		} else {
			// 일단 200으로 반환
			view.setHttpStatusDescription(HttpStatusCode.FORBIDDEN.getHttpStatusDescription());
			view.setHttpStatusCode(HttpStatusCode.FORBIDDEN.getHttpStatusCode());
			view.setCookie("login=false");
			view.setLocation("/user/login-fail.html");
			logger.debug("!!! login fail !!!");
		}

		return view;
	}

	@GetMapping("/user/form.html")
	public View createPage() {
		View view = new View();
		view.setHttpStatusDescription(HttpStatusCode.OK.getHttpStatusDescription());
		view.setHttpStatusDescription(HttpStatusCode.OK.getHttpStatusDescription());
		view.setLocation("/user/form.html");
		logger.debug("return user create page");
		return view;
	}

	@GetMapping("/user/create")
	public View registerGet(RequestForm form) {
		userService.registerUser(form.getName(), form.getPassword());
		View view = new View();
		view.setHttpStatusCode(HttpStatusCode.FOUND.getHttpStatusCode());
		view.setHttpStatusDescription(HttpStatusCode.FOUND.getHttpStatusDescription());
		view.setLocation("/index.html");
		logger.debug("registerGet method called");
		return view;
	}

	@PostMapping("/user/create")
	public View registerPost(RequestForm form) {
		userService.registerUser(form.getName(), form.getPassword());
		View view = new View();
		view.setHttpStatusCode(HttpStatusCode.FOUND.getHttpStatusCode());
		view.setHttpStatusDescription(HttpStatusCode.FOUND.getHttpStatusDescription());
		view.setLocation("/index.html");
		logger.debug("registerPost method called");
		return view;
	}

	@GetMapping("/user/list")
	public View getUserListPage() {
		View view = new View();
		List<User> userList = userService.getUserList();
		view.setHttpStatusCode(HttpStatusCode.OK.getHttpStatusCode());
		view.setHttpStatusDescription(HttpStatusCode.OK.getHttpStatusDescription());
		view.setBody(userList.toString());
		return view;
	}
}
