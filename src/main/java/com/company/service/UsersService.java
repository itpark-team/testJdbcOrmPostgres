package com.company.service;

import com.company.model.DbManager;
import com.company.model.entities.Country;
import com.company.model.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UsersService {

    private static Logger logger = LoggerFactory.getLogger(UsersService.class);

    private DbManager dbManager;

    public UsersService() throws Exception {
        try {
            dbManager = DbManager.getInstance();
        } catch (Exception e) {
            throw new Exception("Ошибка инициализации базы данных: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() throws Exception {
        try {
            List<User> users = dbManager.getTableUsers().getAllUsers();
            List<Country> countries = dbManager.getTableCountries().getAllCountries();

            users.forEach(
                    user -> user.setCountry(countries.stream().filter(country -> country.getId() == user.getCountryId()).findFirst().get())
            );

            logger.info("get all users correct");

            return users;

        } catch (Exception e) {

            String errorMessage = "Ошибка получения пользователей из БД: " + e.getMessage();

            logger.error("get all users correct with error: {}",errorMessage);

            throw new Exception(errorMessage);
        }
    }

    public User getUser(int id) throws Exception {
        try {
            return dbManager.getTableUsers().getUser(id);
        } catch (Exception e) {
            throw new Exception("Ошибка получения пользователя из БД: " + e.getMessage());
        }
    }

    public void addNewUser(User user) throws Exception {
        try {
            dbManager.getTableUsers().addNewUser(user);
        } catch (Exception e) {
            throw new Exception("Ошибка добавления пользователя в БД: " + e.getMessage());
        }
    }

    public void deleteUser(int id) throws Exception {
        try {
            dbManager.getTableUsers().deleteUser(id);
        } catch (Exception e) {
            throw new Exception("Ошибка удаления пользователя из БД: " + e.getMessage());
        }
    }

    public void updateUser(User user) throws Exception {
        try {
            dbManager.getTableUsers().updateUser(user);
        } catch (Exception e) {
            throw new Exception("Ошибка обновления пользователя в БД: " + e.getMessage());
        }
    }
}
