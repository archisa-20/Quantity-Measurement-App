import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testYardToFeet() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.LengthUnit.YARD,
                3.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testYardToInch() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.LengthUnit.YARD,
                36.0, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testCentimeterToInch() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER,
                0.393701, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testCentimeterToCentimeter() {
        assertTrue(QuantityMeasurementApp.compare(
                2.0, QuantityMeasurementApp.LengthUnit.CENTIMETER,
                2.0, QuantityMeasurementApp.LengthUnit.CENTIMETER));
    }

    @Test
    void testYardToFeet_FalseCase() {
        assertFalse(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.LengthUnit.YARD,
                2.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testSameReference() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(
                        1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testNullComparison() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(
                        1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q.equals(null));
    }
}