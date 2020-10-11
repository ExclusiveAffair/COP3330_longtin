public class Circle extends Shape2D {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "circle";
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
