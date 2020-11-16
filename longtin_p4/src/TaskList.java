import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class TaskList {
    private List<TaskItem> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public boolean validateIndex(int id) {
        try {
            if(id < 0 || id >= size()) {
                throw new InvalidListIndexQueryException();
            }
        } catch(InvalidListIndexQueryException e) {
            System.out.println("WARNING: index is not in range. Progress not saved.\n");
            return false;
        }
        return true;
    }
    public TaskItem validateItem(String title, String description, String dueDate, boolean hasBeenCompleted) {
        try {
            TaskItem newItem = new TaskItem(title, description, dueDate, hasBeenCompleted);
            return newItem;

        } catch(InvalidTitleException e) {
            System.out.println("WARNING: title cannot be empty. Progress not saved.\n");
        } catch(InvalidDescriptionException e) {
            System.out.println("WARNING: description cannot be empty. Progress not saved.\n");
        } catch(InvalidDateException e) {
            System.out.println("WARNING: due date must be in YYYY-MM-DD format. Progress not saved.\n");
        }
        return null;
    }
    public void addItem(String title, String description, String dueDate, boolean hasBeenCompleted) {
        TaskItem itemToAdd = validateItem(title, description, dueDate, hasBeenCompleted);
        if(itemToAdd != null) tasks.add(itemToAdd);
    }
    public boolean editItem(String title, String description, String dueDate, int id) {
        TaskItem newItem = validateItem(title, description, dueDate, false);
        boolean indexIsValid = validateIndex(id);

        if(newItem != null && indexIsValid) {
            TaskItem toModify = tasks.get(id);
            toModify.setTitle(title);
            toModify.setDescription(description);
            toModify.setDueDate(dueDate);
            toModify.setHasBeenCompleted(false);
            return true;
        }
        return false;
    }
    public boolean setCompletedStatus(int id, boolean newState) {
        boolean indexIsValid = validateIndex(id);
        if(!indexIsValid) return false;
        TaskItem toModify = tasks.get(id);
        toModify.setHasBeenCompleted(newState);
        return true;
    }
    public TaskItem remove(int id) {
        boolean indexIsValid = validateIndex(id);
        if(!indexIsValid) return null;
        return tasks.remove(id);
    }
    public TaskItem get(int id) {
        boolean indexIsValid = validateIndex(id);
        if(!indexIsValid) return null;
        return tasks.get(id);
    }
    public int size() {
        return tasks.size();
    }
    public void clear() {
        tasks.clear();
    }
    public boolean emptyCheck() {
        if(size() == 0) {
            System.out.println("Cannot proceed: there are no items in the current list");
            return true;
        }
        return false;
    }
    public void write(String filename) {
        try(Formatter output = new Formatter(filename)) {
            output.format(toString());

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find the file "+filename);
        }
    }
    public void load(String filename) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    filename));

            //skipping the header
            reader.readLine();
            reader.readLine();

            String line = reader.readLine();
            while (line != null) {
                String[] currentLine = line.split("[\\[\\]:]");
                boolean hasBeenCompleted = currentLine[0].endsWith("*");
                String title = currentLine[2].trim();
                String description = currentLine[3].trim();
                String dueDate = currentLine[1].trim();

                addItem(title, description, dueDate, hasBeenCompleted);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the file "+filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        String list = "Current Tasks\n---------\n";
        for(int i = 0; i < tasks.size(); i++) {
            list += i+") ";
            list += tasks.get(i).toString();
            list += '\n';
        }
        return list;
    }
}
class InvalidListIndexQueryException extends IllegalArgumentException {}