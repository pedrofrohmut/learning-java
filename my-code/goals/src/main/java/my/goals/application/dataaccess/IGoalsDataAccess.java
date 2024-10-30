package my.goals.application.dataaccess;

import java.util.List;

import my.goals.entities.Goal;

public interface IGoalsDataAccess {
    void create(Goal goal);
    List<Goal> findAll();
    void delete(String id);
}
