import java.util.InputMismatchException;

public class ContactApp {
    private static final int CREATELIST = 1;
    private static final int LOADLIST = 2;
    private static final int QUITPROGRAM = 3;

    private static final int VIEWLIST = 1;
    private static final int ADDITEM = 2;
    private static final int EDITITEM = 3;
    private static final int REMOVEITEM = 4;
    private static final int SAVELIST = 5;
    private static final int QUITTOMAIN = 6;

    private static ContactList currentContactList;

    static void runApp() {
        currentContactList = new ContactList();
        int mainMenuResponse = queryContactMainMenu();

        while(mainMenuResponse != QUITPROGRAM) {
            currentContactList.clear();
            if(mainMenuResponse == CREATELIST) {
                System.out.println("New list created!\n");
            }
            else if(mainMenuResponse == LOADLIST) {
                String loadFileResponse = queryLoadFile();
                currentContactList.load(loadFileResponse);
            }
            int listMenuResponse = queryContactListOperationMenu();

            while(listMenuResponse != QUITTOMAIN) {
                listMenuQuery(listMenuResponse);
                listMenuResponse = queryContactListOperationMenu();
            }
            mainMenuResponse = queryContactMainMenu();
        }
    }

    private static int queryContactMainMenu() {
        System.out.println("Contact List Main Menu\n---------");
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

    private static int queryContactListOperationMenu() {
        System.out.println("Contact List Operation Menu\n---------");
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) save the current list");
        System.out.println("6) quit to the main menu");

        int response = 0;
        while(true) {
            try {
                response = App.scan.nextInt();
                if(response <= 0 || response > 6) {
                    throw new InvalidContactListMenuQueryException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Please input an integer. ");
                App.scan.nextLine();
            } catch (InvalidContactListMenuQueryException e) {
                System.out.print("Please input an integer from 1-6. ");
            }
        }
        return response;
    }


    private static void listMenuQuery(int listMenuResponse) {
        if(listMenuResponse == VIEWLIST) {
            System.out.println(currentContactList);
        }
        else if(listMenuResponse == ADDITEM) {
            App.scan.nextLine();
            addListItem();
        }
        else if(listMenuResponse == EDITITEM) {
            if(!currentContactList.emptyCheck()) {
                editListItem(queryIndex());
            }
        }
        else if(listMenuResponse == REMOVEITEM) {
            if(!currentContactList.emptyCheck()) {
                currentContactList.remove(queryIndex());
            }
        }
        else if(listMenuResponse == SAVELIST) {
            if(!currentContactList.emptyCheck()) {
                saveListToFile();
            }
        }
    }
    private static void saveListToFile() {
        App.scan.nextLine();
        System.out.print("Enter file name to save as: ");
        String filename = validateFileName();
        currentContactList.write(filename);
    }
    private static void editListItem(int id) {
        App.scan.nextLine();

        System.out.print("New contact first name: ");
        String firstName = App.scan.nextLine();
        System.out.print("New contact last name: ");
        String lastName = App.scan.nextLine();
        System.out.print("New phone number (xxx-xxx-xxxx): ");
        String phoneNumber = App.scan.nextLine();
        System.out.print("New email address (x@y.z): ");
        String emailAddress = App.scan.nextLine();
        currentContactList.editItem(firstName, lastName, phoneNumber, emailAddress, id);
    }
    private static int queryIndex() {
        System.out.println(currentContactList);
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
        System.out.print("Contact first name: ");
        String firstName = App.scan.nextLine();
        System.out.print("Contact last name: ");
        String lastName = App.scan.nextLine();
        System.out.print("Phone number (xxx-xxx-xxxx): ");
        String phoneNumber = App.scan.nextLine();
        System.out.print("Email address (x@y.z): ");
        String emailAddress = App.scan.nextLine();

        currentContactList.addItem(firstName, lastName, phoneNumber, emailAddress);
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
}

class InvalidContactListMenuQueryException extends IllegalArgumentException {}
