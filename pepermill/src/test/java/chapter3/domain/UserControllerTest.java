package chapter3.domain;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import chapter3.webapp.domain.UserController;
import chapter3.webapp.domain.UserService;
import chapter3.webapp.domain.model.User;
import chapter3.webapp.web.HttpStatusCode;
import chapter3.webapp.web.RequestForm;
import chapter3.webapp.web.View;

public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		userController = new UserController(userService);
	}

	@Test
	@DisplayName("로그인에 성공하면 login=true 쿠키와 FOUND 응답 코드를 전달한다.")
	void success_login_with_cookie_and_FOUND_code() {
		//given
		RequestForm requestForm = getRequestForm();
		when(userService.login(anyString(), anyString()))
			.thenReturn(new User(requestForm.getName(), requestForm.getPassword()));

		//when
		View login = userController.login(requestForm);

		//then
		Assertions.assertEquals(login.getHttpStatusCode(), HttpStatusCode.FOUND.getHttpStatusCode());
		Assertions.assertEquals(login.getCookie(), "login=true");
	}

	@Test
	@DisplayName("로그인에 실패하면 login=false 쿠키와 FORBIDDEN 응답 코드를 전달한다.")
	void fail_login_with_cookie_and_FORBIDDEN_code() {
		//given
		RequestForm requestForm = getRequestForm();
		when(userService.login(anyString(), anyString()))
			.thenReturn(null);

		//when
		View login = userController.login(requestForm);

		//then
		Assertions.assertEquals(login.getHttpStatusCode(), HttpStatusCode.FORBIDDEN.getHttpStatusCode());
		Assertions.assertEquals(login.getCookie(), "login=false");
	}

	private RequestForm getRequestForm() {
		return new RequestForm("Tester1", "123");
	}
}
