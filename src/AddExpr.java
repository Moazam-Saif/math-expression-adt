/**
 * Immutable n-ary addition.
 */
import java.util.List;
import java.util.stream.Collectors;

public final class AddExpr implements Expression {

    private final List<Expression> terms;

    /*
     * RI:
     *   - terms.size() >= 2
     *   - no nulls
     *   - list is unmodifiable
     *
     * AF(terms) = terms[0] + terms[1] + ...
     */

    public AddExpr(List<Expression> terms) {
        this.terms = List.copyOf(terms);
        checkRep();
    }

    private void checkRep() {
        assert terms.size() >= 2;
        for (Expression e : terms) assert e != null;
    }

    public List<Expression> getTerms() {
        return terms; // safe — unmodifiable
    }

    @Override
    public String toString() {
        // recursive: each sub-expression prints itself
        return terms.stream()
            .map(Expression::toString)
            .collect(Collectors.joining(" + "));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AddExpr other)) return false;
        return this.terms.equals(other.terms);
    }

    @Override
    public int hashCode() {
        // cannot use instanceof, but we don’t need it
        return terms.hashCode();
    }
}
