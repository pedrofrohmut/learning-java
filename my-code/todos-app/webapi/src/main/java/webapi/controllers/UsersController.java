package webapi.controllers;

import java.sql.Connection;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dataaccess.ConnectionManager;
import utils.UseCasesFactory;
import utils.EnvUtils;
import webapi.dtos.SignUpFormDto;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @PostMapping("signup")
    public void signUp(@RequestBody SignUpFormDto form) {
        // String foo = UseCasesFactory.getSignUpUseCase();
        // System.out.println(foo);

        System.out.println(form.name);
        System.out.println(form.email);
        System.out.println(form.phone);
        System.out.println(form.password);

        Connection connection = null;
        var connectionManager = new ConnectionManager();
        try {
            var connectionString = EnvUtils.getConnectionString();
            connection = connectionManager.getOpenedConnection(connectionString);
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    @PostMapping("signin")
    public String signIn() {
        return "Sign in";
    }

}
