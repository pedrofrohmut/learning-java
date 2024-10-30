package my.goals.application;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import my.goals.application.dataaccess.IGoalsDataAccess;
import my.goals.application.usecases.ICreateGoal;
import my.goals.application.usecases.IDeleteGoal;
import my.goals.application.usecases.IFindAllGoals;
import my.goals.infra.repositories.PostgresGoalsDataAccess;

@Service
public class MainUseCaseFactory implements IUseCaseFactory {

    private final ApplicationContext context;

    @Autowired
    public MainUseCaseFactory(ApplicationContext context) {
	this.context = context;
    }

    private IGoalsDataAccess getGoalsDataAccess(Connection connection) {
	final var goalsDataAccess = (PostgresGoalsDataAccess) this.context.getBean("postgresGoalsDataAccess");
	goalsDataAccess.setConnection(connection);
	return goalsDataAccess;
    }

    @Override
    public ICreateGoal getCreateGoal(Connection connection) {
	final var goalsDataAccess = getGoalsDataAccess(connection);
	return (ICreateGoal) this.context.getBean("CreateGoal", goalsDataAccess);
    }

    @Override
    public IFindAllGoals getFindAllGoals(Connection connection) {
	final var goalsDataAccess = getGoalsDataAccess(connection);
	return (IFindAllGoals) this.context.getBean("FindAllGoal", goalsDataAccess);
    }

    @Override
    public IDeleteGoal getDeleteGoal(Connection connection) {
	final var goalsDataAccess = getGoalsDataAccess(connection);
	return (IDeleteGoal) this.context.getBean("DeleteGoal", goalsDataAccess);
    }

}
