import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testFeetToFeet_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                1.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testInchToInch_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.LengthUnit.INCH,
                1.0, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testFeetToInch_Equivalent() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                12.0, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testFeetToFeet_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                2.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testSameObjectEquality() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testNullComparison() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q.equals(null));
    }
}