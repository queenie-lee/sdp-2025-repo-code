package observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ObserverGoF {

    public static void main(String... args) {
        Observable observable = new Observable();
        new Observer1(observable);
        new Observer2(observable);

        observable.sendEvent("Hello World!");
    }

    interface Listener { // functional interface!
        void onEvent(Object event);
    }

    public static final class Observable { // button - something that can generate events
        private final Map<Object, Listener> listeners
            = new ConcurrentHashMap<>(); // for multi-thread environment

        public void register(Object key, Listener listener) {
            listeners.put(key, listener);
        }

        public void unregister(Object key) {
            listeners.remove(key);
        }

        public void sendEvent(Object event) { // called every time a button is pressed
            for (Listener listener : listeners.values()) {
                listener.onEvent(event);
            }
        }
    }

    public static class Observer1 {
        Observer1(Observable observable) {
            observable.register(this, new Listener() { // using anonymous class
                @Override
                public void onEvent(Object event) {
                    System.out.println(this.getClass() + ":" + event);
                }
            });
        }
    }

    public static final class Observer2 implements Listener {
        Observer2(Observable observable) {
            observable.register(this, this);
        }

        @Override
        public void onEvent(Object event) {
            System.out.println(this.getClass() + ":" + event);
        }
    }
}
