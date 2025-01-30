package solid.lsp;

public class Penguin extends Bird {
    /**
     * UnsupportedOperationException is a standard solution/trick in Java
     * It is used in standard libraries (e.g. Connection), however purists consider it bad design.
     * Exceptions are only discovered at runtime

     * Goal: try to use benefits of static type checking in Java (by compiler or IDE) before the program runs
     * Try to fix all problems during compile time, before runtime.
     * Helps us to save time on testing, discovering bugs later on
     * Programming language helps you to write programs that do not contain errors, helping you save time.

     * Instead, create a subclass FlyingBird which extends Bird, and put the fly method there.
     * */
    @Override
    public void fly() { throw new UnsupportedOperationException(); } // standard solution
}