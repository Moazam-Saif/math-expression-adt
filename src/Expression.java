/**
 * Expression represents an immutable mathematical expression.
 *
 * ADT definition:
 *   Expression = Number(double value)
 *               + Variable(String name)
 *               + Add(List<Expression> terms)
 *               + Multiply(List<Expression> factors)
 *
 * Representation rule:
 *   Implementations must be immutable.
 *
 * Safety:
 *   No rep exposure: defensive copies or unmodifiable lists.
 */
public interface Expression {

    @Override
    String toString();

    @Override
    boolean equals(Object that);

    @Override
    int hashCode();
}
