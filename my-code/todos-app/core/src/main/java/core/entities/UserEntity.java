package core.entities;

import java.sql.SQLException;
import java.util.UUID;

import core.dataaccess.IUsersDataAccess;
import core.dtos.SignInFormDto;
import core.dtos.SignUpFormDto;
import core.dtos.UserDbDto;
import core.exceptions.EmailAlreadyInUseException;
import core.exceptions.InvalidUserException;
import core.exceptions.PasswordDontMatchException;
import core.exceptions.ResourceOwnershipException;
import core.exceptions.UserNotFoundException;
import core.services.IJwtService;
import core.services.IPasswordService;
import core.utils.Constants;
import core.utils.EnvUtils;

public class UserEntity {
    public static void validateId(String id) throws InvalidUserException {
        if (id == null || id.isBlank()) {
            throw new InvalidUserException("UserId not provided. UserId is required and cannot be empty.");
        }
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidUserException("UserId not valid. UserId does not have a valid format.");
        }
    }

    public static void validateName(String name) throws InvalidUserException {
        if (name == null || name.isBlank()) {
            throw new InvalidUserException("Name not provided. Name is required and cannot be empty.");
        }
        if (name.length() < 3) {
            throw new InvalidUserException("Name is too short. Name must be at least 3 characters long.");
        }
        if (name.length() > 120) {
            throw new InvalidUserException("Name is too long. Name must be less than 120 characters long.");
        }
    }

    public static void validateEmail(String email) throws InvalidUserException {
        if (email == null || email.isBlank()) {
            throw new InvalidUserException("E-mail not provided. E-mail is required and cannot be empty.");
        }
        if (!email.matches(Constants.emailRegex)) {
            throw new InvalidUserException("E-mail not valid. E-mail does not have a valid format.");
        }
        if (email.length() < 6) {
            throw new InvalidUserException("E-mail is too short. E-mail must be at least 6 characters long.");
        }
    }

    public static void validatePhone(String phone) throws InvalidUserException {
        if (phone == null || phone.isBlank()) {
            throw new InvalidUserException("Phone not provided. Phone is required and cannot be empty.");
        }
        if (!phone.matches(Constants.phoneRegex)) {
            throw new InvalidUserException("Phone not valid. Phone does not have a valid format.");
        }
    }

    public static void validatePassword(String password) throws InvalidUserException {
        if (password == null || password.isBlank()) {
            throw new InvalidUserException("Password not provided. Password is required and cannot be empty.");
        }
        if (password.length() < 3) {
            throw new InvalidUserException("Password is too short. Password must be at least 3 characters long.");
        }
        if (password.length() > 32) {
            throw new InvalidUserException("Password is too long. Password must be less than 33 characters long.");
        }
    }

    public static void validateUser(SignUpFormDto form) throws InvalidUserException {
        validateName(form.name);
        validateEmail(form.email);
        validatePhone(form.phone);
        validatePassword(form.password);
    }

    public static void validateUser(SignInFormDto form) throws InvalidUserException {
        validateEmail(form.email);
        validatePassword(form.password);
    }

    public static void checkEmailIsAvailable(String email, IUsersDataAccess usersDataAccess)
            throws EmailAlreadyInUseException, SQLException {
        final var user = usersDataAccess.findUserByEmail(email);
        if (user.isPresent()) {
            throw new EmailAlreadyInUseException();
        }
    }

    public static String hashPassword(String password, IPasswordService passwordService) {
        return passwordService.hashPassword(password);
    }

    public static void createUser(SignUpFormDto form, String passwordHash, IUsersDataAccess usersDataAccess)
            throws SQLException {
        usersDataAccess.create(form, passwordHash);
    }

    public static UserDbDto findUserByEmail(String email, IUsersDataAccess usersDataAccess)
            throws SQLException, UserNotFoundException {
        final var userFound = usersDataAccess.findUserByEmail(email);
        if (!userFound.isPresent()) {
            throw new UserNotFoundException("User not found by e-mail");
        }
        return userFound.get();
    }

    public static void verifyPassword(String password, String passwordHash, IPasswordService passwordService)
            throws PasswordDontMatchException {
        final var isMatch = passwordService.verifyPassword(password, passwordHash);
        if (!isMatch) {
            throw new PasswordDontMatchException();
        }
    }

    public static String createSignInToken(String userId, IJwtService jwtService) {
        final var secret = EnvUtils.getJwtSecretKey();
        return jwtService.createSignInToken(userId, Constants.jwtDuration, secret);
    }

    public static void checkUserExistsById(String userId, IUsersDataAccess usersDataAccess)
            throws UserNotFoundException, SQLException {
        final var user = usersDataAccess.findUserById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found by id");
        }
    }

    public static void checkIsTheSameUser(String userId, String authUserId) throws ResourceOwnershipException {
        if (!userId.equals(authUserId)) {
            throw new ResourceOwnershipException("User id provided is not the same of the user id authenticated");
        }
    }
}
