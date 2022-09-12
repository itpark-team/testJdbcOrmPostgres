package com.company.model;

import com.company.model.connection.DbConnection;
import com.company.model.tables.TableUsers;

import java.sql.Connection;
import java.sql.SQLException;

public class DbManager {
    private TableUsers tableUsers;

    private DbManager() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        tableUsers = new TableUsers(connection);
    }

    public TableUsers getTableUsers() {
        return tableUsers;
    }

    private static DbManager instance;

    public static DbManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }
}
