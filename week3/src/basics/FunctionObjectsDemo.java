package basics;

interface IntBinaryFunction {
    int apply(int i, int j);
}

// Version 1: standalone class (since Java 1.0)
class IntMinimumStandalone implements IntBinaryFunction {
    // a typical function object
    //    - it has no state (no instance variables)
    //    - it implements a single method that transforms its inputs and produces an output
    //    - it has no side effects (does not modify any global variables, does not write into files, etc.)
    @Override public int apply(int i, int j) {
        return i < j ? i : j;
    } // equivalent to mathematical function min(i, j)
}

public class FunctionObjectsDemo {
    static int arrayFunctionApplication(int[] array, IntBinaryFunction function) {
        int result = function.apply(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            result = function.apply(result, array[i]);
        }
        return result;
    }

    // Step 2: nested static class, with a more local name (since Java 1.1) -- recording: 1:21:29
    // private nested classes are not accessible from the outside
    private static class IntMinimumStatic implements IntBinaryFunction {
        @Override public int apply(int i, int j) {
            return i < j ? i : j;
        }
    }

    public static void main(String... args) {
        int[] a1 = { 5, 2, 4, 1, 3, 8 };

        // Version 1: standalone class
        int r1 = arrayFunctionApplication(a1, new IntMinimumStandalone());
        System.out.println(r1); // good, but the class name (IntMinimumStandalone) is global (is it needed?)

        // Version 2: nested static class
        int r2 = arrayFunctionApplication(a1, new IntMinimumStatic());
        System.out.println(r2); // the class is private and its name is FunctionObjectsDemo.IntMinimumStatic

        // Version 3: local class, with an even more local name that cannot be used elsewhere (since Java 1.1)
        // Moved declaration class inside the method (main)
        class IntMinimumLocal implements IntBinaryFunction {
            @Override public int apply(int i, int j) {
                return i < j ? i : j;
            }
        }
        int r3 = arrayFunctionApplication(a1, new IntMinimumLocal());
        System.out.println(r3); // good, but do we need the class name at all then?

        // Version 4: anonymous class (since Java 1.1) -- time: 1:23:40
        // The name of the class disappears. We only use this class once, so it is unnecessary.
        int r4 = arrayFunctionApplication(a1, new IntBinaryFunction() {
            @Override public int apply(int i, int j) { // SAM - Single Abstract Method
                return i < j ? i : j;
            }
        });
        System.out.println(r4); // good, but do we need to write the boilerplate?

        // Step 5: typed lambda with full body (since Java 8)
        // TODO: 1:27:55-- Review
        int r5 = arrayFunctionApplication(a1, (int i, int j) -> {
            return i < j ? i : j; // body of the method remains the same as in Step 4
        });
        System.out.println(r5); // good, but can Java not see that it's ints?

        // Step 6: short lambda with type inferencing (since Java 8)
        /**
         * If you run into any issues here, go back three steps and break it down to identify the problem.
         * */
        int r6 = arrayFunctionApplication(a1, (i, j) -> i < j ? i : j);
        System.out.println(r6); // good, but has no-one else ever written something like this?

        // Step 7: method reference (Java 8)
        // Reusing something that already exists in Math.min
        int r7 = arrayFunctionApplication(a1, Integer::min);
        System.out.println(r7);

        // Step 8: method reference to another function object
        int r8 = arrayFunctionApplication(a1, Integer::max);
        System.out.println(r8);

        // Step 9: method reference to yet another function object
        int r9 = arrayFunctionApplication(a1, Integer::sum);
        System.out.println(r9);
    }

}
