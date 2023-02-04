package chapter3.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chapter3.webapp.domain.model.User;
import chapter3.webapp.domain.repository.UserRepository;

public class UserRepositoryTest {

	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userRepository = new UserRepository();
	}

	@Test
	@DisplayName("사용자를 저장한다.")
	void save_user() {
		//given
		String name = "tester1";
		String password = "123";
		User user = new User(name, password);

		//when
		Integer storedId = userRepository.save(user);

		//then
		Assertions.assertEquals(0, storedId);
	}

	@Test
	@DisplayName("Id로 사용자를 조회할 수 있다.")
	void find_user_by_id() {
		//given
		String name = "tester1";
		String password = "123";
		User user = new User(name, password);

		//when
		Integer storedId = userRepository.save(user);
		User expected = userRepository.findById(storedId);

		//then
		Assertions.assertEquals(expected, user);
	}

	@ParameterizedTest
	@MethodSource("provideMultipleUsers")
	@DisplayName("특정 유저를 검색할 수 있다.")
	void find_specified_user(List<User> users, int count) {
		//given
		User user = new User(String.valueOf(count-1), "123");

		//when
		users.forEach(u -> userRepository.save(u));
		User expected = userRepository.findByUser(user);

		//then
		Assertions.assertEquals(expected, user);
	}

	@ParameterizedTest
	@MethodSource("provideMultipleUsers")
	@DisplayName("여러 사용자를 조회할 수 있다.")
	void find_multiple_users(List<User> users, int count) {
		//when
		users.forEach(user -> userRepository.save(user));
		List<User> expected = userRepository.findAll();

		//then
		Assertions.assertEquals(expected.size(), count);
	}

	private static Stream<Arguments> provideMultipleUsers() {
		return Stream.of(
			Arguments.of(getUserList(1), 1),
			Arguments.of(getUserList(2), 2),
			Arguments.of(getUserList(3), 3)
		);
	}

	private static List<User> getUserList(int count) {
		List<User> result = new ArrayList<>(count);
		for (int i=0; i<count; i++) {
			result.add(new User(String.valueOf(i), "123"));
		}
		return result;
	}
}
