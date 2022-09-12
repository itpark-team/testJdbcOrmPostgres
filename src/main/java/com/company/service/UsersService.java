package com.company.service;

import com.company.model.DbManager;
import com.company.model.entities.User;

import java.util.List;

public class UsersService {

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
            return dbManager.getTableUsers().getAllUsers();
        } catch (Exception e) {
            throw new Exception("Ошибка работы с базой данных: " + e.getMessage());
        }
    }
}
