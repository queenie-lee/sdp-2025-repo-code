package comparator;

import java.util.Arrays;
import java.util.Comparator;

public class HigherOrderDemo {
    public static Comparator<String> compareInDirection(int direction) {
        return (x, y) -> direction * x.compareTo(y);
    }

    public static Comparator<String> reverse(Comparator<String> comparator) {
        return (x, y) -> comparator.compare(y, x);
    }

    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};

        Arrays.sort(words);
        System.out.println(Arrays.toString(words));

        Arrays.sort(words,
                compareInDirection(-1));
        System.out.println(Arrays.toString(words));

        Arrays.sort(words,
                reverse(String::compareToIgnoreCase));
        System.out.println(Arrays.toString(words));

        Arrays.sort(words,
                // the type cast is needed to specify the interface
                // matching the method reference
                ((Comparator<String>) String::compareToIgnoreCase)
                        .reversed());

        // a longer version that is simplified to the above by IntelliJ
        Arrays.sort(words,
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareToIgnoreCase(o2);
                    }
                }.reversed());
    }
}
