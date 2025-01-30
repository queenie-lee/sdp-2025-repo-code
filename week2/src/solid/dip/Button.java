package solid.dip;

public class Button {
    private Lamp lamp; // will need to change existing Lamp class if we want to use for, ex: vacuum. Solution: have a device with an on/off operation
    private boolean state;

    Button(Lamp lamp) { this.lamp = lamp; }
/**
 * Does not work for OOP, because it is not easily extensible

 * If design is rigid, there will be issues.

 * */
    public void push() {
        state = !state;
        if (state)
            lamp.turnOn(); // low-level subroutines
        else
            lamp.turnOff(); // low-level subroutines
    }
}