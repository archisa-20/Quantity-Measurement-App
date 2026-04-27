import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // =========================
    // Explicit Target Unit Tests
    // =========================

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(1.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
    }

    // =========================
    // Same / Different Operand Unit Scenarios
    // =========================

    @Test
    void testAddition_TargetUnitSameAsFirstOperand() {
        QuantityLength l1 = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength l2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_TargetUnitSameAsSecondOperand() {
        QuantityLength l1 = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength l2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON);
    }

    // =========================
    // Commutativity
    // =========================

    @Test
    void testAddition_Commutativity_WithTargetUnit() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength r1 = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);
        QuantityLength r2 = QuantityMeasurementApp.add(l2, l1, LengthUnit.YARDS);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    // =========================
    // Edge Cases
    // =========================

    @Test
    void testAddition_WithZero() {
        QuantityLength l1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);

        assertEquals(1.667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityLength l1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    // =========================
    // Validation Tests
    // =========================

    @Test
    void testAddition_NullOperands() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.add(l1, null, LengthUnit.FEET)
        );
    }

    @Test
    void testAddition_NullTargetUnit() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.add(l1, l2, null)
        );
    }

    // =========================
    // Precision Test
    // =========================

    @Test
    void testAddition_PrecisionTolerance() {
        QuantityLength l1 = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength l2 = new QuantityLength(1.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp.add(l1, l2, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
    }
}