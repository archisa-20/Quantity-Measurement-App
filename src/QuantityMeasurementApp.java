public class QuantityMeasurementApp {

    // ---------------- UNIT ENUM ----------------
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double conversionToFeet;

        LengthUnit(double conversionToFeet) {
            this.conversionToFeet = conversionToFeet;
        }

        public double toFeet(double value) {
            return value * conversionToFeet;
        }
    }

    // ---------------- CORE CLASS ----------------
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double inFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.inFeet(), other.inFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(inFeet());
        }
    }

    // ---------------- PUBLIC API ----------------
    public static boolean compare(double v1, LengthUnit u1,
                                  double v2, LengthUnit u2) {

        return new QuantityLength(v1, u1)
                .equals(new QuantityLength(v2, u2));
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        System.out.println("1 YARD = 3 FEET: " +
                compare(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET));

        System.out.println("1 YARD = 36 INCH: " +
                compare(1.0, LengthUnit.YARD, 36.0, LengthUnit.INCH));

        System.out.println("1 CM = 0.393701 INCH: " +
                compare(1.0, LengthUnit.CENTIMETER, 0.393701, LengthUnit.INCH));
    }
}