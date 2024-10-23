package webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.adapters.web.UsersWebAdapter;
import core.dtos.SignUpFormDto;
import core.usecases.users.SignUpUserUseCase;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    @Qualifier("signUpUserUseCase")
    private final SignUpUserUseCase signUpUserUseCase;

    private UsersController(SignUpUserUseCase signUpUserUseCase) {
        this.signUpUserUseCase = signUpUserUseCase;
    }

    //@PostMapping("old/signup")
    //public ResponseEntity<Object> signUp(@RequestBody SignUpFormDto form) {
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var signUpUserUseCase = UseCasesFactory.getSignUpUserUseCase(connection);
    //        final var response = UsersWebAdapter.signUpUser(signUpUserUseCase, form);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}

    @PostMapping("signup")
    public ResponseEntity<Object> signUp(@RequestBody SignUpFormDto form) {
        final var response = UsersWebAdapter.signUpUser(this.signUpUserUseCase, form);
        return ResponseEntity.status(response.statusCode).body(response.body);
    }

    //@PostMapping("signin")
    //public ResponseEntity<Object> signIn(@RequestBody SignInFormDto form) {
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var signInUserUseCase = UseCasesFactory.getSignInUserUseCase(connection);
    //        final var response = UsersWebAdapter.signInUser(signInUserUseCase, form);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}

}
