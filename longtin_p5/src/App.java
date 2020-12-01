import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static final int TASKLIST = 1;
    private static final int CONTACTLIST = 2;
    private static final int QUITAPPLICATION = 3;

    public static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        runApp();
    }
    private static void runApp() {
        int mainMenuResponse = queryMainMenu();

        while(mainMenuResponse != QUITAPPLICATION) {
            if(mainMenuResponse == TASKLIST) {
                TaskApp.runApp();
            }
            else if(mainMenuResponse == CONTACTLIST) {
                ContactApp.runApp();
            }
            mainMenuResponse = queryMainMenu();
        }
        System.out.println("Thank you! Goodbye!");
    }
    private static int queryMainMenu() {
        System.out.println("Main Menu\n---------");
        System.out.println("1) task list");
        System.out.println("2) contact list");
        System.out.println("3) quit");

        int response = 0;
        while(true) {
            try {
                response = scan.nextInt();
                if(response <= 0 || response > 3) {
                    throw new InvalidMainMenuQueryException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Please input an integer. ");
                scan.nextLine();
            } catch (InvalidMainMenuQueryException e) {
                System.out.print("Please input an integer from 1-3. ");
                scan.nextLine();
            }
        }
        return response;
    }
}
