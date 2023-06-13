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
        ArrayList<Pessoa> nascidos = state.pesquisaPorDataDeNascimento(15,3,2010);
        assertEquals(1, nascidos.size());
        Pessoa gina = nascidos.get(0);
        assertEquals(83085366306L, gina.getCPF());
        assertEquals(1610764889L, gina.getRG());
        assertEquals("Gina Pham", gina.getNome());
        assertEquals(LocalDate.of(2010, 3, 15), gina.getDataNascimento());
        assertEquals("Teresina", gina.getCidadeNatal());
    }

    @Test
    void testePesquisaPorNome() {
        ArrayList<Pessoa> nigels = state.pesquisaPorNome("Nigel Gilbert");
        assertEquals(1, nigels.size());
        Pessoa nigel = nigels.get(0);
        assertEquals(28964587075L, nigel.getCPF());
        assertEquals(4767141575L, nigel.getRG());
        assertEquals("Nigel Gilbert", nigel.getNome());
        assertEquals(LocalDate.of(1958, 9, 26), nigel.getDataNascimento());
        assertEquals("João Pessoa", nigel.getCidadeNatal());
    }
}
