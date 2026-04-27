import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testFeetToInches() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testInchesToFeet() {
        double result = QuantityMeasurementApp.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testYardsToFeet() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.FEET);
        assertEquals(3.0, result, EPSILON);
    }

    @Test
    void testFeetToYards() {
        double result = QuantityMeasurementApp.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testCentimetersToInches() {
        double result = QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testSameUnitConversion() {
        double result = QuantityMeasurementApp.convert(5.0, LengthUnit.FEET, LengthUnit.FEET);
        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testZeroValueConversion() {
        double result = QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testNegativeValueConversion() {
        double result = QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(-12.0, result, EPSILON);
    }

    @Test
    void testInvalidUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(1.0, null, LengthUnit.FEET)
        );
    }

    @Test
    void testNaNValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES)
        );
    }

    @Test
    void testInfiniteValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES)
        );
    }

    @Test
    void testRoundTripConversion() {
        double original = 10.0;

        double converted = QuantityMeasurementApp.convert(original, LengthUnit.YARDS, LengthUnit.FEET);
        double roundTrip = QuantityMeasurementApp.convert(converted, LengthUnit.FEET, LengthUnit.YARDS);

        assertEquals(original, roundTrip, EPSILON);
    }
}