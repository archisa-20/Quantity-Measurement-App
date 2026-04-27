public class QuantityMeasurementApp {

    // =========================
    // UC5: Conversion
    // =========================
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        validate(value, source, target);

        if (source == target) {
            return round(value);
        }

        double baseValue = value * source.getConversionFactor();
        double result = baseValue / target.getConversionFactor();

        return round(result);
    }

    // =========================
    // UC6 + UC7: Addition with explicit target unit
    // =========================
    public static QuantityLength add(QuantityLength l1, QuantityLength l2, LengthUnit targetUnit) {

        validateAddition(l1, l2, targetUnit);

        double baseSum = toBase(l1) + toBase(l2);

        double resultValue = fromBase(baseSum, targetUnit);

        return new QuantityLength(round(resultValue), targetUnit);
    }

    // =========================
    // Helpers
    // =========================

    private static double toBase(QuantityLength length) {
        return length.getValue() * length.getUnit().getConversionFactor();
    }

    private static double fromBase(double baseValue, LengthUnit unit) {
        return baseValue / unit.getConversionFactor();
    }

    private static void validate(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
    }

    private static void validateAddition(QuantityLength l1, QuantityLength l2, LengthUnit targetUnit) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    private static double round(double value) {
        return Math.round(value * 1_000_000d) / 1_000_000d;
    }
}