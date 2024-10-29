package my.goals.application.usecases;

import java.util.List;

import my.goals.entities.Goal;

public interface IFindAllGoals {
    List<Goal> execute();
}
