public class QuantityMeasurementApp {

    /**
     * Converts a value from one length unit to another using base-unit normalization.
     *
     * Formula:
     * result = value × (sourceFactor / targetFactor)
     */
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {

        validateInput(value, sourceUnit, targetUnit);

        if (sourceUnit == targetUnit) {
            return value;
        }

        double sourceFactor = sourceUnit.getConversionFactor();
        double targetFactor = targetUnit.getConversionFactor();

        double result = value * (sourceFactor / targetFactor);

        return round(result);
    }

    /**
     * Overloaded method: converts using QuantityLength object (if used in UC4 model).
     */
    public static double convert(QuantityLength length, LengthUnit targetUnit) {
        if (length == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }
        return convert(length.getValue(), length.getUnit(), targetUnit);
    }

    private static void validateInput(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
    }

    private static double round(double value) {
        return Math.round(value * 1_000_000d) / 1_000_000d;
    }
}