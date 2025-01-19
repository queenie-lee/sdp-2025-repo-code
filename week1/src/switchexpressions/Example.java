package switchexpressions;

enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

public class Example {
    public static void main(String[] args) {
        expression1(Day.WEDNESDAY);
    }

    static void expression1(Day day){
        final int numLetters;
        switch (day) {
            case MONDAY: // falls through
            case FRIDAY: // falls through
            case SUNDAY:
                numLetters = 6;
                break; // break terminates the switch statement.
            case TUESDAY:
                numLetters = 7;
                break;
            case THURSDAY:
            case SATURDAY:
                numLetters = 8;
                break;
            case WEDNESDAY:
                numLetters = 9;
                break;
            default:
                throw new IllegalStateException("Invalid day: " + day);
        }
        System.out.println(numLetters);
    }

    static void expression2(Day day) {
        final int numLetters;
        switch (day) { // new revised version of switch statement
            case MONDAY, FRIDAY, SUNDAY -> numLetters = 6; // gets rid of the break statement, no fall throughs. All branches are now independent
            case TUESDAY -> numLetters = 7;
            case THURSDAY, SATURDAY -> numLetters = 8;
            case WEDNESDAY -> numLetters = 9;
            default -> throw new IllegalStateException("Invalid day: " + day);
        }
        System.out.println(numLetters);
    }

    static void expression3(Day day) {
        System.out.println(
            switch (day) { // Right hand side contains an expression, rather than an assignment
                case MONDAY, FRIDAY, SUNDAY -> 6;
                case TUESDAY -> 7;
                case THURSDAY, SATURDAY -> 8;
                case WEDNESDAY -> 9;
                // default not needed because all possible values are covered!

                // switch expressions and switch statements used to throw a NullPointerException
                // if the value of the selector expression is null.
                // From Java 21, to add more flexibility, a null case label is available:
                case null -> -1;
            }
        );
    }


    static void expression4(Day day) {
        int numLetters = switch (day) {
            case MONDAY,
                FRIDAY,
                SUNDAY -> {
                System.out.println(6);
                yield 6;
            }
            case TUESDAY -> { // using a block expression
                System.out.println(7);
                yield 7; // only used in switches, acts like a return
            }
            case THURSDAY,
                SATURDAY -> {
                System.out.println(8);
                yield 8;
            }
            case WEDNESDAY -> {
                System.out.println(9);
                yield 9;
            }
            // default not needed because all possible values are covered!
        };
        System.out.println(numLetters);
    }
}