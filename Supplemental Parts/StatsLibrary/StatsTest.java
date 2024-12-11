import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;

public class StatsTest {

    @Test
    public void testFactorial() {
        assertEquals(BigInteger.ONE, Stats.factorial(0)); // 0! = 1
        assertEquals(BigInteger.ONE, Stats.factorial(1)); // 1! = 1
        assertEquals(BigInteger.valueOf(120), Stats.factorial(5)); // 5! = 120
        assertEquals(BigInteger.valueOf(3628800), Stats.factorial(10)); // 10! = 3628800
        assertThrows(IllegalArgumentException.class, () -> Stats.factorial(-1)); // Negative input
    }

    @Test
    public void testCombinationSolver() {
        assertEquals(10.0, Stats.combinationSolver(5, 2), 0.001); // 5C2 = 10
        assertEquals(1.0, Stats.combinationSolver(5, 0), 0.001); // 5C0 = 1
        assertEquals(1.0, Stats.combinationSolver(5, 5), 0.001); // 5C5 = 1
        assertEquals(252.0, Stats.combinationSolver(10, 5), 0.001); // 10C5 = 252
        assertThrows(IllegalArgumentException.class, () -> Stats.combinationSolver(-5, 2)); // Invalid n
        assertThrows(IllegalArgumentException.class, () -> Stats.combinationSolver(5, 6)); // r > n
    }

    @Test
    public void testHypergeometricDistribution() {
        double result = Stats.hypergeomtricDistribution(5, 10, 5, 2);
        assertEquals(0.3968, result, 0.001); // Calculated using formula
    }

    @Test
    public void testNegativeBinomialDistribution() {
        double result = Stats.negativeBinomialDistribution(3, 5, 0.5);
        assertEquals(0.1875, result, 0.0001); // Tolerance added for floating-point precision
    }

    @Test
    public void testPoissonDistribution() {
        double result = Stats.PoissonDistribution(2.0, 3); // lambda = 2.0, x = 3
        assertEquals(0.1804, result, 0.001); // e^(-2) * 2^3 / 3!
    }

    @Test
    public void testTchebysheffsTheorem() {
        double result = Stats.tchebysheffsTheorem(0, 1, -2, 2); // k = 2 (2 std deviations)
        assertEquals(0.75, result, 0.001); // 1 - 1/k^2
        assertThrows(IllegalArgumentException.class, () -> Stats.tchebysheffsTheorem(0, 1, 2, 1)); // Invalid bounds
        assertThrows(IllegalArgumentException.class, () -> Stats.tchebysheffsTheorem(0, 1, 0, 0.5)); // k <= 1
    }
}