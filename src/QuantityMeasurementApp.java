enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // =========================
    // Convert THIS unit → BASE (FEET)
    // =========================
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    // =========================
    // Convert BASE (FEET) → THIS unit
    // =========================
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}

class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        validate(value, unit);
        this.value = value;
        this.unit = unit;
    }

    // =========================
    // Convert to another unit
    // =========================
    public QuantityLength convertTo(LengthUnit targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);
        return new QuantityLength(round(result), targetUnit);
    }

    // =========================
    // Add operation (UC6 + UC7)
    // =========================
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

        double baseSum =
                this.unit.convertToBaseUnit(this.value)
                        + other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(baseSum);

        return new QuantityLength(round(result), targetUnit);
    }

    // =========================
    // Equality (UC1–UC4 compatibility)
    // =========================
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QuantityLength)) {
            return false;
        }

        QuantityLength other = (QuantityLength) obj;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < 1e-6;
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

    public LengthUnit getUnit() {
        return unit;
    }

    // =========================
    // Helpers
    // =========================
    private void validate(double value, LengthUnit unit) {
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

    // UC5 conversion (delegated style kept for compatibility)
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        double base = source.convertToBaseUnit(value);
        double result = target.convertFromBaseUnit(base);
        return Math.round(result * 1_000_000d) / 1_000_000d;
    }

    // UC6 + UC7 delegation
    public static QuantityLength add(QuantityLength l1, QuantityLength l2, LengthUnit target) {
        return l1.add(l2, target);
    }
}