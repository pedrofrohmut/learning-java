package pedrofrohmut.todos.webapi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @PostMapping("signup")
    public String signUp() {
        return "Sign up";
    }

    @PostMapping("signin")
    public String signIn() {
        return "Sign in";
    }

}
