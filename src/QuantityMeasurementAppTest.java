import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // =========================
    // Equality Tests
    // =========================

    @Test
    void testEquality_Kg_Kg() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testEquality_Kg_Gram() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    void testEquality_Kg_Pound() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.20462, WeightUnit.POUND)
        );
    }

    // =========================
    // Conversion Tests
    // =========================

    @Test
    void testConvert_KgToGram() {
        QuantityWeight result =
                QuantityMeasurementApp.convert(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConvert_GramToKg() {
        QuantityWeight result =
                QuantityMeasurementApp.convert(1000.0, WeightUnit.GRAM, WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testConvert_KgToPound() {
        QuantityWeight result =
                QuantityMeasurementApp.convert(1.0, WeightUnit.KILOGRAM, WeightUnit.POUND);

        assertEquals(2.20462, result.getValue(), EPSILON);
    }

    // =========================
    // Addition Tests
    // =========================

    @Test
    void testAddition_SameUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = QuantityMeasurementApp.add(w1, w2);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_KgPlusGram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = QuantityMeasurementApp.add(w1, w2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result =
                QuantityMeasurementApp.add(w1, w2, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_PoundPlusKg() {
        QuantityWeight w1 = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result =
                QuantityMeasurementApp.add(w1, w2, WeightUnit.POUND);

        assertEquals(4.40924, result.getValue(), EPSILON);
    }

    // =========================
    // Edge Cases
    // =========================

    @Test
    void testZeroValue() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(0.0, WeightUnit.GRAM);

        QuantityWeight result = QuantityMeasurementApp.add(w1, w2);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testNegativeValues() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(-2000.0, WeightUnit.GRAM);

        QuantityWeight result = QuantityMeasurementApp.add(w1, w2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    // =========================
    // Validation
    // =========================

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null));
    }

    @Test
    void testNaNValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    // =========================
    // Cross-category safety (important UC9 concept)
    // =========================

    @Test
    void testWeightVsLengthNotEqual() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        // simulated wrong type comparison should fail safely
        assertNotEquals(weight, new Object());
    }
}