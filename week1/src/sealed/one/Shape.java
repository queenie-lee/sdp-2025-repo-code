package sealed.one;

// Java did not have controls over how many subclasses you could have, except for FINAL (would have no subclasses) and ABSTRACT
// Shape can only have three subclasses: Circle, Square and Rectangle

public sealed class Shape permits Circle, Square, Rectangle {
}

