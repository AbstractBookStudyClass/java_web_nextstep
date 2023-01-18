package chapter3.webapp.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter3.webapp.domain.model.User;

public class MemoryRepository {

	private static final Logger log = LoggerFactory.getLogger(MemoryRepository.class);

	private Map<Integer, User> memoryStorage = new ConcurrentHashMap<>();
	private AtomicInteger id = new AtomicInteger(1);

	public Integer save(User user) {
		memoryStorage.put(id.get(), user);
		log.debug("save user : " + user.toString());
		return id.getAndIncrement();
	}

	public Optional<User> findById(Integer id) {
		User user = memoryStorage.get(id);
		log.debug("find user : " + user.toString());
		return Optional.of(user);
	}

	public List<User> findAll() {
		return memoryStorage.entrySet()
			.stream()
			.map(entry -> entry.getValue())
			.collect(Collectors.toList());
	}

	public User findByName(String name) {
		return memoryStorage.entrySet()
			.stream()
			.filter(entry -> entry.getValue().getUserName().equals(name))
			.findFirst()
			.get().getValue();
	}
}
