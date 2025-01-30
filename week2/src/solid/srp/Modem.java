package solid.srp;

/** Single responsibility:
 * Think about how things may be used into the future
 *
 * Can split it into two responsibilities:
 * 1. Connecting and disconnecting
 * 2. Sending and receiving
 *
 * Don't split it into four pieces - introduces unnecessarily levels of abstraction
 * You still need to connect pieces together
 *
 * */
public interface Modem {
    void dial(String pno);

    void hangup();

    void send(char c);

    char receive();
}