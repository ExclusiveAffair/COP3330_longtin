public class BodyMassIndex {
    double height, weight;

    public BodyMassIndex(double height, double weight) {
        this.height=height;
        this.weight=weight;
    }

    public double calculateBmi() {
        return 703 * weight / (height * height);
    }

    public String bmiRange() {
        double bmi = calculateBmi();
        String bmiRange;

        if (bmi < 18.5) {
            bmiRange = "Underweight";
        }
        else if (bmi <= 24.9) {
            bmiRange = "Normal weight";
        }
        else if (bmi <=29.9) {
            bmiRange = "Overweight";
        }
        else {
            bmiRange = "Obese";
        }
        return bmiRange;
    }
}
