import java.util.*;

public class Tree<K extends Comparable<K>, V> implements Iterable<K> {
    private Node<K, V> root;

    private Caminhamento<K, V> iterator;

    public Node<K, V> getRoot() {
        return root;
    }

    public int getHeight() {
        return root != null ? root.getHeight() : 0;
    }

    // TODO: Sobrecarregar o construtor com mais argumentos
    public Tree() {}

    public Tree(K key, V value) {
        root = new Node<K, V>(null, key, value);
    }

    public ArrayList<Node<K, V>> preOrdem() {
        ArrayList<Node<K, V>> list = new ArrayList<>();
        preOrdem(this.root, list);
        return list;
    }

    private void preOrdem(Node<K, V> current, ArrayList<Node<K, V>> list) {
        if (current == null) {
            return;
        }
        list.add(current);
        preOrdem(current.getLeftSon(), list);
        preOrdem(current.getRightSon(), list);
    }

    public ArrayList<Node<K, V>> posOrdem() {
        ArrayList<Node<K, V>> list = new ArrayList<>();
        posOrdem(this.root, list);
        return list;
    }

    private void posOrdem(Node<K, V> current, ArrayList<Node<K, V>> list) {
        if (current == null) {
            return;
        }
        posOrdem(current.getLeftSon(), list);
        posOrdem(current.getRightSon(), list);
        list.add(current);
    }

    public ArrayList<Node<K, V>> emOrdem(Node<K, V> node) {
        ArrayList<Node<K, V>> list = new ArrayList<>();
        emOrdem(node, list);
        return list;
    }

    public ArrayList<Node<K, V>> emOrdem() {
        return emOrdem(this.root);
    }

    private void emOrdem(Node<K, V> current, ArrayList<Node<K, V>> list) {
        if (current == null) {
            return;
        }
        emOrdem(current.getLeftSon(), list);
        list.add(current);
        emOrdem(current.getRightSon(), list);
    }

    public LinkedList<K> emLargura() {
        if (this.root == null)
            return null;
        int size = (1 << this.getHeight()) - 1;
        LinkedList<K> result = new LinkedList<>();
        Node<K, V> node;
        Queue<Node<K, V>> next = new LinkedList<>();
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

    public void inserir(K key, V value) throws SameKeyException {
        Node<K, V> node = root;
        if (node == null) {
            root = new Node<K, V>(null, key, value);
            return;
        }
        while (true) {
            if (node.getKey().equals(key)) throw new SameKeyException(String.format("Já existe uma chave com o número %s.", key));
            int pos = node.getKey().compareTo(key) > 0 ? 0 : 1;
            if (node.getChild(pos) == null) {
                node.changeChild(pos, new Node<K, V>(node, key, value));
                break;
            }
            node = node.getChild(pos);
        }

        Node<K, V> problem = null;
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
    public boolean excluir(K keyToExclude) {
        Node<K, V> excluded = this.pesquisarInterno(keyToExclude);
        if (excluded == null){
            return false;
        }
        final Node<K, V> leftSon = excluded.getLeftSon();
        final Node<K, V> rightSon = excluded.getRightSon();
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
        Node<K, V> substitute = leftSon;

        while (substitute.getRightSon() != null)
            substitute = substitute.getRightSon();

        Node<K, V> substituteLeftSon = substitute.getLeftSon();
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

    private void updateAndRebalence(Node<K, V> current) {
        while (current != null) {
            current.updateHeightCb();
            if (current.getCb() > 1 || current.getCb() < -1) {
                this.rebalancear(current);
            }
            assert current != current.getDaddy();
            current = current.getDaddy();
        }
    }

    public static <K extends Comparable<K>, V> void changeDaddy(Node<K, V> child, Node<K, V> daddy) {
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

    private static <K extends Comparable<K>, V> void replace(Node<K, V> toKill, Node<K, V> replacement) {
        Node<K, V> dad = toKill.getDaddy();
        Node<K, V> leftSon = toKill.getLeftSon();
        Node<K, V> rightSon = toKill.getRightSon();

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

    public void rebalancear(Node<K, V> node) {
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

    public void rebalancear(int pos, Node<K, V> node) {
        Node<K, V> child = node.getChild(pos);
        int oppositeChild = 1 - pos;
        Node<K, V> childOtherChild = child.getChild(oppositeChild);

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
        Node<K, V> current = node;
        while (current != null) {
            current.updateHeightCb();
            current = current.getDaddy();
        }
    }

    /**
     * Pesquisa por um nó usando sua chave
     * @param keySearch chave do nó que estamos procurando
     * @return O nó que contém a chave keySearch ou nulo caso nenhum nó seja encontrado
     */
    private Node<K, V> pesquisarInterno(K keySearch) {
        Node<K, V> nodeControl = root;
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

    public V pesquisar(K keySearch) {
        Node<K, V> node = pesquisarInterno(keySearch);
        if (node == null) {
            return null;
        }
        return node.getValue();
    }

    /**
     * Parte da interface Iterable, permite caminhar pela arvore vendo cada chave individualmente.
     * <br />
     * Exemplo de uso:
     * <pre>
     *     {@code
     * Tree<Integer> tree = new Tree<>(1);
     * tree.inserir(-1);
     * tree.inserir(3);
     * tree.setIterator(new EmOrdem<>());
     * for (Integer v : tree)
     *     System.Out.println(v);
     * }
     * <pre>
     * Output = -1, 1, 3
     * Exemplo de uso com outros caminhamentos:
     * <pre>
     *     {@code
     * Tree<Integer> tree = new Tree<>(1);
     * tree.inserir(-1);
     * tree.inserir(3);
     * tree.setIterator(new PreOrdem<>());
     * for (Integer v : tree)
     *     System.Out.println(v);
     * }
     * <pre>
     * Output = 1, -1, 3
     * @return Iterador da arvore começando pela raiz seguindo a ordem armazenada em this.iterator
     */
    @Override
    public Iterator<K> iterator() {
        iterator.setRoot(root);
        return iterator;
    }

    public void setIterator(Caminhamento<K, V> iterator) {
        this.iterator = iterator;
    }

    // TODO: String grande com a árvore em tabs
    @Override
    public String toString() {
        return "Tree{}";
    }

    public Node<K, V> StartsWithGenerico(K key) {
        Node<K, V> nodeControl = root;
        for (int i=0; i <= getHeight(); i++) {
            int nodeControlKey = nodeControl.getKey().compareTo(key);
            if (0 == nodeControlKey)
                return nodeControl;
            if (nodeControlKey > 0) {
                return nodeControl;
            } else
            if (nodeControl.getLeftSon() != null)
                nodeControl = nodeControl.getLeftSon();
        }
        return null;
    }

    public Node<K, V> EndsWithGenerico(K key) {
        Node<K, V> nodeControl = root;
        for (int i=0; i <= getHeight(); i++) {
            int nodeControlKey = nodeControl.getKey().compareTo(key);
            if (0 == nodeControlKey)
                return nodeControl;
            if (nodeControlKey < 0) {
                return nodeControl;
            } else
            if (nodeControl.getLeftSon() != null)
                nodeControl = nodeControl.getLeftSon();
        }
        return null;
    }
}

