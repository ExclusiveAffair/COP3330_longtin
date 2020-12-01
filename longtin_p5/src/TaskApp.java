import java.util.InputMismatchException;

public class TaskApp {
    private static final int CREATELIST = 1;
    private static final int LOADLIST = 2;
    private static final int QUITPROGRAM = 3;

    private static final int VIEWLIST = 1;
    private static final int ADDITEM = 2;
    private static final int EDITITEM = 3;
    private static final int REMOVEITEM = 4;
    private static final int MARKCOMPLETED = 5;
    private static final int UNMARKCOMPLETED = 6;
    private static final int SAVELIST = 7;
    private static final int QUITTOMAIN = 8;

    private static TaskList currentTaskList;

    static void runApp() {
        currentTaskList = new TaskList();
        int mainMenuResponse = queryTaskMainMenu();

        while(mainMenuResponse != QUITPROGRAM) {
            currentTaskList.clear();
            if(mainMenuResponse == CREATELIST) {
                System.out.println("New list created!\n");
            }
            else if(mainMenuResponse == LOADLIST) {
                String loadFileResponse = queryLoadFile();
                currentTaskList.load(loadFileResponse);
            }
            int listMenuResponse = queryTaskListOperationMenu();

            while(listMenuResponse != QUITTOMAIN) {
                listMenuQuery(listMenuResponse);
                listMenuResponse = queryTaskListOperationMenu();
            }
            mainMenuResponse = queryTaskMainMenu();
        }
    }

    private static int queryTaskMainMenu() {
        System.out.println("Task Main Menu\n---------");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");

        int response = 0;
        while(true) {
            try {
                response = App.scan.nextInt();
                if(response <= 0 || response > 3) {
                    throw new InvalidMainMenuQueryException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Please input an integer. ");
                App.scan.nextLine();
            } catch (InvalidMainMenuQueryException e) {
                System.out.print("Please input an integer from 1-3. ");
                App.scan.nextLine();
            }
        }
        return response;
    }

    private static int queryTaskListOperationMenu() {
        System.out.println("Task List Operation Menu\n---------");
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) mark an item as completed");
        System.out.println("6) unmark an item as completed");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu");

        int response = 0;
        while(true) {
            try {
                response = App.scan.nextInt();
                if(response <= 0 || response > 8) {
                    throw new InvalidTaskListMenuQueryException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Please input an integer. ");
                App.scan.nextLine();
            } catch (InvalidTaskListMenuQueryException e) {
                System.out.print("Please input an integer from 1-8. ");
            }
        }
        return response;
    }


    private static void listMenuQuery(int listMenuResponse) {
        if(listMenuResponse == VIEWLIST) {
            System.out.println(currentTaskList);
        }
        else if(listMenuResponse == ADDITEM) {
            App.scan.nextLine();
            addListItem();
        }
        else if(listMenuResponse == EDITITEM) {
            if(!currentTaskList.emptyCheck()) {
                editListItem(queryIndex());
            }
        }
        else if(listMenuResponse == REMOVEITEM) {
            if(!currentTaskList.emptyCheck()) {
                currentTaskList.remove(queryIndex());
            }
        }
        else if(listMenuResponse == MARKCOMPLETED) {
            if(!currentTaskList.emptyCheck()) {
                currentTaskList.setCompletedStatus(queryIndex(), true);
            }
        }
        else if(listMenuResponse == UNMARKCOMPLETED) {
            if(!currentTaskList.emptyCheck()) {
                currentTaskList.setCompletedStatus(queryIndex(), false);
            }
        }
        else if(listMenuResponse == SAVELIST) {
            if(!currentTaskList.emptyCheck()) {
                saveListToFile();
            }
        }
    }
    private static void saveListToFile() {
        App.scan.nextLine();
        System.out.print("Enter file name to save as: ");
        String filename = validateFileName();
        currentTaskList.write(filename);
    }
    private static void editListItem(int id) {
        App.scan.nextLine();

        System.out.print("New task title: ");
        String title = App.scan.nextLine();
        System.out.print("New task description: ");
        String description = App.scan.nextLine();
        System.out.print("New task due date (YYYY-MM-DD): ");
        String dueDate = App.scan.nextLine();

        currentTaskList.editItem(title, description, dueDate, id);
    }
    private static int queryIndex() {
        System.out.println(currentTaskList);
        System.out.print("Select a task: ");

        int response = 0;
        while(true) {
            try {
                response = App.scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Please input an integer. ");
                App.scan.nextLine();
            }
        }
        return response;
    }
    private static void addListItem() {
        TaskItem newItem = null;
        System.out.print("Task title: ");
        String title = App.scan.nextLine();
        System.out.print("Task description: ");
        String description = App.scan.nextLine();
        System.out.print("Task due date (YYYY-MM-DD): ");
        String dueDate = App.scan.nextLine();

        currentTaskList.addItem(title, description, dueDate, false);
    }

    private static String validateFileName() {
        String response = null;

        while(true) {
            try {
                response = App.scan.nextLine();
                if(response.contains(" ") || !response.endsWith(".txt")) {
                    throw new InvalidFileNameQueryException();
                }
                break;
            } catch (InvalidFileNameQueryException e) {
                System.out.print("Please enter the name of a valid .txt file. ");
            }
        }
        return response;
    }
    private static String queryLoadFile() {
        App.scan.nextLine();
        System.out.print("Enter the filename to load: ");
        return validateFileName();
    }
    public static void main(String[] args) {
        runApp();
    }
}

class InvalidMainMenuQueryException extends IllegalArgumentException {}
class InvalidTaskListMenuQueryException extends IllegalArgumentException {}
class InvalidFileNameQueryException extends IllegalArgumentException {}