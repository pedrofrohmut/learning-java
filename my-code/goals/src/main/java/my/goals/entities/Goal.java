package my.goals.entities;

import java.util.List;
import java.util.UUID;

import my.goals.application.dataaccess.IGoalsDataAccess;
import my.goals.web.dtos.CreateGoalForm;

public class Goal {

    private final String id;
    private final String description;
    private final boolean isDone;

    public Goal(String id, String description, boolean isDone) {
        this.id = id;
        this.description = description;
        this.isDone = isDone;
    }

    public Goal(String description, boolean isDone) {
        this(null, description, isDone);
    }

    public Goal(CreateGoalForm form) {
        this(form.description, form.isDone);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public static void validateId(String id) throws Exception {
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new Exception("Id is not a valid UUID");
        }
    }

    public static void validateDescription(String description) throws Exception {
        if (description.isBlank()) {
            throw new Exception("Description cannot be blank");
        }
    }

    public static void validateIsDone(boolean isDone) throws Exception {
    }

    public static void createGoal(Goal goal, IGoalsDataAccess goalsDataAccess) throws Exception {
        validateDescription(goal.getDescription());
        validateIsDone(goal.getIsDone());

        final var newGoal = new Goal(UUID.randomUUID().toString(), goal.getDescription(), goal.getIsDone());

        goalsDataAccess.create(newGoal);
    }

    public static List<Goal> findAllGoals(IGoalsDataAccess goalsDataAccess) throws Exception {
        final var goals = goalsDataAccess.findAll();
        if (goals.size() == 0) {
            throw new Exception("There are no goals to display");
        }
        return goals;
    }

    public static void existsGoal(String id, IGoalsDataAccess goalsDataAccess) throws Exception {
        final var goal = goalsDataAccess.findById(id);
        if (!goal.isPresent()) {
            throw new Exception("Goal with this id does not exists");
        }
    }

    public static void deleteGoal(String id, IGoalsDataAccess goalsDataAccess) throws Exception {
        validateId(id);

        final var rowsAffected = goalsDataAccess.delete(id);
        if (rowsAffected < 1) {
            throw new Exception("No goals, with this id, was deleted");
        }
    }
}
