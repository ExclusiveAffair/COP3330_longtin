public class Square extends Shape2D {
    private double sideLength;

    Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public String getName() {
        return "square";
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }
}
