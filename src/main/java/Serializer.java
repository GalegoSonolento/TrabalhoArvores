import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Serializer {
    public static State deserialize(Path filename) {
        State result = new State();
        try {
            Files.lines(filename)
                    .map(line -> line.split(";"))
                    .forEach(values -> {
                        long cpf = Long.parseLong(values[0]);
                        long rg = Long.parseLong(values[1]);
                        String nome = values[2];
                        LocalDate data = LocalDate.parse(values[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        String cidade = values[4];
                        result.adicionaPessoa(new Pessoa(cpf, rg, data, nome, cidade));
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
