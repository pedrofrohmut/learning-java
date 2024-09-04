package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import core.dataaccess.IUsersDataAccess;
import core.dtos.SignUpFormDto;
import core.dtos.UserDbDto;

public class UsersDataAccess implements IUsersDataAccess {
    private final Connection connection;

    public UsersDataAccess(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<UserDbDto> findUserByEmail(String email) throws SQLException {
        var sql = "SELECT id, name, email, phone, password_hash FROM users WHERE email = ?";
        try (var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, email);
            try (var rs = stm.executeQuery()) {
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
    }

	@Override
	public void create(SignUpFormDto form, String passwordHash) throws SQLException {
        var sql = "INSERT INTO users (name, email, phone, password_hash) VALUES (?, ?, ?, ?)";
        try (var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, form.name);
            stm.setString(2, form.email);
            stm.setString(3, form.phone);
            stm.setString(4, passwordHash);
            stm.execute();
        }
	}

	@Override
	public Optional<UserDbDto> findUserById(String userId) throws SQLException {
        var sql = "SELECT id, name, email, phone, password_hash FROM users WHERE id = ?";
        try (var stm = this.connection.prepareStatement(sql)) {
            stm.setObject(1, UUID.fromString(userId));
            try (var rs = stm.executeQuery()) {
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
	}

}
