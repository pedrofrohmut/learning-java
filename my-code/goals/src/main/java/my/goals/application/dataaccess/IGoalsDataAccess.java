package my.goals.application.dataaccess;

import java.util.List;
import java.util.Optional;

import my.goals.entities.Goal;

public interface IGoalsDataAccess {
    boolean create(Goal goal);

    List<Goal> findAll();

    Optional<Goal> findById(String id);

    int delete(String id);
}
