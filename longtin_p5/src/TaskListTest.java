import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList init = new TaskList();
    @Test
    public void addingTaskItemsIncreasesSize() {
        int oldSize = init.size();
        init.addItem("uh huh", "this my shit", "2000-10-10", false);
        int newSize = init.size();
        assertTrue(newSize > oldSize);
    }
    @Test
    public void completingTaskItemChangesStatus() {
        init.addItem("lala", "lalala", "2000-10-10", false);
        boolean oldStatus = init.get(0).getCompleted();
        init.setCompletedStatus(0, true);
        boolean newStatus = init.get(0).getCompleted();
        assertTrue(oldStatus != newStatus);
    }
    @Test
    public void uncompletingTaskItemChangesStatus() {
        init.addItem("a", "a", "2000-10-10", false);
        init.setCompletedStatus(0, true);
        boolean oldStatus = init.get(0).getCompleted();
        init.setCompletedStatus(0, false);
        boolean newStatus = init.get(0).getCompleted();
        assertTrue(oldStatus != newStatus);
    }
    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        boolean successfullyEdited = init.setCompletedStatus(0, true);
        assertFalse(successfullyEdited);
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        boolean successfullyEdited = init.setCompletedStatus(0, false);
        assertFalse(successfullyEdited);
    }
    @Test
    public void editingTaskItemChangesValues() {
        init.addItem("a", "a", "2000-10-10", false);
        init.editItem("newtitle", "new desc", "2000-00-00", 0);
        assertEquals("newtitle", init.get(0).getTitle());
        assertEquals("new desc", init.get(0).getDescription());
        assertEquals("2000-00-00", init.get(0).getDueDate());
    }
    @Test
    public void editingTaskItemFailsWithInvalidTitle() {
        init.addItem("a", "a", "2000-10-10", false);
        assertEquals(init.editItem("", "new desc", "2000-00-00", 0), false);
    }
    @Test
    public void editingTaskItemFailsWithInvalidDescription() {
        init.addItem("a", "a", "2000-10-10", false);
        assertEquals(init.editItem("lala", "", "2000-00-00", 0), false);
    }
    @Test
    public void editingTaskItemFailsWithInvalidDueDate() {
        init.addItem("a", "a", "2000-10-10", false);
        assertEquals(init.editItem("ok", "new desc", "20500-5-00", 0), false);
    }
    @Test
    public void editingTaskItemDescriptionChangesValue() {
        init.addItem("a", "a", "2000-10-10", false);
        init.editItem("newtitle", "new desc2", "2000-00-00", 0);
        assertEquals("new desc2", init.get(0).getDescription());
    }
    @Test
    public void editingTaskItemTitleChangesValue() {
        init.addItem("a", "a", "2000-10-10", false);
        init.editItem("newtitle2", "new desc2", "2000-00-00", 0);
        assertEquals("newtitle2", init.get(0).getTitle());
    }
    @Test
    public void editingTaskItemDueDateChangesValue() {
        init.addItem("a", "a", "2000-10-10", false);
        init.editItem("newtitle2", "new desc2", "2000-00-01", 0);
        assertEquals("2000-00-01", init.get(0).getDueDate());
    }
    @Test
    public void editingTaskItemFailsWithInvalidIndex() {
        boolean successfullyEdited = init.editItem("how are you", "i got some sleep so now i'm awake", "2000-00-00", 0);
        assertFalse(successfullyEdited);
    }
    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {
        init.addItem("a", "a", "2000-10-10", false);
        assertEquals("2000-10-10", init.get(0).getDueDate());
    }
    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        init.addItem("a", "a", "2000-10-10", false);
        assertEquals("a", init.get(0).getDescription());
    }
    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
        init.addItem("a", "a", "2000-10-10", false);
        assertEquals("a", init.get(0).getTitle());
    }
    @Test
    public void gettingTaskItemFailsWithInvalidIndex() {
        TaskItem cur = init.get(0);
        assertEquals(cur, null);
    }

    @Test
    public void newTaskListIsEmpty() {
        TaskList newList = new TaskList();
        assertEquals(newList.size(), 0);
    }
    @Test
    public void removingTaskItemsDecreasesSize() {
        init.addItem("a", "a", "2000-10-10", false);
        int oldSize = init.size();
        init.remove(0);
        int newSize = init.size();
        assertTrue(newSize < oldSize);
    }
    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskItem removed = init.remove(0);
        assertEquals(removed, null);
    }
    @Test
    public void savedTaskListCanBeLoaded() {
        TaskList initial = new TaskList();
        initial.addItem("one", "two", "3333-33-33", false);
        initial.write("WOOO.txt");

        TaskList loaded = new TaskList();
        loaded.load("WOOO.txt");
        assertEquals(initial.size(), loaded.size());
        assertEquals(initial.get(0).getTitle(), loaded.get(0).getTitle());
        assertEquals(initial.get(0).getDescription(), loaded.get(0).getDescription());
        assertEquals(initial.get(0).getDueDate(), loaded.get(0).getDueDate());
        assertEquals(initial.get(0).getCompleted(), loaded.get(0).getCompleted());
    }
}