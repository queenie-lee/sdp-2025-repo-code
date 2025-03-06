package template;

import java.util.function.Consumer;

public class TemplateLambda {
    public static void withResource(Consumer<Resource> consumer) { // replaces execute, parameterised by Consumer
        try (Resource resource = new Resource()) {
            consumer.accept(resource); // Performs this operation on the given argument.
        }
    }

    public static void main(String... args) {
        withResource(Resource::useResource);
        withResource(Resource::employResource);
    }
}
