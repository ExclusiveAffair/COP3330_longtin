import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {
    @Test
    public void testSquareName() {
        Shape3D shape = new Sphere(10);
        assertEquals(4188.79, shape.getVolume(),0.01);
    }
}