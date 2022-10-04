import static java.lang.System.currentTimeMillis;

public class RussianPeasants {

    public static int russianMultiplication(int x, int y) {
        int result = 0;

        while (x != 0) {
            if(x % 2 == 1) { // if the x component is odd then add y to running tally
                result += y;
            }
            x = x/2;
            y = y * 2;
        }

        return result;
    }

    public static void main(String[] args) {
        final long startTime = System.nanoTime();
        int result = russianMultiplication(1334, 1833);
        final long finishTime = System.nanoTime();
        final long timeTaken = finishTime - startTime;
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + timeTaken);
    }
}
