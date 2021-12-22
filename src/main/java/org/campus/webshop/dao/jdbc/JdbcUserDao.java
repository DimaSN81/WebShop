package org.campus.webshop.dao.jdbc;

import org.apache.commons.codec.digest.DigestUtils;
import org.campus.webshop.dao.UserDao;
import org.campus.webshop.dao.jdbc.mapper.UserRowMapper;
import org.campus.webshop.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JdbcUserDao extends JdbcProcessor implements UserDao {
    private static final String FIND_BY_EMAIL_SQL = "SELECT id, email, password, sole, firstname, lastname FROM users WHERE email = ?";
    private static final String ADD_SQL = "INSERT INTO users(email, password, sole, firstname, lastname) VALUES (?, ?, ?, ?, ?);";
    private static final UserRowMapper USERS_ROW_MAPPER = new UserRowMapper();

    @Override
    public User findByEmail(String email) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_SQL); ) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next()) {
                user = USERS_ROW_MAPPER.mapRow(resultSet);
            }

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User add(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SQL);) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, DigestUtils.md5Hex(user.getSole() + user.getPassword()));
            preparedStatement.setString(3, user.getSole());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.executeUpdate();

            return findByEmail(user.getEmail());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
