package dataaccess;

import java.sql.Connection;

import core.dataaccess.IUsersDataAccess;

public class UsersDataAccess implements IUsersDataAccess {
    private final Connection connection;

    public UsersDataAccess(Connection connection) {
        this.connection = connection;
    }
}
