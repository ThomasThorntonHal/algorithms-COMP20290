import java.util.HashMap;
import java.util.Map;

import static java.lang.System.nanoTime;

public class Fibonacci {
    static int fibonacciIterative(int n) {
        if (n<=1)
            return 1;

        int fib = 1;
        int prevFib =  1;

        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib = fib + prevFib;
            prevFib = temp;
        }
        return fib;
    }

    static int fibonacciRecursive(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }

    private static Map<Integer, Integer> memo = new HashMap<>();

    static int fibonacciRecursiveMemoization(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        if(memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = fibonacciRecursiveMemoization(n-1) + fibonacciRecursiveMemoization(n-2);
        // store result in HashMap
        memo.put(n, result);

        return result;
    }

    public static void main (String[] args) {
        int n = 19;

        // Time iterative Fibonacci method
        final long startTimeIterative = nanoTime();
        System.out.println(fibonacciIterative(n));
        final long endTimeIterative = nanoTime();
        final long timeTakenIterative = endTimeIterative - startTimeIterative;
        System.out.println("Time taken for iterative method: " + timeTakenIterative + "\n");

        // Time recursive Fibonacci method
        final long startTimeRecursive = nanoTime();
        System.out.println(fibonacciRecursive(n));
        final long endTimeRecursive = nanoTime();
        final long timeTakenRecursive = endTimeRecursive - startTimeRecursive;
        System.out.println("Time taken for recursive method: " + timeTakenRecursive + "\n");

        // Time recursive Fibonacci method with memoization
        final long startTimeRecursiveMem = nanoTime();
        System.out.println(fibonacciRecursiveMemoization(n));
        final long endTimeRecursiveMem = nanoTime();
        final long timeTakenRecursiveMem = endTimeRecursiveMem - startTimeRecursiveMem;
        System.out.println("Time taken for recursive method with memoization: " + timeTakenRecursiveMem);
    }
}
