package chapter5.webapp.domain;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter5.webapp.domain.model.User;
import chapter5.webapp.domain.repository.UserRepository;
import chapter5.webapp.web.storoage.SessionStorage;

public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	private final SessionStorage sessionStorage;

	public UserService(final UserRepository userRepository, final SessionStorage sessionStorage) {
		this.userRepository = userRepository;
		this.sessionStorage = sessionStorage;
	}

	public void registerUser(String name, String password) {
		User user = new User(name, password);
		userRepository.save(user);
	}

	public User login(String name, String password) {
		User user = new User(name, password);
		try {
			User storedUser = userRepository.findByUser(user);
		} catch (NoSuchElementException e) {
			log.error("!!!! NO USER!!!");
			return null;
		}
		sessionStorage.setSession(user);
		return user;
	}

	public void logout(String name, String password) {
		User user = new User(name, password);
		if(!sessionStorage.checkSession(user)) {
			throw new RuntimeException("로그인이 안된 회원입니다.");
		}
		sessionStorage.removeSession(user);
	}

	public List<User> getUserList() {
		return userRepository.findAll();
	}
}
