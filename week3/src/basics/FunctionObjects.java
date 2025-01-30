package basics;

interface IntBinaryFunction {
    int apply(int i, int j);
}

// Option 1: standalone class
class IntMinimumStandalone implements IntBinaryFunction {
    @Override public int apply(int i, int j) {
        return i < j ? i : j;
    }
}

public class FunctionObjects {

    public static void main(String... args) {
        int[] a1 = { 5, 2, 4, 1, 3, 8 };

        int r1 = arrayFunctionApplication(a1, new IntMinimumStandalone());
        System.out.println(r1); // good, but the class name is global (is it needed?)

        int r2 = arrayFunctionApplication(a1, new IntMinimumStatic());
        System.out.println(r2); // the class is private and its name is FunctionObjectsDemo.IntMinimumStatic

        // Step 3: local class (even  more local name that cannot be used elsewhere)
        class IntMinimumLocal implements IntBinaryFunction {
            @Override public int apply(int i, int j) {
                return i < j ? i : j;
            }
        }
        int r3 = arrayFunctionApplication(a1, new IntMinimumLocal());
        System.out.println(r3); // good, but do we need the class name at all then?

        // Step 4: anonymous class (no name at all)
        int r4 = arrayFunctionApplication(a1, new IntBinaryFunction() {
            @Override public int apply(int i, int j) {
                return i < j ? i : j;
            }
        });
        System.out.println(r4); // good, but do we need to write the boilerplate?

        // Step 5: typed lambda with full body
        int r5 = arrayFunctionApplication(a1, (int i, int j) -> {
            return i < j ? i : j;
        });
        System.out.println(r5); // good, but can Java not see that it's ints?

        // Step 6: short lambda (type inferencing)
        int r6 = arrayFunctionApplication(a1, (i, j) -> i < j ? i : j);
        System.out.println(r6); // good, but has no-one else ever written something like this?

        // Step 7: method reference
        int r7 = arrayFunctionApplication(a1, Integer::min);
        System.out.println(r7);

        // Step 8: method reference to another function object
        int r8 = arrayFunctionApplication(a1, Integer::max);
        System.out.println(r8);

        // Step 9: method reference to yet another function object
        int r9 = arrayFunctionApplication(a1, Integer::sum);
        System.out.println(r9);
    }

    // Step 2: nested static class (more local name)
    // private nested classes are not accessible from the outside
    private static class IntMinimumStatic implements IntBinaryFunction {
        @Override public int apply(int i, int j) {
                return i < j ? i : j;
            }
    }

    static int arrayFunctionApplication(int[] array, IntBinaryFunction function) {
        int result = function.apply(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            result = function.apply(result, array[i]);
        }
        return result;
    }
}
