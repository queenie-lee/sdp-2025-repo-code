package solid.refactored.dip;

public class Lamp implements ButtonClient { // can replace vacuum with Lamp here, when trying to use this for something else.
    @Override
    public void turnOn() { /* implementation */ }
    @Override
    public void turnOff() { /* implementation */ }
}
