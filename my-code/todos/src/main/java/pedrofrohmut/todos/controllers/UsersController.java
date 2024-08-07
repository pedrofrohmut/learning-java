package pedrofrohmut.todos.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @PostMapping
    public String signUp() {
        return "sign up";
    }

    @PostMapping
    public String signIn() {
        return "sign in";
    }

}
