/**
 * Immutable n-ary multiplication.
 */
import java.util.List;
import java.util.stream.Collectors;

public final class MultiplyExpr implements Expression {

    private final List<Expression> factors;

    /*
     * RI:
     *   - factors.size() >= 2
     *   - no nulls
     *   - list is unmodifiable
     *
     * AF(factors) = factors[0] * factors[1] * ...
     */

    public MultiplyExpr(List<Expression> factors) {
        this.factors = List.copyOf(factors);
        checkRep();
    }

    private void checkRep() {
        assert factors.size() >= 2;
        for (Expression e : factors) assert e != null;
    }

    public List<Expression> getFactors() {
        return factors;
    }

    @Override
    public String toString() {
        return factors.stream()
            .map(Expression::toString)
            .collect(Collectors.joining(" * "));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MultiplyExpr other)) return false;
        return this.factors.equals(other.factors);
    }

    @Override
    public int hashCode() {
        return factors.hashCode();
    }
}
