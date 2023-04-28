import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public ArrayList<Node> preOrdem() {
        ArrayList<Node> list = new ArrayList<>();
        Tree.preOrdem(this.root, list);
        return list;
    }

    private static void preOrdem(Node current, ArrayList<Node> list) {
        if (current == null) {
            return;
        }
        list.add(current);
        preOrdem(current.getLeftSon(), list);
        preOrdem(current.getRightSon(), list);
    }

    public ArrayList<Node> posOrdem() {
        ArrayList<Node> list = new ArrayList<>();
        Tree.posOrdem(this.root, list);
        return list;
    }

    private static void posOrdem(Node current, ArrayList<Node> list) {
        if (current == null) {
            return;
        }
        posOrdem(current.getLeftSon(), list);
        posOrdem(current.getRightSon(), list);
        list.add(current);
    }

    public ArrayList<Node> emOrdem() {
        ArrayList<Node> list = new ArrayList<>();
        Tree.emOrdem(this.root, list);
        return list;
    }

    private static void emOrdem(Node current, ArrayList<Node> list) {
        if (current == null) {
            return;
        }
        emOrdem(current.getLeftSon(), list);
        list.add(current);
        emOrdem(current.getRightSon(), list);
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
        while (node != null) {
            node.updateHeightCb();
            if (node.getCb() > 1 || node.getCb() < -1)
                problem = node;
            node = node.getDaddy();
        }
        // TODO: Incrementar a altura height ++;
        // TODO: Usar uma variável para controlar a profundidade do while e comparar a altura.
        if (problem != null)
            this.rebalancear(problem);
    }

    // esse método pode retornar um boolean pra dizer se deu certo ou n
    // exclusão ainda não terminada
    // exclusão por copia
    public boolean excluir(int keyToExclude) {
        Node excluded = this.pesquisar(keyToExclude);
        if (excluded == null){
            return false;
        }
        Node leftSon = excluded.getLeftSon();
        Node rightSon = excluded.getRightSon();
        if (rightSon == null && leftSon == null) {
            if (excluded == root) {
                root = null;
                return true;
            }
            Tree.replace(excluded, null);
            updateAndRebalence(excluded.getDaddy());
            return true;
        }
        // TODO: trocar a ordem dos testes de 1 filho e 2 filhos, fiz agora nessa ordem exclusivamente para fins didáticos
        if (leftSon != null && rightSon == null) {
            if (excluded == root) {
                excluded.getLeftSon().setDaddy(null);
                root = excluded.getLeftSon();
                return true;
            }
            Tree.replace(excluded, leftSon);
            updateAndRebalence(leftSon);
            return true;
        }
        if (leftSon == null) {
            if (excluded == root) {
                excluded.getRightSon().setDaddy(null);
                root = excluded.getRightSon();
                return true;
            }
            Tree.replace(excluded, rightSon);
            updateAndRebalence(rightSon);
            return true;
        }
        Node substitute = leftSon;

        while (substitute.getRightSon() != null)
            substitute = substitute.getRightSon();

        Node substituteLeftSon = substitute.getLeftSon();
        if (substituteLeftSon != null) {
            substituteLeftSon.setDaddy(substitute.getDaddy());
        }
        if (substitute != leftSon)
            substitute.getDaddy().setRightSon(substituteLeftSon);

        Tree.replace(excluded, substitute);

        if (excluded == root)
            root = substitute;

        updateAndRebalence(leftSon);

        return true;
    }

    private void updateAndRebalence(Node current) {
        while (current != null) {
            current.updateHeightCb();
            if (current.getCb() > 1 || current.getCb() < -1) {
                System.out.println("a");
                this.rebalancear(current);
            }
            assert current != current.getDaddy();
            current = current.getDaddy();
        }
    }

    private static void replace(Node toKill, Node replacement) {
        Node dad = toKill.getDaddy();
        Node leftSon = toKill.getLeftSon();
        Node rightSon = toKill.getRightSon();

        if (dad != replacement) {
            if (dad != null) {
                if (toKill.getKey() < dad.getKey()) {
                    dad.setLeftSon(replacement);
                }
                else {
                    dad.setRightSon(replacement);
                }
            }
            if (replacement != null) {
                replacement.setDaddy(dad);
            }
        }

        if (replacement != leftSon) {
            if (leftSon != null) {
                leftSon.setDaddy(replacement);
            }
            if (replacement != null) {
                replacement.setLeftSon(leftSon);
            }
        }

        if (replacement != rightSon) {
            if (rightSon != null) {
                rightSon.setDaddy(replacement);
            }
            if (replacement != null) {
                replacement.setRightSon(rightSon);
            }
        }
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
                rebalancearDireita(node.getRightSon());
            rebalancearEsquerda(node);
        }
    }

    public void rebalancearDireita(Node node) {
        Node left = node.getLeftSon();
        Node leftRight = left.getRightSon();

        left.setRightSon(node);
        left.setDaddy(node.getDaddy());
        if (node.getDaddy() != null)
            node.getDaddy().setRightSon(left);
        else
            this.root = left;
        node.setDaddy(left);
        node.setLeftSon(leftRight);
        if (leftRight != null) {
            leftRight.setDaddy(node);
        }
        Node current = node;
        while (current != null) {
            current.updateHeightCb();
            current = current.getDaddy();
        }
    }

    public void rebalancearEsquerda(Node node) {
        Node right = node.getRightSon();
        Node rightLeft = right.getLeftSon();

        right.setLeftSon(node);
        right.setDaddy(node.getDaddy());
        if (node.getDaddy() != null)
            node.getDaddy().setLeftSon(right);
        else
            this.root = right;
        node.setDaddy(right);
        node.setRightSon(rightLeft);
        if (rightLeft != null) {
            rightLeft.setDaddy(node);
        }

        while (node != null) {
            node.updateHeightCb();
            node = node.getDaddy();
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

