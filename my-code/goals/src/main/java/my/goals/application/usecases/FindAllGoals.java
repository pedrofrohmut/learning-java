package my.goals.application.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import my.goals.application.dataaccess.IGoalsDataAccess;
import my.goals.entities.Goal;

@Component("FindAllGoals")
public class FindAllGoals implements IFindAllGoals {

    private final IGoalsDataAccess goalsDataAccess;

    public FindAllGoals(IGoalsDataAccess goalsDataAccess) {
        this.goalsDataAccess = goalsDataAccess;
    }

    @Override
    public List<Goal> execute() throws Exception {
        return Goal.findAllGoals(this.goalsDataAccess);
    }

}
