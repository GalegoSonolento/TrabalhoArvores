import java.util.*;

public class Tree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public int getHeight() {
        return root != null ? root.getHeight() : 0;
    }

    // TODO: Sobrecarregar o construtor com mais argumentos
    public Tree() {}

    public Tree(int key) {
        root = new Node(null, key);
    }

    public void preOrdem() {

    }

    public void posOrdem() {

    }

    public void emOrdem() {

    }

    public Integer[] emLargura() {
        if (this.root == null)
            return new Integer[]{null};
        int size = (1 << this.getHeight()) - 1;
        Integer[] result = new Integer[size];
        Node node;
        int level = 0;
        Queue<Node> next = new LinkedList<>();
        next.add(this.root);
        for (int i = 0; i < result.length; i++) {
            node = next.poll();
            if (node == null) {
                result[i] = null;
                continue;
            }
            result[i] = node.getKey();
            next.add(node.getLeftSon());
            next.add(node.getRightSon());
        }
        return result;
    }

    // TODO: identificar onde colocar o height
    public void inserir(int key) throws SameKeyException {
        Node node = root;
        if (node == null) {
            root = new Node(null, key);
            return;
        }
        while (true) {
            if (node.getKey() == key) throw new SameKeyException(String.format("Já existe uma chave com o número %d.", key));
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
        Node problem = null;
        int heightControl = 1;
        while (node != null) {
            heightControl++;
            if (heightControl > node.getHeight()) node.setHeight(heightControl);
            int left = node.getLeftSon() == null ? 0 : node.getLeftSon().getHeight();
            int right = node.getRightSon() == null ? 0 : node.getRightSon().getHeight();
            node.setCb(left-right);
            if (node.getCb() > 1 || node.getCb() < -1)
                problem = node;
            node = node.getDaddy();
        }
        // TODO: Incrementar a altura height ++;
        // TODO: Usar uma variável para controlar a profundidade do while e comparar a altura.
        if (problem != null)
            this.rebalancear(problem);
    }

    public void rebalancear(Node node) {
        if (node.getCb() > 1) {
            if (node.getLeftSon().getCb() < 0) {
                rebalancearEsquerda(node.getLeftSon());
            }
            rebalancearDireita(node);
        }
        if (node.getCb() < -1) {
            if (node.getRightSon().getCb() > 0)
                rebalancearDireita(node);
            rebalancearEsquerda(node);
        }
    }

    public void rebalancearDireita(Node node) {
        Node left = node.getLeftSon();
        Node outro = left.getRightSon();
        left.setRightSon(node);
        left.setDaddy(node.getDaddy());
        if (node.getDaddy() != null)
            node.getDaddy().setRightSon(left);
        else
            this.root = left;
        node.setDaddy(left);
        node.setLeftSon(outro);
        if (outro != null) {
            outro.setDaddy(node);
        }
    }

    public void rebalancearEsquerda(Node node) {
        Node right = node.getRightSon();
        Node outro = right.getLeftSon();
        right.setLeftSon(node);
        right.setDaddy(node.getDaddy());
        if (node.getDaddy() != null)
            node.getDaddy().setLeftSon(right);
        else
            this.root = right;
        node.setDaddy(right);
        node.setRightSon(outro);
        if (outro != null) {
            outro.setDaddy(node);
        }
    }

    public Node pesquisar(int keySearch) {
        Node nodeControl = root;
        // esse for evita elses desnecessários e mantém o código mais limpo (até porque o número de
        // pesquisas não vai passar do valor da altura da árvore)
        for (int i=0; i <= getHeight(); i++) {
            int nodeControlKey = nodeControl.getKey();
            if (keySearch == nodeControlKey)
                return nodeControl;
            if (keySearch > nodeControlKey) {
                if (nodeControl.getRightSon() != null)
                    nodeControl = nodeControl.getRightSon();
            } else
                if (nodeControl.getLeftSon() != null)
                    nodeControl = nodeControl.getLeftSon();
        }
        return null;
    }

    // TODO: String grande com a árvore em tabs
    @Override
    public String toString() {
        return "Tree{}";
    }
}

