package my.goals.application.usecases;

import my.goals.web.dtos.CreateGoalForm;

public interface ICreateGoal {
    void execute(CreateGoalForm form) throws Exception;
}
