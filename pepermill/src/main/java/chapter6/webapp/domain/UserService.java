package chapter6.webapp.domain;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter6.webapp.domain.model.User;
import chapter6.webapp.domain.repository.UserRepository;
import chapter6.webapp.web.storoage.session.HttpSessionStorage;

public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	private static final String LOGIN_USER = "login-user";

	private final UserRepository userRepository;
	private final HttpSessionStorage sessionStorage;

	public UserService(final UserRepository userRepository,
					   final HttpSessionStorage sessionStorage) {
		this.userRepository = userRepository;
		this.sessionStorage = sessionStorage;
	}

	public void registerUser(String name, String password) {
		User user = new User(name, password);
		userRepository.save(user);
	}

	public User login(String name, String password) {
		User user =  null;
		try {
			user = userRepository.findByUser(new User(name, password));
		} catch (NoSuchElementException e) {
			log.error("!!!! NO USER!!!");
			return user;
		}
		sessionStorage.setAttribute(LOGIN_USER, user);
		return user;
	}

	public void logout(String name, String password) {
		User user = new User(name, password);
		if(!sessionStorage.containAttribute(LOGIN_USER)) {
			throw new RuntimeException("로그인이 안된 회원입니다.");
		}
		sessionStorage.removeSessionId();
	}

	public List<User> getUserList() {
		return userRepository.findAll();
	}
}
