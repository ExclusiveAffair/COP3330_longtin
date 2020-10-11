public class Sphere extends Shape3D {
    private double radius;
    Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "sphere";
    }

    @Override
    public double getArea() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public double getVolume() {
        return 4.0 / 3.0 * Math.PI * radius * radius * radius;
    }
}
