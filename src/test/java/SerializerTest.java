import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class SerializerTest {
    //State state = Serializer.deserialize(Path.of("teste.csv"));

    // TODO Teste para o issue #3
    @Test
    void testeDeserializerMesmoNascimento() {
        try {
            Serializer.deserialize(Path.of("erro.csv"));
        } catch (Exception e) {
            assertEquals("SameKeyException: Já existe uma chave com o número 2003-04-01.", e.toString());
        }
    }
   /* @Test
    void testeDeserializer() {
        Pessoa chloe = state.pesquisaPorCPF(771186845);
        assertNotNull(chloe);
        assertEquals(771186845, chloe.getCPF());
        assertEquals(1748643745, chloe.getRG());
        assertEquals("Chloe Smith", chloe.getNome());
        assertEquals("São Gonçalo", chloe.getCidadeNatal());
        assertEquals("16/05/1954", chloe.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }*/
}
