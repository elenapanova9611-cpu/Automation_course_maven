package tasktracker;

public enum ActionMenu {
    CREATE,
    DISPLAY,
    EXIT,
    UNKNOWN;


    public static ActionMenu fromInt(int userInput) {
        return switch (userInput) {
            case 1 -> CREATE;
            case 2 -> DISPLAY;
            case 0 -> EXIT;
            default -> UNKNOWN;
        };
    }
}
