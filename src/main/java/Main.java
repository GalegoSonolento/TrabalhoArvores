import java.nio.file.Path;

public class Main {
    final static Path FILEPATH = Path.of(".\\teste.csv");
    public static void main(String[] args){
        State initialState = Serializer.deserialize(FILEPATH);

        Menu m1 = new Menu();
        m1.rosto(initialState);
    }
}