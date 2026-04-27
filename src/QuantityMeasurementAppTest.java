import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // =========================
    // Same Unit Addition
    // =========================
    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_SameUnit_Inches() {
        QuantityLength l1 = new QuantityLength(6.0, LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(6.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    // =========================
    // Cross Unit Addition
    // =========================
    @Test
    void testAddition_FeetPlusInches() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_InchesPlusFeet() {
        QuantityLength l1 = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_YardPlusFeet() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength l2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // =========================
    // Identity (Zero)
    // =========================
    @Test
    void testAddition_WithZero() {
        QuantityLength l1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    // =========================
    // Negative Values
    // =========================
    @Test
    void testAddition_NegativeValues() {
        QuantityLength l1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    // =========================
    // Commutativity
    // =========================
    @Test
    void testAddition_Commutativity() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength r1 = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);
        QuantityLength r2 = QuantityMeasurementApp.add(l2, l1, LengthUnit.FEET);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    // =========================
    // Large Values
    // =========================
    @Test
    void testAddition_LargeValues() {
        QuantityLength l1 = new QuantityLength(1e6, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1e6, LengthUnit.FEET);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    // =========================
    // Small Values
    // =========================
    @Test
    void testAddition_SmallValues() {
        QuantityLength l1 = new QuantityLength(0.001, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(0.002, LengthUnit.FEET);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(0.003, result.getValue(), EPSILON);
    }

    // =========================
    // Null Validation
    // =========================
    @Test
    void testAddition_NullOperand() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.add(l1, null, LengthUnit.FEET)
        );
    }
}