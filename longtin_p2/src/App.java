import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }

    private static Scanner scan = new Scanner(System.in);

    public static double getUserHeight() {
        System.out.print("Enter user height: ");
        double height;

        while(true) {
            try {
                height = scan.nextDouble();
                if(height < 0) {
                    throw null;
                }
                break;
            } catch (Exception e) {
                System.out.print("Please enter a non-negative value: ");
                scan.nextLine();
            }
        }

        return height;
    }

    public static double getUserWeight() {
        System.out.print("Enter user weight: ");
        double weight;

        while(true) {
            try {
                weight = scan.nextDouble();
                if(weight < 0) {
                    throw null;
                }
                break;
            } catch (Exception e) {
                System.out.print("Please enter a non-negative value: ");
                scan.nextLine();
            }
        }

        return weight;
    }

    public static boolean moreInput() {
        System.out.print("More input? (Y/N) ");
        String answer;

        while(true) {
             answer = scan.next().toUpperCase();
             if(answer.equals("Y")) return true;
             if(answer.equals("N")) return false;

            System.out.print("Please enter (Y/N): ");
        }
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData) {
        int dataCount = 0;
        double dataSum = 0;

        for(BodyMassIndex bmi:bmiData) {
            double dataPoint = bmi.calculateBmi();
            dataCount++;
            dataSum += dataPoint;
        }

        if(dataCount == 0) {
            System.out.println("No data has been entered");
        }
        else {
            System.out.printf("Average BMI is %.2f\n", (dataSum / dataCount));
        }
    }

    public static void displayBmiInfo(BodyMassIndex bmiObject) {
        double bmi = bmiObject.calculateBmi();
        String bmiRange = bmiObject.bmiRange();

        System.out.printf("BMI: %.2f (%s)%n%n", bmi, bmiRange);
    }
}