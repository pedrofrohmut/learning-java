package my.goals.application.usecases;

import org.springframework.stereotype.Component;

import my.goals.application.dataaccess.IGoalsDataAccess;
import my.goals.entities.Goal;

@Component("DeleteGoal")
public class DeleteGoal implements IDeleteGoal {

    private final IGoalsDataAccess goalsDataAccess;

    public DeleteGoal(IGoalsDataAccess goalsDataAccess) {
	this.goalsDataAccess = goalsDataAccess;
    }

    @Override
    public void execute(String id) throws Exception {
	Goal.existsGoal(id, this.goalsDataAccess);
	Goal.deleteGoal(id, this.goalsDataAccess);
    }

}
