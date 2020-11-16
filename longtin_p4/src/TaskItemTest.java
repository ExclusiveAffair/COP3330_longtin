import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {
        assertThrows(InvalidDateException.class, () -> {
            new TaskItem("a", "a", "a", false);
        });
    }
    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        assertThrows(InvalidTitleException.class, () -> {
            new TaskItem("", "a", "a", false);
        });
    }
    @Test
    public void creatingTaskItemFailsWithInvalidDescription() {
        assertThrows(InvalidDescriptionException.class, () -> {
            new TaskItem("a", "", "a", false);
        });
    }
    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        assertDoesNotThrow(() -> {
            new TaskItem("a", "a", "2000-10-10", false);
        });
    }
    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        assertDoesNotThrow(() -> {
            new TaskItem("a", "a", "2000-10-10", false);
        });
    }
    @Test
    public void creatingTaskItemSucceedsWithValidDescription() {
        assertDoesNotThrow(() -> {
            new TaskItem("a", "a", "2000-10-10", false);
        });
    }
    TaskItem sample = new TaskItem("a", "a", "2000-10-10", false);
    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        assertThrows(InvalidDateException.class, () -> {
            sample.setDueDate("a");
        });
    }
    @Test
    public void settingTaskItemDueDateFailsWithInvalidTitle() {
        assertThrows(InvalidTitleException.class, () -> {
            sample.setTitle("");;
        });
    }
    @Test
    public void settingTaskItemDueDateFailsWithInvalidDescription() {
        assertThrows(InvalidDescriptionException.class, () -> {
            sample.setDescription("");;
        });
    }
    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {
        assertDoesNotThrow(() -> {
            sample.setDueDate("2000-08-07");
        });
    }
    @Test
    public void settingTaskItemDueDateSucceedsWithValidTitle() {
        assertDoesNotThrow(() -> {
            sample.setTitle("im so tired rn");
        });
    }
    @Test
    public void settingTaskItemDueDateSucceedsWithValidDescription() {
        assertDoesNotThrow(() -> {
            sample.setDescription("i wanna slepe thank u");
        });
    }
}