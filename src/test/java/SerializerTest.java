import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class SerializerTest {
    State state = Serializer.deserialize(Path.of("teste.csv"));
    @Test
    void testeDeserializer() {
        Pessoa chloe = state.pesquisaPorCPF(771186845);
        assertNotNull(chloe);
        assertEquals(771186845, chloe.getCPF());
        assertEquals(1748643745, chloe.getRG());
        assertEquals("Chloe Smith", chloe.getNome());
        assertEquals("São Gonçalo", chloe.getCidadeNatal());
        assertEquals("16/05/1954", chloe.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
