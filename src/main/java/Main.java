public class Main {
    final static String FILENAME = ".\\pessoas.csv";
    public static void main(String args[]){
        State initialState = new State();

        // TODO puxar do arquivo file -> initialState

        Menu m1 = new Menu();
        m1.rosto(initialState);
    }
}