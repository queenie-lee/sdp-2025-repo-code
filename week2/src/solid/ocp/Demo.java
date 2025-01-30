package solid.ocp;

class Circle implements Shape {
}

class Square implements Shape {

}
/**
 * Violates Open-Closed principle
 * If you wanted to draw a triangle, you would need to modify the code below.
 * Sequences of If/Else, would need to modify each one.
 * == TO fix:
 * add method draw() in interface
 * add method draw() in each class which implements Shape
 * Demo --> draw method: Loop only has one method: draw()
 * */
public class Demo {
    public void draw(Shape[] shapes) {
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                // draw circle
            }
            else if (shape instanceof Square) {
                // draw square
            }
        }
    }
}