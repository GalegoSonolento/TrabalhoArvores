import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilterTest {
    State state = Serializer.deserialize(Path.of("teste.csv"));

    @Test
    void testeFilter() {
        String[] expected = new String[]{"Aileen Cummings", "Aimee Stein", "Aiyana Tate", "Alejandra Key", "Alexis Johnson", "Alexus Osborn", "Alexus Sweeney", "Aliana Herring", "Alicia Greer", "Alicia Hutchinson", "Alijah Cochran", "Alijah Kane", "Alivia Lutz", "Alvin Wright", "Alyvia Barnes", "Amanda Ryan", "Anastasia Mccoy", "Angel Bruce", "Anthony Rodriguez", "Arianna Mayer", "Ariel Blankenship", "Ashanti Olson", "Ashley Campos", "Ashley Davis", "Ashlyn Shea", "Audrina Hardy", "August Allen", "Ayaan Day", "Ayaan Pruitt", "Aydin Poole"};
        ArrayList<Pessoa> result = state.pesquisaPorNomeParcial("A");
        for (Pessoa p :
                result) {
            assertTrue(Arrays.stream(expected).anyMatch(m -> m.equals(p.getNome())));
        }
    }
}