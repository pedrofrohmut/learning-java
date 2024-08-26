package core.adapters.web;

import core.dtos.AdaptedWebResponse;
import core.dtos.SignUpFormDto;
import core.dtos.SignInFormDto;
import core.exceptions.EmailAlreadyInUseException;
import core.exceptions.InvalidUserException;
import core.exceptions.PasswordDontMatchException;
import core.exceptions.UserNotFoundException;
import core.usecases.users.SignUpUserUseCase;
import core.usecases.users.SignInUserUseCase;

public class UsersWebAdapter {
    public static AdaptedWebResponse signUpUser(SignUpUserUseCase useCase, SignUpFormDto form) {
        try {
            useCase.execute(form);
            return AdaptedWebResponse.of(201, null);
        } catch (InvalidUserException | EmailAlreadyInUseException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }

    public static AdaptedWebResponse signInUser(SignInUserUseCase useCase, SignInFormDto form) {
        try {
            var signedUser = useCase.execute(form);
            return AdaptedWebResponse.of(200, signedUser);
        } catch (InvalidUserException | UserNotFoundException | PasswordDontMatchException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }
}
