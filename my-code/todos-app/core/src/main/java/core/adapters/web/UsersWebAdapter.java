package core.adapters.web;

import core.dtos.AdaptedWebResponse;
import core.dtos.SignUpFormDto;
import core.exceptions.EmailAlreadyInUseException;
import core.exceptions.InvalidUserException;
import core.usecases.users.SignUpUserUseCase;

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
        //return AdaptedWebResponse.of(201, "Hello, World!");
    }
}
