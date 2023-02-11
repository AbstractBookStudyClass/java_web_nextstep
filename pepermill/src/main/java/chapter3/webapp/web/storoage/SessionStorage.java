package chapter3.webapp.web.storoage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter3.webapp.domain.model.User;

public class SessionStorage {

	private static final Logger log = LoggerFactory.getLogger(SessionStorage.class);

	private Map<Integer, User> memoryStorage = new ConcurrentHashMap<>();
	private AtomicInteger id = new AtomicInteger(1);

	public void setSession(User user) {
		memoryStorage.put(id.get(), user);
		id.incrementAndGet();
		log.debug("User - {} has been singed in.", user);
	}

	public void removeSession(User user) {
		Integer id = memoryStorage.entrySet()
			.stream()
			.filter(entry -> entry.getValue().equals(user))
			.findFirst()
			.get().getKey();
		memoryStorage.remove(id);
		log.debug("Sign out : {}", user);
	}

	public boolean checkSession(User user) {
		return memoryStorage.containsValue(user);
	}
}
