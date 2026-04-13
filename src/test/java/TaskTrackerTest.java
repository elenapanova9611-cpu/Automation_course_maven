import org.testng.Assert;
import org.testng.annotations.Test;
import tasktracker.Status;
import tasktracker.Task;

public class TaskTrackerTest {

    @Test(groups = "positive")
    public void givenTask_whenGetInfo_thenReturnsExpectedTaskInfo() {
        // given
        Task testTask = new Task("Test title", "Test description", 1, Status.IN_TEST);
        String expectedToCompare = "Info about the task: \nTitle: Test title \nDescription: Test description \nPriority: 1 \nStatus: In Test\n";

        // when
        String actualFromMethod = testTask.getInfo();

        // then
        Assert.assertEquals(actualFromMethod, expectedToCompare);
    }

    @Test(groups = "positive")
    public void makeFromUserInput_shouldReturnOpenStatus_whenInputIsOpen() {
        // given
        Status expectedStatus = Status.OPEN;

        // when
        Status actualStatus = Status.makeFromUserInput("Open");

        // then
        Assert.assertEquals(actualStatus, expectedStatus);
    }

    @Test(groups = "negative")
    public void makeFromUserInput_shouldReturnNull_whenStatusIsUnknown() {
        // given
        String invalidInput = "NonExistingStatus";

        // when
        Status actualStatus = Status.makeFromUserInput(invalidInput);

        // then
        Assert.assertNull(actualStatus);
    }

    @Test(groups = "positive")
    public void makeFromUserInput_shouldReturnStatusInTest_whenInputIsLowerCase() {
        // given
        String lowerCaseInput = "in test";

        // when
        Status actualStatus = Status.makeFromUserInput(lowerCaseInput);

        // then
        Assert.assertSame(actualStatus, Status.IN_TEST);
    }
}
