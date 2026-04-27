public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    // Convert this unit → base unit (kg)
    public double convertToBaseUnit(double value) {
        return value * toKgFactor;
    }

    // Convert base unit (kg) → this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toKgFactor;
    }
}
public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        validate(value, unit);
        this.value = value;
        this.unit = unit;
    }

    // =========================
    // Conversion
    // =========================
    public QuantityWeight convertTo(WeightUnit targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);
        return new QuantityWeight(round(result), targetUnit);
    }

    // =========================
    // Addition (default unit = first operand)
    // =========================
    public QuantityWeight add(QuantityWeight other) {
        return add(this, other, this.unit);
    }

    // =========================
    // Addition (explicit unit - UC7 style)
    // =========================
    public static QuantityWeight add(QuantityWeight w1, QuantityWeight w2, WeightUnit targetUnit) {

        double baseSum =
                w1.unit.convertToBaseUnit(w1.value)
                        + w2.unit.convertToBaseUnit(w2.value);

        double result = targetUnit.convertFromBaseUnit(baseSum);

        return new QuantityWeight(round(result), targetUnit);
    }

    // =========================
    // Equality (base unit comparison)
    // =========================
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        return Math.abs(
                this.unit.convertToBaseUnit(this.value)
                        - other.unit.convertToBaseUnit(other.value)
        ) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    // =========================
    // Getters
    // =========================
    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    // =========================
    // Helpers
    // =========================
    private void validate(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
    }

    private double round(double value) {
        return Math.round(value * 1_000_000d) / 1_000_000d;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
public class QuantityMeasurementApp {

    // Weight Conversion API
    public static QuantityWeight convert(double value, WeightUnit from, WeightUnit to) {
        double base = from.convertToBaseUnit(value);
        double result = to.convertFromBaseUnit(base);
        return new QuantityWeight(result, to);
    }

    // Addition (default unit)
    public static QuantityWeight add(QuantityWeight w1, QuantityWeight w2) {
        return w1.add(w2);
    }

    // Addition (explicit unit)
    public static QuantityWeight add(QuantityWeight w1, QuantityWeight w2, WeightUnit unit) {
        return QuantityWeight.add(w1, w2, unit);
    }
}