/**
 * Immutable variable expression like x, y.
 */
public final class VariableExpr implements Expression {
    private final String name;

    // RI: name matches [a-zA-Z]+
    // AF(name) = variable with that name

    public VariableExpr(String name) {
        this.name = name;
        checkRep();
    }

    private void checkRep() {
        assert name != null;
        assert name.matches("[a-zA-Z]+");
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VariableExpr other))
            return false;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
