import java.lang.String;
import java.lang.Integer;

public class Utils {
    public static Tree<Integer, String> perfect(int size) {
        Tree<Integer, String> tree = new Tree<Integer, String>();
        perfectChild(tree, 0, size);
        return tree;
    }

    public static Tree<Integer, String> perfect(int init, int end) {
        Tree<Integer, String> tree = new Tree<>();
        perfectChild(tree, init, end);
        return tree;
    }

    public static void perfectChild(Tree<Integer, String> t, int init, int end) {
        int size = end - init;
        if (size < 2) {
            return;
        }
        int key = size / 2;
        t.inserir(init+key, "8");
        perfectChild(t, init, init + key);
        perfectChild(t, init + key , end);
    }
}
