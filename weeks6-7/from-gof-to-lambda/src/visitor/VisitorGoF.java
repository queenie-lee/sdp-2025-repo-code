package visitor;

import java.util.Arrays;
import java.util.List;

/* you have classes and elements (square, circle, rectangle)
* Choice: implement a number of algorithms that work with these elements (e.g. perimeter, area)
* Visitor: collect them in one place, instead of polluting classes independently.
* Uses double dispatch */
public class VisitorGoF {
    public static void main(String... args) {
        List<Element> figures = Arrays.asList(new Circle(4), new Square(5), new Rectangle(6, 7));

        double totalArea = 0.0;
        Visitor<Double> areaVisitor = new AreaVisitor();
        for (Element figure : figures) {
            totalArea += figure.accept(areaVisitor);
        }
        System.out.println("Total area = " + totalArea);

        double totalPerimeter = 0.0;
        Visitor<Double> perimeterVisitor = new PerimeterVisitor();
        for (Element figure : figures) {
            totalPerimeter += figure.accept(perimeterVisitor);
        }
        System.out.println("Total perimeter = " + totalPerimeter);
    }

    interface Element {
        <T> T accept(Visitor<T> visitor);
    }

    interface Visitor<T> {
        T visit(Square element);

        T visit(Circle element);

        T visit(Rectangle element);
    }

    public static class Square implements Element {
        final double side;

        public Square(double side) {
            this.side = side;
        }

        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this); // method overloading. Calls visit for square.
        }
    }

    public static class Circle implements Element {
        final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class Rectangle implements Element {
        final double width;
        final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    // All collected in the same place. Doesn't need to be introduced into subclasses.
    public static class AreaVisitor implements Visitor<Double> {
        public Double visit(Square element) {
            return element.side * element.side;
        }

        public Double visit(Circle element) {
            return Math.PI * element.radius * element.radius;
        }

        public Double visit(Rectangle element) {
            return element.height * element.width;
        }
    }

    public static class PerimeterVisitor implements Visitor<Double> {
        public Double visit(Square element) {
            return 4 * element.side;
        }

        public Double visit(Circle element) {
            return 2 * Math.PI * element.radius;
        }

        public Double visit(Rectangle element) {
            return (2 * element.height + 2 * element.width);
        }
    }
}
