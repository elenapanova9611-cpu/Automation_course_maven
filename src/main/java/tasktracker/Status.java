package tasktracker;

public enum Status {
    OPEN,
    IN_PROGRESS,
    READY_FOR_TEST,
    IN_TEST,
    CLOSED;

    public static Status makeFromUserInput(String input) {
        try {
            return Status.valueOf(input.trim().toUpperCase().replace(' ', '_'));
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown status");
            return null;
        }
    }

    @Override
    public String toString() {
        return switch (this) {
            case OPEN -> "Open";
            case IN_PROGRESS -> "In Progress";
            case READY_FOR_TEST -> "Ready to Test";
            case IN_TEST -> "In Test";
            case CLOSED -> "Closed";
        };
    }
}