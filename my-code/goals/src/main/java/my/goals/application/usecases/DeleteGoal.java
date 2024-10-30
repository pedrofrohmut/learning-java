package my.goals.application.usecases;

import org.springframework.stereotype.Component;

import my.goals.application.dataaccess.IGoalsDataAccess;

@Component("DeleteGoal")
public class DeleteGoal implements IDeleteGoal {

    private final IGoalsDataAccess goalsDataAccess;

    public DeleteGoal(IGoalsDataAccess goalsDataAccess) {
	this.goalsDataAccess = goalsDataAccess;
    }

    @Override
    public void execute(String id) {
	this.goalsDataAccess.delete(id);
    }

}
