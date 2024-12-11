import java.math.BigInteger;

public class Stats {

    /**
     * Calculates the factorial of a non-negative integer.
     * Factorial is the product of all positive integers less than or equal to n.
     * @param n The number to calculate factorial for (must be non-negative)
     * @return The factorial of n as a BigInteger
     * @throws IllegalArgumentException if n is negative
     */
    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }

        BigInteger nBig = BigInteger.valueOf(n);
        BigInteger result = BigInteger.ONE;
        
        for (BigInteger i = BigInteger.ONE; i.compareTo(nBig) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        
        return result;
    }

    /**
     * Calculates combinations (n choose r), representing the number of ways to select r items 
     * from a set of n items where order doesn't matter. This is also known as the binomial
     * coefficient.
     * 
     * The formula used is: C(n,r) = n! / (r! * (n-r)!)
     *
     * @param n The size of the set (must be non-negative)
     * @param r The size of the subset to choose (must be between 0 and n inclusive)
     * @return The number of combinations
     * @throws IllegalArgumentException if n < 0 or if r is not between 0 and n inclusive
     */
    public static double combinationSolver(int n, int r) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        if (r < 0 || r > n) {
            throw new IllegalArgumentException("r must be between 0 and n");
        }
        
        BigInteger nFact = factorial(n);
        BigInteger rFact = factorial(r);
        BigInteger nMinusRFact = factorial(n - r);
        
        return nFact.divide(rFact.multiply(nMinusRFact)).doubleValue();
    }

    /**
     * Computes the hypergeometric distribution probability.
     *
     * @param r the number of successes in the population
     * @param N the size of the population
     * @param n the number of draws
     * @param y the number of observed successes
     * @return the hypergeometric distribution probability as a double
     */
    public static double hypergeomtricDistribution(int r, int N, int n, int y) {
        double combo_ry = combinationSolver(r, y);
        double combo_NminR_nminy = combinationSolver(N - r, n - y);
        double combo_Nn = combinationSolver(N, n);
        return (combo_ry * combo_NminR_nminy) / combo_Nn;
    }

    /**
     * Computes the expected value of the hypergeometric distribution.
     *
     * @param n the number of draws
     * @param r the number of successes in the population
     * @param N the size of the population
     * @return the expected value as a double
     */
    public double hypergeomtricDistribution_expected(int n, int r, int N) {
        return (double) (n * r) / N;
    }

    /**
     * Computes the variance of the hypergeometric distribution.
     *
     * @param r the number of successes in the population
     * @param n the number of draws
     * @param N the size of the population
     * @return the variance as a double
     */
    public double hypergeomtricDistribution_variance(int r, int n, int N) {
        return n * ((double) r / N) * ((double) (N - r) / N) * ((double) (N - n) / (N - 1));
    }

    /**
     * Computes the probability for the negative binomial distribution.
     *
     * @param r the number of successes
     * @param y the number of trials
     * @param p the probability of success in each trial
     * @return the probability as a double
     */
    public static double negativeBinomialDistribution(int r, int y, double p) {
        double q = 1 - p;
        return combinationSolver(y - 1, r - 1) * Math.pow(p, r) * Math.pow(q, y - r);
    }

    /**
     * Computes the probability for the Poisson distribution.
     *
     * @param lambda the average number of occurrences in a fixed interval
     * @param x the actual number of occurrences
     * @return the probability as a double
     */
    public static double PoissonDistribution(double lambda, int x) {
        return (Math.pow(Math.E, -lambda) * Math.pow(lambda, x)) / factorial(x).doubleValue();
    }

    /**
     * Applies Tchebyshev's theorem to determine the probability of a value falling
     * within a specified range.
     *
     * @param mean the mean of the distribution
     * @param stdDev the standard deviation of the distribution
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     * @return the probability as a double
     * @throws IllegalArgumentException if lowerBound >= upperBound or k <= 1
     */
    public static double tchebysheffsTheorem(double mean, double stdDev, double lowerBound, double upperBound) {
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("Lower bound must be less than upper bound.");
        }

        double k = (upperBound - mean) / stdDev;

        if (k <= 1) {
            throw new IllegalArgumentException("k must be greater than 1 for Tchebyshev's Theorem to apply.");
        }

        return (1 - (1 / (k * k)));
    }

    /**
     * Computes the uniform distribution probability.
     *
     * @param d the upper limit of the interval
     * @param c the lower limit of the interval
     * @param b the maximum value in the range
     * @param a the minimum value in the range
     * @return the probability as a double
     */
    public double uniformDistribution(int d, int c, int b, int a) {
        return (double) (d - c) / (b - a);
    }
}