package my.goals.web.controllers;

import java.sql.Connection;

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

    // TODO: Make the abstract and concrete ConnectionManager
    // TODO: Make the abstract and concrete useCaseFactory
    // TODO: Make the 3 useCases
    // TODO: Make the repository
    // TODO: Make a web presenter that get the useCase Output and converts it to web response.
    // TODO: If the presenter make no sense, then make just a webAdapter like in older projects

    private final IUseCaseFactory useCaseFactory;

    @Autowired
    public GoalsController(IUseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @PostMapping("create")
    public ResponseEntity<Object> create(@Autowired IConnectionManager connectionManager,
            @RequestBody CreateGoalForm form) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            final var createGoal = this.useCaseFactory.getCreateGoal(connection);
            final var response = GoalsWebAdapter.createGoal(createGoal, form);
            return ResponseEntity.status(response.status).body(response.body);
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    @GetMapping("find")
    public ResponseEntity<Object> findAll(@Autowired IConnectionManager connectionManager) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            final var findAllGoals = this.useCaseFactory.getFindAllGoals(connection);
            final var response = GoalsWebAdapter.findAllGoals(findAllGoals);
            return ResponseEntity.status(response.status).body(response.body);
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteOne(@Autowired IConnectionManager connectionManager,
            @PathVariable("id") String id) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            final var deleteGoal = this.useCaseFactory.getDeleteGoal(connection);
            final var response = GoalsWebAdapter.deleteGoal(deleteGoal, id);
            return ResponseEntity.status(response.status).body(response.body);
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

}
