package core.entities;

import java.sql.SQLException;

import core.dataaccess.IUsersDataAccess;
import core.dtos.SignUpFormDto;
import core.exceptions.EmailAlreadyInUseException;
import core.exceptions.InvalidUserException;
import core.utils.Constants;

public class UserEntity {
    public static void validateName(String name) throws InvalidUserException {
        if (name == null || name.isBlank()) {
            throw new InvalidUserException("Name not provided. Name is required and cannot be empty.");
        }
        if (name.length() < 3) {
            throw new InvalidUserException("Name is too short. Name must be at least 3 characters long.");
        }
        if (name.length() > 120) {
            throw new InvalidUserException("Name is too long. Name less than 120 characters long.");
        }
    }

    public static void validateEmail(String email) throws InvalidUserException {
        if (email == null || email.isBlank()) {
            throw new InvalidUserException("E-mail not provided. E-mail is required and cannot be empty.");
        }
        if (!email.matches(Constants.emailRegex)) {
            throw new InvalidUserException("E-mail not valid. E-mail does not have a valid format");
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
            throw new InvalidUserException("Password is too long. Password must less than 33 characters long.");
        }
    }

    public static void validateUser(SignUpFormDto form) throws InvalidUserException {
        validateName(form.name);
        validateEmail(form.email);
        validatePhone(form.phone);
        validatePassword(form.password);
    }

    public static void checkEmailIsAvailable(String email, IUsersDataAccess usersDataAccess) throws EmailAlreadyInUseException, SQLException {
        var user = usersDataAccess.findUserByEmail(email);
        if (user.isPresent()) {
            throw new EmailAlreadyInUseException();
        }
    }
}
