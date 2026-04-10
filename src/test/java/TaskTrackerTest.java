import org.testng.Assert;
import org.testng.annotations.Test;
import tasktracker.Status;
import tasktracker.Task;

public class TaskTrackerTest {

    @Test(groups = "positive")
    public void getInfo_actualTaskInfo_expectedTaskInfo_areEqual() {
        Task testTask = new Task("Test title", "Test description", 1, Status.IN_TEST);
        String actualFromMethod = testTask.getInfo();
        String expectedToCompare = "Info about the task: \nTitle: Test title \nDescription: Test description \nPriority: 1 \nStatus: In Test\n";
        Assert.assertEquals(actualFromMethod, expectedToCompare);
    }

    @Test(groups = "positive")
    public void makeFromUserInput_actualOpen_expectedOpen() {
        Status actualStatus = Status.makeFromUserInput("Open");
        Assert.assertEquals(actualStatus, Status.OPEN);
    }

    @Test(groups = "negative")
    public void makeFromUserInput_checkInvalidStatus() {
        Status actualStatus = Status.makeFromUserInput("NonExistingStatus");
        Assert.assertNotEquals(actualStatus, Status.OPEN);
    }

    @Test(groups = "positive")
    public void makeFromUserInput_checkLowerCaseIsValidInput() {
        Status actualStatus = Status.makeFromUserInput("in test");
        Assert.assertTrue(actualStatus == Status.IN_TEST);
    }
}
