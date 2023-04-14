public class Utils {
    public static Tree perfect(int size) {
        Tree tree = new Tree();
        perfectChild(tree, 0, size);
        return tree;
    }

    public static Tree perfect(int init, int end) {
        Tree tree = new Tree();
        perfectChild(tree, init, end);
        return tree;
    }

    public static void perfectChild(Tree t, int init, int end) {
        int size = end - init;
        if (size < 2) {
            return;
        }
        int key = size / 2;
        t.inserir(init+key);
        perfectChild(t, init, init + key);
        perfectChild(t, init + key , end);
    }
}
