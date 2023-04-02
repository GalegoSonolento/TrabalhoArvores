package src;

public class Tree {
    private Node root;
    private int height;

    public Node getRoot() {
        return root;
    }

    public int getHeight() {
        return height;
    }

    // TODO: Sobrecarregar o construtor com mais argumentos
    public Tree() {
        Node root = null;
        height = 0;
    }

    public Tree(int key) {
        root = new Node(null, null, null, 0, key);
        height = 1;
    }

    public void preOrdem() {

    }

    public void posOrdem() {

    }

    public void emOrdem() {

    }

    // TODO: identificar onde colocar o height
    public void inserir(int key) throws SameKeyException {
        Node node = root;
        if (node == null) {
            root = new Node(null, null, null, 0, key);
            height = 1;
            return;
        }
        int heightControl = 1;
        while (true) {
            heightControl ++;
            if (node.getKey() == key) throw new SameKeyException("Já existe uma chave com esse número.");
            else if (node.getKey() > key) {
                if (node.getLeftSon() == null) {
                    node.setLeftSon(new Node(node, null, null, 0, key));
                    if (heightControl > height) height = heightControl;
                    break;
                } else {
                    node = node.getLeftSon();
                }
            } else {
                if (node.getRightSon() == null) {
                    node.setRightSon(new Node(node, null, null, 0, key));
                    if (heightControl > height) height = heightControl;
                    break;
                } else {
                    node = node.getRightSon();
                }
            }
        }

        // TODO: Incrementar a altura height ++;
        // TODO: Usar uma variável para controlar a profundidade do while e comparar a altura.
        // TODO: inserir um rebalanceamento
    }

    public void rebalancear() {

    }

    public void pesquisar() {

    }

    // TODO: String grande com a árvore em tabs
    @Override
    public String toString() {
        return "src.Tree{}";
    }
}

