package org.campus.webshop.dao.jdbc.mapper;

import org.campus.webshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {
    public User mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String sole = resultSet.getString("sole");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");


        User user = User.builder().
                id(id)
                .email(email)
                .password(password)
                .sole(sole)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        return user;
    }

}
