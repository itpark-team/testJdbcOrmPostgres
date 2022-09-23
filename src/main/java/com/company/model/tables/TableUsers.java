package com.company.model.tables;

import com.company.model.entities.User;
import com.company.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableUsers {

    private static Logger logger = LoggerFactory.getLogger(TableUsers.class);

    private Connection connection;

    public TableUsers(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> users = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM users ORDER BY id ASC");

        ResultSet resultSet = statement.executeQuery(selectQuery);

        logger.debug("select users from table");

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            int countryId = resultSet.getInt("country_id");

            users.add(new User(id, name, age, countryId));
        }

        logger.debug("all users add to List<User>");

        resultSet.close();

        statement.close();

        logger.debug("resultSet.close and statement.close");

        return users;
    }

    public void addNewUser(User user) throws SQLException {
        Statement statement = connection.createStatement();

        String insertQuery = String.format("INSERT INTO users (name, country_id, age)\n" +
                "VALUES ('%s',%d,%d)", user.getName(), user.getCountryId(), user.getAge());

        statement.executeUpdate(insertQuery);

        statement.close();
    }

    public void deleteUser(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String deleteQuery = String.format("DELETE FROM users WHERE id = %d", id);

        statement.executeUpdate(deleteQuery);
        statement.close();
    }

    public void updateUser(User user) throws SQLException {
        Statement statement = connection.createStatement();
        String updateQuery = String.format("UPDATE users set name='%s', age=%d, country_id=%d WHERE id = %d", user.getName(), user.getAge(), user.getCountryId(), user.getId());

        statement.executeUpdate(updateQuery);
        statement.close();
    }

    public User getUser(int searchId) throws SQLException {
        User user = null;

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM users WHERE id = %d", searchId);

        ResultSet resultSet = statement.executeQuery(selectQuery);

        resultSet.next();

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        int countryId = resultSet.getInt("country_id");

        user = new User(id, name, age, countryId);

        resultSet.close();

        statement.close();

        return user;
    }
}
