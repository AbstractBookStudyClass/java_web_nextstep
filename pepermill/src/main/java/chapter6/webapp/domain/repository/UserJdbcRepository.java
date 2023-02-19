package chapter6.webapp.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter6.webapp.domain.model.User;
import chapter6.webapp.web.storoage.jdbc.JdbcConnectionManager;

public class UserJdbcRepository implements UserRepository {
	
	private static final Logger log = LoggerFactory.getLogger(UserJdbcRepository.class);

	private final JdbcConnectionManager manager;

	public UserJdbcRepository(final JdbcConnectionManager manager) {
		this.manager = manager;
	}

	@Override
	public User findById(Integer userId) {
		try (Connection connection = manager.getConnection();
			 PreparedStatement preparedStatement = getStatementFindById(connection, userId);
			 ResultSet resultSet = preparedStatement.executeQuery();) {

			User user = null;
			if (resultSet.next()) {
				user = new User(
					resultSet.getInt("id"),
					resultSet.getString("username"),
					resultSet.getString("password")
				);
			}
			return user;
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new IllegalArgumentException(e);
		}
	}

	private PreparedStatement getStatementFindById(final Connection connection, final Integer userId) throws
		SQLException {
		String sql = "SELECT id, username, password FROM users WHERE id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		return preparedStatement;
	}

	@Override
	public Integer save(final User user) {
		try (Connection connection = manager.getConnection();
			 PreparedStatement preparedStatement = getStatementSaveUser(connection, user);) {

			preparedStatement.executeUpdate();
			User byUser = findByUser(user);
			return byUser.getUserId();
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new IllegalArgumentException(e);
		}
	}

	private PreparedStatement getStatementSaveUser(final Connection connection, final User user) throws SQLException {
		String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getUserName());
		preparedStatement.setString(2, user.getPassword());
		return preparedStatement;
	}

	@Override
	public List<User> findAll() {
		try (Connection connection = manager.getConnection();
			 PreparedStatement preparedStatement = getStatementFindAll(connection);
			 ResultSet resultSet = preparedStatement.executeQuery();) {

			List<User> list = new ArrayList<>();
			while(resultSet.next()) {
				User user = new User(
					resultSet.getInt("id"),
					resultSet.getString("username"),
					resultSet.getString("password")
				);
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new IllegalArgumentException(e);
		}
	}

	private PreparedStatement getStatementFindAll(Connection connection) throws SQLException {
		String sql = "SELECT id, username, password FROM users";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement;
	}

	@Override
	public User findByUser(final User user) {
		try (Connection connection = manager.getConnection();
			 PreparedStatement preparedStatement = getStatementFindByUser(connection, user);
			 ResultSet resultSet = preparedStatement.executeQuery();) {

			User resultUser = null;
			if (resultSet.next()) {
				resultUser = new User(
					resultSet.getInt("id"),
					resultSet.getString("username"),
					resultSet.getString("password")
				);
			} else {
				throw new NoSuchElementException();
			}
			return resultUser;
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new IllegalArgumentException(e);
		}
	}

	private PreparedStatement getStatementFindByUser(final Connection connection, User user) throws SQLException {
		String sql = "SELECT id, username, password FROM users WHERE username=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getUserName());
		return preparedStatement;
	}

	@Override
	public void delete(final User user) {

	}
}
