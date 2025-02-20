package helloworld;

// Issue: Dependency inversion. One class depends on the other. If we change one, we will need to recompile the other.

public class HelloWorldDecoupled {
    public static void main(String... args) {
        // With var, the compiler *infers* the type of the variables
        // from the expression on the right-hand side
        // (StandardOutMessageRenderer and HelloWorldMessageProvider, respectively).
        var mr = new StandardOutMessageRenderer();
        var mp = new HelloWorldMessageProvider();
        mr.setMessageProvider(mp);
        mr.render();
    }
}
