package core.adapters.web;

import core.dtos.AdaptedWebResponse;
import core.dtos.SignUpFormDto;
import core.usecases.users.SignUpUserUseCase;

public class UsersWebAdapter {
    public static AdaptedWebResponse signUpUser(SignUpUserUseCase useCase, SignUpFormDto form) {
        return AdaptedWebResponse.of(201, "Hello, World!");
    }
}
