package my.goals.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;

import my.goals.application.dataaccess.IGoalsDataAccess;
import my.goals.entities.Goal;
import my.goals.web.dtos.CreateGoalForm;

@Component("CreateGoal")
public class CreateGoal implements ICreateGoal {

    private final IGoalsDataAccess goalsDataAccess;

    public CreateGoal(IGoalsDataAccess goalsDataAccess) {
	this.goalsDataAccess = goalsDataAccess;
    }

    @Override
    public void execute(CreateGoalForm form) {
	final var goal = new Goal(UUID.randomUUID().toString(), form.description, form.isDone);
	this.goalsDataAccess.create(goal);
    }

}
