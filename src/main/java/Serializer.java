import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Serializer {
    public static State serialize(Path filename) {
        try {
            Files.lines(filename)
                    .map(line -> line.split(";"))
                    .forEach(value -> {

                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
