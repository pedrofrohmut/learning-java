package my.goals.entities;

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

    public String getId() {
	return id;
    }

    public String getDescription() {
	return description;
    }

    public boolean getIsDone() {
	return isDone;
    }

}
