package my.goals.application.adapters;

import my.goals.application.usecases.ICreateGoal;
import my.goals.application.usecases.IDeleteGoal;
import my.goals.application.usecases.IFindAllGoals;
import my.goals.web.dtos.CreateGoalForm;

public class GoalsWebAdapter {

    public static AdaptedWebResponse createGoal(ICreateGoal useCase, CreateGoalForm form) {
	useCase.execute(form);
	return AdaptedWebResponse.of(201);
    }

    public static AdaptedWebResponse findAllGoals(IFindAllGoals useCase) {
	final var allGoals = useCase.execute();
	return AdaptedWebResponse.of(200, allGoals);
    }

    public static AdaptedWebResponse deleteGoal(IDeleteGoal useCase, String id) {
	useCase.execute(id);
	return AdaptedWebResponse.of(204);
    }

}
