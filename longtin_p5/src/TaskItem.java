public class TaskItem {
    private String title;
    private String description;
    private String dueDate;
    private boolean hasBeenCompleted;

    public TaskItem(String title, String description, String dueDate, boolean hasBeenCompleted) {
        if(isTitleValid(title)) {
            this.title = title;
        } else {
            throw new InvalidTitleException();
        }
        if(isDescriptionValid(description)) {
            this.description = description;
        } else {
            throw new InvalidDescriptionException();
        }
        if(isDateValid(dueDate)) {
            this.dueDate = dueDate;
        } else {
            throw new InvalidDateException();
        }
        this.hasBeenCompleted = hasBeenCompleted;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getDueDate() {
        return dueDate;
    }
    public boolean getCompleted() {
        return hasBeenCompleted;
    }
    public void setTitle(String newTitle) {
        if(isTitleValid(newTitle)) {
            title = newTitle;
        } else {
            throw new InvalidTitleException();
        }
    }
    public void setDescription(String newDescription) {
        if(isDescriptionValid(newDescription)) {
            description = newDescription;
        } else {
            throw new InvalidDescriptionException();
        }
    }
    public void setDueDate(String newDueDate) {
        if(isDateValid(newDueDate)) {
            this.dueDate = newDueDate;
        } else {
            throw new InvalidDateException();
        }
    }
    public void setHasBeenCompleted(boolean newHasBeenCompleted) {
        hasBeenCompleted = newHasBeenCompleted;
    }
    private boolean isTitleValid(String title) {
        return title.length()>=1;
    }
    private boolean isDescriptionValid(String description) {
        return description.length()>=1;
    }
    private boolean isDateValid(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }
    @Override
    public String toString() {
        String prefix = hasBeenCompleted ? "*** " : "";
        return prefix+"["+dueDate+"] "+title+": "+description;
    }
}
class InvalidTitleException extends IllegalArgumentException {}
class InvalidDescriptionException extends IllegalArgumentException {}
class InvalidDateException extends IllegalArgumentException {}