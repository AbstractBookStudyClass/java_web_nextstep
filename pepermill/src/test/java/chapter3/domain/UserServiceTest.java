package chapter3.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chapter3.webapp.domain.UserService;
import chapter3.webapp.domain.model.User;
import chapter3.webapp.domain.repository.UserRepository;
import chapter3.webapp.web.storoage.SessionStorage;

public class UserServiceTest {

	private UserService userService;

	@BeforeEach
	void setUp() {
		UserRepository userRepository = new UserRepository();
		SessionStorage sessionStorage = new SessionStorage();
		userService = new UserService(userRepository, sessionStorage);

		userRepository.save(getRegisteredUser());
	}

	@Test
	@DisplayName("등록된 회원은 로그인이 가능한다.")
	void login_with_registered_user() {
		//given
		User user = getRegisteredUser();
		String username = user.getUserName();
		String password = user.getPassword();

		//when
		User expected = userService.login(username, password);

		//then
		Assertions.assertEquals(expected, user);
	}

	@Test
	@DisplayName("미등록된 유저는 로그인할 수 없다.")
	void reject_unsigned_user() {
		//given
		String username = "unsigned";
		String password = "123";

		//when
		User expected = userService.login(username, password);

		//then
		Assertions.assertEquals(expected, null);
	}

	private User getRegisteredUser() {
		return new User("Tester1", "123");
	}
}
