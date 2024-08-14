package webapi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utils.UseCasesFactory;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @PostMapping("signup")
    public void signUp() {
        String foo = UseCasesFactory.getSignUpUseCase();
        System.out.println(foo);
    }

    @PostMapping("signin")
    public String signIn() {
        return "Sign in";
    }

}
