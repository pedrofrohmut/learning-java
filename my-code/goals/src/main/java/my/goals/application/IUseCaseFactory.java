package my.goals.application;

import java.sql.Connection;

import my.goals.application.usecases.ICreateGoal;
import my.goals.application.usecases.IFindAllGoals;
import my.goals.application.usecases.IDeleteGoal;

public interface IUseCaseFactory {
    ICreateGoal getCreateGoal(Connection connection);

    IFindAllGoals getFindAllGoals(Connection connection);

    IDeleteGoal getDeleteGoal(Connection connection);
}
