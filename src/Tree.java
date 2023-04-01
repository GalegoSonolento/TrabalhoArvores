package src;

public class Tree {
    private Node root;
    private int height;

    private class Node {
        private Node daddy, rightSon, leftsSon;
        private int cb;
        private int key;

        public Node(Node daddy, Node rightSon, Node leftsSon, int cb, int key) {
            this.daddy = daddy;
            this.rightSon = rightSon;
            this.leftsSon = leftsSon;
            this.cb = cb;
            this.key = key;

        }
    }

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

    public void preOrdem(){

    }

    public void posOrdem(){

    }

    public void emOrdem(){

    }

    // TODO: identificar onde colocar o height
    public void inserir(int key) throws SameKeyException {
        Node node = root;
        if(node == null) {
            root = new Node(null, null, null, 0, key);
            return;
        }
        while(true){
            if (node.key == key) throw new SameKeyException("Já existe uma chave com esse número.");
            else if (node.key > key) {
                if (node.leftsSon == null) {
                    node.leftsSon = new Node(node, null, null, 0, key);
                    break;
                }
                else {
                    node = node.leftsSon;
                }
            }
            else {
                if (node.rightSon == null) {
                    node.rightSon = new Node(node, null, null, 0, key);
                    break;
                }
                else {
                    node = node.rightSon;
                }
            }
        }

        // TODO: Incrementar a altura height ++;
        // TODO: Usar uma variavel para controlar a profundidade do while e comparar a altura.
        // TODO: inserir um rebalancIMENTO
    }

    public void rebalancear(){

    }

    public void pesquisar(){

    }

    // TODO: String grande com a árvore em tabs
    @Override
    public String toString() {
        return "src.Tree{}";
    }
}

