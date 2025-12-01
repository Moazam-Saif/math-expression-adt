/**
 * Immutable number expression.
 */
public final class NumberExpr implements Expression {
    private final double value;

    // Rep invariant: no constraint beyond being a valid double
    // AF(value) = the number represented by 'value'

    public NumberExpr(double value) {
        this.value = value;
        checkRep();
    }

    private void checkRep() {}

    public double getValue() { return value; }

    @Override
    public String toString() {
        // recursive? here it's base case
        return (value == (long) value) ? Long.toString((long) value) : Double.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NumberExpr other))
            return false;
        // treat integers and 1.000 same
        return Math.abs(this.value - other.value) < 1e-9;
    }

    @Override
    public int hashCode() {
        // cannot use instanceof, but here not needed
        long bits = Double.doubleToLongBits(value);
        return (int)(bits ^ (bits >>> 32));
    }
}
