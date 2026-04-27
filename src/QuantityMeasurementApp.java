public class QuantityMeasurementApp {

    // =========================
    // UC5: Conversion
    // =========================
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        validate(value, sourceUnit, targetUnit);

        if (sourceUnit == targetUnit) {
            return round(value);
        }

        double baseValue = value * sourceUnit.getConversionFactor();
        double result = baseValue / targetUnit.getConversionFactor();

        return round(result);
    }

    public static double convert(QuantityLength length, LengthUnit targetUnit) {
        if (length == null) {
            throw new IllegalArgumentException("QuantityLength cannot be null");
        }
        return convert(length.getValue(), length.getUnit(), targetUnit);
    }

    // =========================
    // UC6: Addition
    // =========================
    public static QuantityLength add(QuantityLength l1, QuantityLength l2, LengthUnit resultUnit) {

        validateAddition(l1, l2, resultUnit);

        double l1Base = toBase(l1);
        double l2Base = toBase(l2);

        double sumBase = l1Base + l2Base;

        double resultValue = fromBase(sumBase, resultUnit);

        return new QuantityLength(round(resultValue), resultUnit);
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

    private static void validateAddition(QuantityLength l1, QuantityLength l2, LengthUnit unit) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Result unit cannot be null");
        }
    }

    private static double round(double value) {
        return Math.round(value * 1_000_000d) / 1_000_000d;
    }
}