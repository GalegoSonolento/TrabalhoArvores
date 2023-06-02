import java.util.*;

public class Tree<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root;

    private Caminhamento<T> iterator;

    public Node<T> getRoot() {
        return root;
    }

    public int getHeight() {
        return root != null ? root.getHeight() : 0;
    }

    // TODO: Sobrecarregar o construtor com mais argumentos
    public Tree() {}

    public Tree(T key) {
        root = new Node<T>(null, key);
    }

    public ArrayList<Node<T>> preOrdem() {
        ArrayList<Node<T>> list = new ArrayList<>();
        preOrdem(this.root, list);
        return list;
    }

    private void preOrdem(Node<T> current, ArrayList<Node<T>> list) {
        if (current == null) {
            return;
        }
        list.add(current);
        preOrdem(current.getLeftSon(), list);
        preOrdem(current.getRightSon(), list);
    }

    public ArrayList<Node<T>> posOrdem() {
        ArrayList<Node<T>> list = new ArrayList<>();
        posOrdem(this.root, list);
        return list;
    }

    private void posOrdem(Node<T> current, ArrayList<Node<T>> list) {
        if (current == null) {
            return;
        }
        posOrdem(current.getLeftSon(), list);
        posOrdem(current.getRightSon(), list);
        list.add(current);
    }

    public ArrayList<Node<T>> emOrdem() {
        ArrayList<Node<T>> list = new ArrayList<>();
        emOrdem(this.root, list);
        return list;
    }

    private void emOrdem(Node<T> current, ArrayList<Node<T>> list) {
        if (current == null) {
            return;
        }
        emOrdem(current.getLeftSon(), list);
        list.add(current);
        emOrdem(current.getRightSon(), list);
    }

    public LinkedList<T> emLargura() {
        if (this.root == null)
            return null;
        int size = (1 << this.getHeight()) - 1;
        LinkedList<T> result = new LinkedList<>();
        Node<T> node;
        Queue<Node<T>> next = new LinkedList<>();
        next.add(this.root);
        for (int i = 0; i < size; i++) {
            node = next.poll();
            if (node == null) {
                result.add(i, null);
                continue;
            }
            result.add(i, node.getKey());
            next.add(node.getLeftSon());
            next.add(node.getRightSon());
        }
        return result;
    }

    public void inserir(T key) throws SameKeyException {
        Node<T> node = root;
        if (node == null) {
            root = new Node<T>(null, key);
            return;
        }
        while (true) {
            if (node.getKey().equals(key)) throw new SameKeyException(String.format("Já existe uma chave com o número %s.", key));
            int pos = 1-((node.getKey().compareTo(key) + 1) >> 1);
            if (node.getChild(pos) == null) {
                node.changeChild(pos, new Node<T>(node, key));
                break;
            }
            node = node.getChild(pos);
        }

        Node<T> problem = null;
        while (node != null) {
            node.updateHeightCb();
            if (node.getCb() > 1 || node.getCb() < -1)
                problem = node;
            node = node.getDaddy();
        }

        if (problem != null)
            this.rebalancear(problem);
    }

    // esse método pode retornar um boolean pra dizer se deu certo ou n
    // exclusão ainda não terminada
    // exclusão por copia
    public boolean excluir(T keyToExclude) {
        Node<T> excluded = this.pesquisar(keyToExclude);
        if (excluded == null){
            return false;
        }
        final Node<T> leftSon = excluded.getLeftSon();
        final Node<T> rightSon = excluded.getRightSon();
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
        Node<T> substitute = leftSon;

        while (substitute.getRightSon() != null)
            substitute = substitute.getRightSon();

        Node<T> substituteLeftSon = substitute.getLeftSon();
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

    private void updateAndRebalence(Node<T> current) {
        while (current != null) {
            current.updateHeightCb();
            if (current.getCb() > 1 || current.getCb() < -1) {
                this.rebalancear(current);
            }
            assert current != current.getDaddy();
            current = current.getDaddy();
        }
    }

    public static <T extends Comparable<T>> void changeDaddy(Node<T> child, Node<T> daddy) {
        if (daddy != child) {
            if (daddy != null) {
                if (daddy.getKey().compareTo(child.getKey()) > 0) {
                    daddy.setLeftSon(child);
                }
                else {
                    daddy.setRightSon(child);
                }
            }
            child.setDaddy(daddy);
        }
    }

    private static <T extends Comparable<T>> void replace(Node<T> toKill, Node<T> replacement) {
        Node<T> dad = toKill.getDaddy();
        Node<T> leftSon = toKill.getLeftSon();
        Node<T> rightSon = toKill.getRightSon();

        if (dad != replacement) {
            if (dad != null) {
                if (dad.getKey().compareTo(toKill.getKey()) > 0) {
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

    public void rebalancear(Node<T> node) {
        if (node.getCb() <= 1 && node.getCb() >= -1)
            return;
        int pos = node.getCb() > 0 ? 1 : 0;
        int oppositeChild = 1 - pos;
        int childCb = node.getChild(oppositeChild).getCb();
        if (childCb != 0 && childCb * node.getCb() < 0) {
            rebalancear(pos, node.getChild(oppositeChild));
        }
        rebalancear(oppositeChild, node);
    }

    public void rebalancear(int pos, Node<T> node) {
        Node<T> child = node.getChild(pos);
        int oppositeChild = 1 - pos;
        Node<T> childOtherChild = child.getChild(oppositeChild);

        child.changeChild(oppositeChild, node);
        child.setDaddy(node.getDaddy());
        if (node.getDaddy() == null)
            this.root = child;
        changeDaddy(child, node.getDaddy());
        node.setDaddy(child);
        node.changeChild(pos, childOtherChild);
        if (childOtherChild != null) {
            childOtherChild.setDaddy(node);
        }
        Node<T> current = node;
        while (current != null) {
            current.updateHeightCb();
            current = current.getDaddy();
        }
    }

    public Node<T> pesquisar(T keySearch) {
        Node<T> nodeControl = root;
        // esse for evita elses desnecessários e mantém o código mais limpo (até porque o número de
        // pesquisas não vai passar do valor da altura da árvore)
        for (int i=0; i <= getHeight(); i++) {
            int nodeControlKey = nodeControl.getKey().compareTo(keySearch);
            if (0 == nodeControlKey)
                return nodeControl;
            if (nodeControlKey < 0) {
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

    @Override
    public Iterator<T> iterator() {
        iterator.setRoot(root);
        return iterator;
    }

    public void setIterator(Caminhamento<T> iterator) {
        this.iterator = iterator;
    }
}

