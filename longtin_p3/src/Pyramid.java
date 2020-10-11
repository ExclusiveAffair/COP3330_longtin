public class Pyramid extends Shape3D {
    private double length, width, height;
    Pyramid(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public String getName() {
        return "pyramid";
    }

    @Override
    public double getArea() {
        double component1 = length * width;
        double component2 = length * Math.sqrt((width / 2.0) * (width / 2.0) + height * height);
        double component3 = width * Math.sqrt((length / 2.0) * (length / 2.0) + height * height);
        return component1 + component2 + component3;
    }

    @Override
    public double getVolume() {
        return length * width * height / 3.0;
    }
}
