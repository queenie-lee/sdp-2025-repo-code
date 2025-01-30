package solid.refactored.ocp;

class Circle implements Shape {
    @Override
    public void draw() {
        // draw circle
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        // draw square
    }
}

public class Demo {
    public void draw(Shape[] shapes) { // closed for modification. Does not need to be rewritten if we want to extend for functionality
        for (Shape shape : shapes) {
            // Type of object is determined at runtime --> determines what version of the method to call
            // Polymorphic call --> allows us to provide different ways of implementing this procedure.
            shape.draw(); // dyanamic polymorphism, allows us to provide different ways to draw
        }
    }
}