package webapi.controllers;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dataaccess.ConnectionManager;
import utils.UseCasesFactory;
import core.adapters.web.UsersWebAdapter;
import core.dtos.SignInFormDto;
import core.dtos.SignUpFormDto;
import utils.EnvUtils;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @PostMapping("signup")
    public ResponseEntity<Object> signUp(@RequestBody SignUpFormDto form) {
        Connection connection = null;
        var connectionManager = new ConnectionManager();
        try {
            var connectionString = EnvUtils.getConnectionString();
            connection = connectionManager.getOpenedConnection(connectionString);
            var signUpUserUseCase = UseCasesFactory.getSignUpUserUseCase(connection);
            var response = UsersWebAdapter.signUpUser(signUpUserUseCase, form);
            return ResponseEntity.status(response.statusCode).body(response.body);
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    @PostMapping("signin")
    public ResponseEntity<Object> signIn(@RequestBody SignInFormDto form) {
        Connection connection = null;
        var connectionManager = new ConnectionManager();
        try {
            var connectionString = EnvUtils.getConnectionString();
            connection = connectionManager.getOpenedConnection(connectionString);
            var signInUserUseCase = UseCasesFactory.getSignInUserUseCase(connection);
            var response = UsersWebAdapter.signInUser(signInUserUseCase, form);
            return ResponseEntity.status(response.statusCode).body(response.body);
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

}
