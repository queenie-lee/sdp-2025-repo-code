import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Person {

    public enum Sex {
        FEMALE,
        MALE
    }

    private int age;
    private String givenName;
    private String surname;
    private Sex sex;
    private String phone;
    private String address;
    private String email;

    public int getAge() { return age; }
    public String getGivenName() { return givenName; }
    public String getSurname() { return surname; }
    public Sex getSex() { return sex; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }

    public void printName() {
        System.out.println("\n" +
                "Name: " + getGivenName() + " " + getSurname() + "\n" +
                "Age: " + getAge() + " " +
                "Sex: " + getSex() + "\n" +
                "EMail: " + getEmail() + "\n" +
                "Phone: " + getPhone() + "\n" +
                "Address: " + getAddress());
    }

    private static class Builder {
        Person person = new Person();

        Builder givenName(String givenName) { person.givenName = givenName; return this; }
        Builder surname(String surname) { person.surname = surname; return this; }
        Builder age(int age) { person.age = age; return this; }
        Builder sex(Sex sex) { person.sex = sex; return this; }
        Builder email(String email) { person.email = email; return this; }
        Builder phone(String phone) { person.phone = phone; return this; }
        Builder address(String address) { person.address = address; return this; }
        Person build() { return person; }
    }

    public static List<Person> createShortList() {
        List<Person> people = new ArrayList<>();

        people.add(
                new Builder()
                        .givenName("Bob")
                        .surname("Baker")
                        .age(21)
                        .sex(Sex.MALE)
                        .email("bob.baker@example.com")
                        .phone("201-121-4678")
                        .address("44 4th St, Smallville, KS 12333")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("Jane")
                        .surname("Doe")
                        .age(25)
                        .sex(Sex.FEMALE)
                        .email("jane.doe@example.com")
                        .phone("202-123-4678")
                        .address("33 3rd St, Smallville, KS 12333")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("John")
                        .surname("Doe")
                        .age(25)
                        .sex(Sex.MALE)
                        .email("john.doe@example.com")
                        .phone("202-123-4678")
                        .address("33 3rd St, Smallville, KS 12333")
                        .build()
        );
        return people;
    }

    public void printNameStyle1() {
        System.out.println("\n" +
                "Name: " + this.getGivenName() + " "
                + this.getSurname() + "\n" +
                "Age: " + this.getAge() + " " +
                "Sex: " + this.getSex() + "\n" +
                "EMail: " + this.getEmail() + "\n" +
                "Phone: " + this.getPhone() + "\n" +
                "Address: " + this.getAddress());
    }

    public void printNameStyle2() {
        System.out.println("\n" +
                "Name: " + this.getSurname() + " "
                + this.getGivenName() + "\n" +
                "Age: " + this.getAge() + " " +
                "Sex: " + this.getSex() + "\n" +
                "EMail: " + this.getEmail() + "\n" +
                "Phone: " + this.getPhone() + "\n" +
                "Address: " + this.getAddress());
    }

    public String printCustom(Function<Person,String> f){
        return f.apply(this);
    }
}
