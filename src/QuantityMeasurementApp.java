public class QuantityMeasurementApp {

    // ---------------- UNIT ENUM ----------------
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionToFeet;

        LengthUnit(double conversionToFeet) {
            this.conversionToFeet = conversionToFeet;
        }

        public double toFeet(double value) {
            return value * conversionToFeet;
        }
    }

    // ---------------- GENERIC QUANTITY CLASS ----------------
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

    // ---------------- STATIC API METHODS ----------------
    public static boolean compare(double v1, LengthUnit u1,
                                  double v2, LengthUnit u2) {

        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);

        return q1.equals(q2);
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        System.out.println("1 ft vs 12 inch: " +
                compare(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH));

        System.out.println("1 inch vs 1 inch: " +
                compare(1.0, LengthUnit.INCH, 1.0, LengthUnit.INCH));

        System.out.println("1 ft vs 2 ft: " +
                compare(1.0, LengthUnit.FEET, 2.0, LengthUnit.FEET));
    }
}