package chainofresponsibility;
// constructing a chain out of the processors
public class ChainOfResponsibilityGoF {

    public static void main(String... args) {
        FileParser textParser = new TextFileParser();
        FileParser presentationParser = new PresentationFileParser();
        FileParser audioParser = new AudioFileParser();
        FileParser videoParser = new VideoFileParser();

        textParser.setNextParser(presentationParser);
        presentationParser.setNextParser(audioParser);
        audioParser.setNextParser(videoParser);

        File file = new File(File.Type.AUDIO, "Dream Theater  - The Astonishing");

        System.out.println(textParser.parse(file));
    }

    interface FileParser {
        String parse(File file);

        void setNextParser(FileParser next); // construct a list out of them
    }

    public abstract static class AbstractFileParser implements FileParser {
        protected FileParser next;

        @Override
        public void setNextParser(FileParser next) {
            this.next = next;
        }
    }

    public static class TextFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.TEXT) { // checks type, if correct, does something
                return "Text file: " + file.getContent();
            } else if (next != null) { // if not, it goes to the next parser
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    public static class PresentationFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.PRESENTATION) {
                return "Presentation file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    public static class AudioFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.AUDIO) {
                return "Audio file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    public static class VideoFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.VIDEO) {
                return "Video file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }
}
