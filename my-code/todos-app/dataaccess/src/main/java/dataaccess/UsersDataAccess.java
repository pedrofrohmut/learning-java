package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import core.dataaccess.IUsersDataAccess;
import core.dtos.SignUpFormDto;
import core.dtos.UserDbDto;

@Service
public class UsersDataAccess implements IUsersDataAccess {

    @Autowired
    @Qualifier("dataSource")
    private final DataSource dataSource;
    private final Connection connection;

    public UsersDataAccess(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            this.connection = this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Problem with the database connection. With message: " + e.getMessage());
        }
    }

    @Override
    public Optional<UserDbDto> findUserByEmail(String email) throws SQLException {
        final var sql = "SELECT id, name, email, phone, password_hash FROM users WHERE email = ?";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, email);
            try (final var rs = stm.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                final var user = new UserDbDto();
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
        final var sql = "INSERT INTO users (name, email, phone, password_hash) VALUES (?, ?, ?, ?)";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, form.name);
            stm.setString(2, form.email);
            stm.setString(3, form.phone);
            stm.setString(4, passwordHash);
            stm.execute();
        }
    }

    @Override
    public Optional<UserDbDto> findUserById(String userId) throws SQLException {
        final var sql = "SELECT id, name, email, phone, password_hash FROM users WHERE id = ?";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setObject(1, UUID.fromString(userId));
            try (final var rs = stm.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                final var user = new UserDbDto();
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
