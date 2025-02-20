package helloworld;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public final class MessageSupportFactory {

    private static final MessageSupportFactory instance = new MessageSupportFactory();

    private MessageRenderer renderer;
    private MessageProvider provider;

    public static MessageSupportFactory getInstance() {
        return instance;
    } // !! implements singleton design pattern

    public MessageRenderer getMessageRenderer() {
        return renderer;
    } // !!

    public MessageProvider getMessageProvider() {
        return provider;
    } // !!

    // constructor is private.
    private MessageSupportFactory() {
        Properties props = new Properties();

        try {
            // Here, using var is perfectly fine - we are not interested in the exact type
            // (which the compiler infers to be InputStream, from the declaration of getResourceAsStream)
            // MessageSupportFactory.class is a *class literal* (see https://docs.oracle.com/javase/specs/jls/se19/html/jls-15.html#jls-15.8.2)
            // REMEMBER: the resources directory (with beans.properties)
            // needs to be marked as "Resources Root" in IntelliJ (see https://www.jetbrains.com/help/idea/content-roots.html)
            try (var fis = MessageSupportFactory.class.getResourceAsStream("/beans.properties")) { // standard way of getting access to resources
                props.load(fis); // load = reading a file
            }

            // get the implementation classes
            // renderer.class and provider.class is from resources/beans.properties (contains key-value pairs)
            String rendererClass = props.getProperty("renderer.class");
            renderer = (MessageRenderer) newInstanceOf(rendererClass);

            String providerClass = props.getProperty("provider.class");
            provider = newTypeSafeInstanceOf(providerClass, MessageProvider.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     Use ReflectiveOperationException super class
     * */
    private Object newInstanceOf(String className) throws ReflectiveOperationException { // core functionality

        Class<?> classObject = Class.forName(className);
        Constructor<?> constructor = classObject.getDeclaredConstructor();
        return constructor.newInstance(); // can throw away a bunch of Instances. DO NOT use a try block
    }

    // Type safe version below, without any type casting needed.

    private <T> T newTypeSafeInstanceOf(String className, Class<T> klass) throws ReflectiveOperationException {

        // just an example of using something different from ? inside <> after Class/Constructor
        Class<? extends T> classObject = Class.forName(className).asSubclass(klass);
        Constructor<? extends T> constructor = classObject.getDeclaredConstructor();
        return constructor.newInstance();
    }

}
