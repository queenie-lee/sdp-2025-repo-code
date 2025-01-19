package sealed.one;
// Rectangle is sealed, has only has one subclass FilledRectangle
public sealed class Rectangle extends Shape permits FilledRectangle {
    public double length, width;
}