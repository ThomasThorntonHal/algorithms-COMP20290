public class Hanoi {

    static void towersOfHanoi(int n, String source, String destination, String auxiliary) {

        if(n == 1) { // base case
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }
        towersOfHanoi(n-1, source, auxiliary, destination);
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        towersOfHanoi(n-1, auxiliary, destination, source);
    }

    public static void main(String[] args) {
        int n = 3;
        towersOfHanoi(n, "A", "B", "C");
    }
}
