package my.goals.web.controllers;

import java.sql.Connection;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.goals.web.dtos.CreateGoalForm;
import my.goals.application.IConnectionManager;
import my.goals.application.IUseCaseFactory;
import my.goals.application.adapters.GoalsWebAdapter;

@RestController
@RequestMapping("api/v1/goals")
public class GoalsController {

    // TODO: Make a web presenter that get the useCase Output and converts it to web response.
    // TODO: If the presenter make no sense, then make just a webAdapter like in older projects

    private final IUseCaseFactory useCaseFactory;
    private final ApplicationContext ctx;

    @Autowired
    public GoalsController(IUseCaseFactory useCaseFactory, ApplicationContext ctx) {
        this.useCaseFactory = useCaseFactory;
	this.ctx = ctx;
    }

    // Works as an optional dependency not to be used always
    // That is why is not passed in the constructor as the other dependencies
    private IConnectionManager getConnectionManager() {
	return (IConnectionManager) this.ctx.getBean("PostgresConnectionManager");
    }

    @PostMapping("create")
    public ResponseEntity<Object> create(@RequestBody CreateGoalForm form) {
	final var connectionManager = getConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            final var createGoal = this.useCaseFactory.getCreateGoal(connection);
            final var response = GoalsWebAdapter.createGoal(createGoal, form);
            return ResponseEntity.status(response.status).body(response.body);
	} catch (Exception e) {
	    throw new RuntimeException("ERROR: with message: " + e.getMessage());
	} finally {
	    connectionManager.closeConnection(connection);
        }
    }

    @GetMapping("find")
    public ResponseEntity<Object> findAll() {
	final var connectionManager = getConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            final var findAllGoals = this.useCaseFactory.getFindAllGoals(connection);
            final var response = GoalsWebAdapter.findAllGoals(findAllGoals);
            return ResponseEntity.status(response.status).body(response.body);
	} catch (Exception e) {
	    throw new RuntimeException("ERROR: with message: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable("id") String id) {
	final var connectionManager = getConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            final var deleteGoal = this.useCaseFactory.getDeleteGoal(connection);
            final var response = GoalsWebAdapter.deleteGoal(deleteGoal, id);
            return ResponseEntity.status(response.status).body(response.body);
	} catch (Exception e) {
	    throw new RuntimeException("ERROR: with message: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

}
