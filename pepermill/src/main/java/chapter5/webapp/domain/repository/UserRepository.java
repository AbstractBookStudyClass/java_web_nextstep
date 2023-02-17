package chapter5.webapp.domain.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter5.webapp.domain.model.User;
import chapter5.webapp.web.storoage.InMemoryStorage;
import chapter5.webapp.web.storoage.Storage;

public class UserRepository {

	private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

	private Storage<User> memoryStorage = new InMemoryStorage<>();

	public Integer save(User user) {
		log.debug("save user : " + user.toString());
		return memoryStorage.save(user);
	}

	public User findById(Integer id) {
		User user = memoryStorage.load(id);
		log.debug("find user : " + user.toString());
		return user;
	}

	public List<User> findAll() {
		return memoryStorage.getAllValues();
	}

	public User findByUser(User user) {
		return findAll().stream()
			.filter(e -> e.equals(user))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
	}
}
