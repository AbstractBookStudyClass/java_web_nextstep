package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.Logger;
import core.jdbc.ConnectionManager;
import next.model.User;
import next.support.JdbcTemplate;
import next.support.PreparedStatementSetter;
import next.support.RowMapper;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(sql, user.getUserId(), user.getPassword(),
                user.getName(), user.getEmail());
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
        jdbcTemplate.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
            }
        };
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        };

        String sql = "SELECT userId, password, name, email FROM USERS";
        return jdbcTemplate.executeQuery(sql, pss, rm);
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        };

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
        return jdbcTemplate.executeQueryObject(sql, pss, rm);
    }
}
