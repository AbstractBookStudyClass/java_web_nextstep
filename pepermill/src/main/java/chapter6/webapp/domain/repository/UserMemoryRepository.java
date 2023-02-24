package chapter6.webapp.domain.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter6.webapp.domain.model.User;
import chapter6.webapp.web.storoage.InMemoryStorage;
import chapter6.webapp.web.storoage.Storage;

public class UserMemoryRepository implements UserRepository {

	private static final Logger log = LoggerFactory.getLogger(UserMemoryRepository.class);

	private Storage<User> memoryStorage = new InMemoryStorage<>();

	@Override
	public Integer save(User user) {
		log.debug("save user : " + user.toString());
		return memoryStorage.save(user);
	}

	@Override
	public User findById(Integer id) {
		User user = memoryStorage.load(id);
		log.debug("find user : " + user.toString());
		return user;
	}

	@Override
	public List<User> findAll() {
		return memoryStorage.getAllValues();
	}

	@Override
	public User findByUser(User user) {
		return findAll().stream()
			.filter(e -> e.equals(user))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
	}

	@Override
	public void delete(final User user) {
		memoryStorage.delete(user);
	}
}
