package functions;

import java.util.List;

public class Employee {
    private final int age;
    private final String name;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public static List<Employee> load() {
        return List.of(
                new Employee("Tom Jones", 45),
                new Employee("Harry Major", 25),
                new Employee("Ethan Hardy", 65),
                new Employee("Nancy Smith", 15),
                new Employee("Deborah Sprightly", 29));
    }
}

