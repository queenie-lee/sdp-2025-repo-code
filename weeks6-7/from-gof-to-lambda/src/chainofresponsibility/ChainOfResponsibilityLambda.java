package chainofresponsibility;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class ChainOfResponsibilityLambda {

    public static Optional<String> parseText(File file) {
        return Optional.of(file)
            .filter(f -> f.getType() == File.Type.TEXT)
            .map(f -> "Text file: " + f.getContent());
    }

    public static Optional<String> parsePresentation(File file) {
        return Optional.of(file) // nice way of writing "ifs" using lambda expression
            .filter(f -> f.getType() == File.Type.PRESENTATION) // will get empty optional if false
            .map(f -> "Presentation file: " + f.getContent()); // "IF" is hidde here.
    }

    public static Optional<String> parseAudio(File file) {
        return Optional.of(file)
            .filter(f -> f.getType() == File.Type.AUDIO)
            .map(f -> "Audio file: " + f.getContent());
    }

    public static Optional<String> parseVideo(File file) {
        return Optional.of(file)
            .filter(f -> f.getType() == File.Type.VIDEO)
            .map(f -> "Video file: " + f.getContent());
    }

    public static void main(String... args) {
        File file = new File(File.Type.AUDIO, "Dream Theater  - The Astonishing");

        System.out.println(
            Stream.<Function<File, Optional<String>>>of(
                    ChainOfResponsibilityLambda::parseText,
                    ChainOfResponsibilityLambda::parsePresentation,
                    ChainOfResponsibilityLambda::parseAudio,
                    ChainOfResponsibilityLambda::parseVideo)
                .map(f -> f.apply(file)) // .apply exists in type Function
                .flatMap(Optional::stream) // flatmap removes empty steams
                .findFirst() // lazy process; find first successful one.
                .orElseThrow(() -> new RuntimeException("Unknown file: " + file)) // throw an exception if cannot find
        );
    }
}
