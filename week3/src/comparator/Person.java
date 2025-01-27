package comparator;

public class Person {
    private final String first;
    private final String last;
    private final String middle;

    public Person(String first, String middle, String last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    public Person(String first, String last) {
        this(first, null, last);
    }

    public String getFirstName() {
        return first;
    }

    public String getMiddleName() {
        return middle;
    }

    public String getLastName() {
        return last;
    }

    public String getName() {
        return middle == null
            ? first + " " + last
            : first + " " + middle + " " + last;
    }

    public String toString() {
        return getName();
    }
}
