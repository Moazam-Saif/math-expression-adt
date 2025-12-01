import static org.junit.Assert.*;   // for JUnit 4
import org.junit.Test;
import java.util.List;

class ExpressionTest {

    @Test
    void testNumberToString() {
        assertEquals("3", new NumberExpr(3).toString());
        assertEquals("1.5", new NumberExpr(1.5).toString());
    }

    @Test
    void testVariable() {
        assertEquals("x", new VariableExpr("x").toString());
    }

    @Test
    void testAdd() {
        Expression e = new AddExpr(List.of(new NumberExpr(1), new VariableExpr("x")));
        assertEquals("1 + x", e.toString());
    }

    @Test
    void testMultiply() {
        Expression e = new MultiplyExpr(List.of(new NumberExpr(2), new NumberExpr(3)));
        assertEquals("2 * 3", e.toString());
    }

    @Test
    void testEqualsStructural() {
        Expression a1 = new AddExpr(List.of(new NumberExpr(1), new VariableExpr("x")));
        Expression a2 = new AddExpr(List.of(new NumberExpr(1.000), new VariableExpr("x")));
        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    void testNotEqualDifferentOrder() {
        Expression e1 = new AddExpr(List.of(new NumberExpr(1), new VariableExpr("x")));
        Expression e2 = new AddExpr(List.of(new VariableExpr("x"), new NumberExpr(1)));
        assertNotEquals(e1, e2);
    }
}
