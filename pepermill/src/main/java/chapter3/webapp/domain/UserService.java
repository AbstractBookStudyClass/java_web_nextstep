package chapter3.webapp.domain;

import java.net.ServerSocket;

import chapter3.webapp.domain.model.User;
import chapter3.webapp.domain.repository.MemoryRepository;
import chapter3.webapp.domain.repository.SessionRepository;

public class UserService {

	private final MemoryRepository memoryRepository;
	private final SessionRepository sessionRepository;

	public UserService(final MemoryRepository memoryRepository,
		final SessionRepository sessionRepository) {
		this.memoryRepository = memoryRepository;
		this.sessionRepository = sessionRepository;
	}

	public void registerUser(String name, String password) {
		User user = new User(name, password);
		memoryRepository.save(user);
	}

	public User login(String name, String password) {
		User user = new User(name, password);
		User byName = memoryRepository.findByName(name);
		if (!user.equals(byName)) {
			throw new RuntimeException("존재하지 않는 계정입니다.");
		}
		sessionRepository.setSession(user);
		return user;
	}

	public void logout(String name, String password) {
		User user = new User(name, password);
		if(!sessionRepository.checkSession(user)) {
			throw new RuntimeException("로그인이 안된 회원입니다.");
		}
		sessionRepository.removeSession(user);
	}
}
