package chapter6.webapp.domain.repository;

import java.util.List;

import chapter6.webapp.domain.model.User;

public interface UserRepository {

	User findById(Integer userId);

	Integer save(User user);

	List<User> findAll();

	User findByUser(User user);

	void delete(User user);
}
