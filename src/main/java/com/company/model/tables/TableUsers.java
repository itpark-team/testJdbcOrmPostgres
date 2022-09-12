package com.company.model.tables;

import com.company.model.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableUsers {
    private Connection connection;

    public TableUsers(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM users ORDER BY id ASC");

        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            int countryId = resultSet.getInt("country_id");

            users.add(new User(id, name, age, countryId));
        }
        resultSet.close();

        statement.close();

        return users;
    }
}
