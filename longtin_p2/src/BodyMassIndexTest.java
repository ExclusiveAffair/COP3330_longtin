import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BodyMassIndexTest {
    @Test
    public void testUnderweightBMI() {
        BodyMassIndex b = new BodyMassIndex(69, 115);
        assertEquals("Underweight", b.bmiRange());
    }

    @Test
    public void testNormalWeightBMI() {
        BodyMassIndex b = new BodyMassIndex(64, 130);
        assertEquals("Normal weight", b.bmiRange());
    }

    @Test
    public void testOverweightBMI() {
        BodyMassIndex b = new BodyMassIndex(60, 150);
        assertEquals("Overweight", b.bmiRange());
    }

    @Test
    public void testObeseBMI() {
        BodyMassIndex b = new BodyMassIndex(63, 300);
        assertEquals("Obese", b.bmiRange());
    }

    @Test
    public void calculateBMITest() {
        BodyMassIndex b = new BodyMassIndex(69, 115);
        assertEquals(17.0, b.calculateBmi(), 0.1);
    }
}