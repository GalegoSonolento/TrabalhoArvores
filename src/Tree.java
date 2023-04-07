package src;

public class Tree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public int getHeight() {
        return root != null ? root.getHeight() : 0;
    }

    // TODO: Sobrecarregar o construtor com mais argumentos
    public Tree() {
        Node root = null;
    }

    public Tree(int key) {
        root = new Node(null, key);
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
            root = new Node(null, key);
            return;
        }
        while (true) {
            if (node.getKey() == key) throw new SameKeyException("Já existe uma chave com esse número.");
            if (node.getKey() > key) {
                if (node.getLeftSon() == null) {
                    node.setLeftSon(new Node(node, key));
                    break;
                }
                node = node.getLeftSon();
            } else {
                if (node.getRightSon() == null) {
                    node.setRightSon(new Node(node, key));
                    break;
                }
                node = node.getRightSon();
            }
        }
        // TODO: inserir um rebalanceamento

        int heightControl = 1;
        while (node != null) {
            heightControl++;
            if (heightControl > node.getHeight()) node.setHeight(heightControl);
            node = node.getDaddy();
        }
        // TODO: Incrementar a altura height ++;
        // TODO: Usar uma variável para controlar a profundidade do while e comparar a altura.

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

