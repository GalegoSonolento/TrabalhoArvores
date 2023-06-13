import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StatusTest {
    State state = Serializer.deserialize(Path.of("teste.csv"));
    @Test
    void testePesquisaPorDataDeNascimento() {
        ArrayList<Pessoa> nascidos = state.pesquisaPorDataDeNascimento(15,03,2010);
        assertEquals(1, nascidos.size());
        Pessoa gina = nascidos.get(0);
        assertEquals(83085366306L, gina.getCPF());
        assertEquals(1610764889L, gina.getRG());
        assertEquals("Gina Pham", gina.getNome());
        assertEquals(LocalDate.of(2010, 03, 15), gina.getDataNascimento());
        assertEquals("Teresina", gina.getCidadeNatal());
    }
}
