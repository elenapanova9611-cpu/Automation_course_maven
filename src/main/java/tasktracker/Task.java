package tasktracker;

public class Task {

    private String name;
    private String description;
    private int priority;
    private Status status;

    public Task(String name, String description, int priority, Status status) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public String getInfo() {
        return String.format("Info about the task: \nTitle: %s \nDescription: %s \nPriority: %d \nStatus: %s\n",
                name, description, priority, status);
    }
}

