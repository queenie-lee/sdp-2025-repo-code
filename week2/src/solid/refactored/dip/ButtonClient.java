package solid.refactored.dip;

public interface ButtonClient { // does not need to know the exact nature of device
    void turnOn();
    void turnOff();
}
