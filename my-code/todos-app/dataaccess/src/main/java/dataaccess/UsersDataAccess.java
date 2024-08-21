package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import core.dataaccess.IUsersDataAccess;
import core.dtos.UserDbDto;

public class UsersDataAccess implements IUsersDataAccess {
    private final Connection connection;

    public UsersDataAccess(Connection connection) {
        this.connection = connection;
    }

	@Override
	public Optional<UserDbDto> findUserByEmail(String email) throws SQLException {
        var sql = "SELECT id, name, email, phone, password_hash FROM users WHERE email = ?";
        var stm = this.connection.prepareStatement(sql);
        stm.setString(1, email);
        var rs = stm.executeQuery();
        if (!rs.next()) {
            return Optional.empty();
        }
        var user = new UserDbDto();
        user.id = rs.getString("id");
        user.name = rs.getString("name");
        user.email = rs.getString("email");
        user.phone = rs.getString("phone");
        user.passwordHash = rs.getString("password_hash");
        return Optional.of(user);
	}
}
