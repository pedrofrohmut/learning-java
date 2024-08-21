package core.dataaccess;

import java.sql.SQLException;
import java.util.Optional;

import core.dtos.UserDbDto;

public interface IUsersDataAccess {
    Optional<UserDbDto> findUserByEmail(String email) throws SQLException;
}
