package helloworld;

import org.springframework.beans.factory.annotation.Autowired;

// setter is gone
// we have one field: messageProvider - final field, initialized with null
// reflectionAPI allows us to break all the rules
// DI gets this field (@Autowired - initialised by framework) - looks at type of this field, finds class of this bean, then sets the property there.
// Reflection API uses get fields (messageProvider), setAccessible, change the value of the field

public class StandardOutMessageRenderer implements MessageRenderer {

    @Autowired
    private final MessageProvider messageProvider = null;

    @Override
    public void render() {
        System.out.println(messageProvider.getMessage());
    }
}