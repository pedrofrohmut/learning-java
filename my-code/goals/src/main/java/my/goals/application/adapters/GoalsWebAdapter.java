package my.goals.application.adapters;

import my.goals.application.usecases.ICreateGoal;
import my.goals.application.usecases.IDeleteGoal;
import my.goals.application.usecases.IFindAllGoals;
import my.goals.web.dtos.CreateGoalForm;

public class GoalsWebAdapter {

    public static AdaptedWebResponse createGoal(ICreateGoal useCase, CreateGoalForm form) {
        try {
            useCase.execute(form);
            return AdaptedWebResponse.of(201);
        } catch (Exception e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        }
    }

    public static AdaptedWebResponse findAllGoals(IFindAllGoals useCase) {
        try {
            final var allGoals = useCase.execute();
            return AdaptedWebResponse.of(200, allGoals);
        } catch (Exception e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        }
    }

    public static AdaptedWebResponse deleteGoal(IDeleteGoal useCase, String id) {
        try {
            useCase.execute(id);
            return AdaptedWebResponse.of(204);
        } catch (Exception e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        }
    }

}
