public class RecursionExample {
    public static void main(String... args) {
        int f = factorial(4);
        System.out.println(f);
    }

    public static int factorial(int n) {
        if (n == 1)
            return 1;

        int p = factorial(n - 1);

        return n * p;
    }
}
