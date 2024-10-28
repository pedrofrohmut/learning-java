package my.goals.web.dtos;

public class CreateGoalForm {

    private final String description;
    private final boolean isDone;

    public CreateGoalForm(String description) {
        this.description = description;
        this.isDone = false;
    }

    public CreateGoalForm(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

}
