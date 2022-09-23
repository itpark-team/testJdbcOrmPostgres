package com.company.model;

import com.company.Main;
import com.company.model.connection.DbConnection;
import com.company.model.tables.TableCountries;
import com.company.model.tables.TableUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DbManager {
    private static Logger logger = LoggerFactory.getLogger(DbManager.class);

    private TableUsers tableUsers;
    private TableCountries tableCountries;

    private DbManager() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        tableUsers = new TableUsers(connection);
        tableCountries = new TableCountries(connection);

        logger.info("connection, tableUsers, tableCountries initialized");
    }

    public TableUsers getTableUsers() {
        return tableUsers;
    }

    public TableCountries getTableCountries() {
        return tableCountries;
    }

    private static DbManager instance;

    public static DbManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }
}
