import java.util.function.Consumer;

public class Node<K extends Comparable<K>, V> {
    private Node<K, V> daddy;

    private final Node<K, V>[] children = new Node[]{null, null};
    private int cb;
    final private K key;
    private int height;
    private V value;

    public Node(Node<K, V> daddy, Node<K, V> rightSon, Node<K, V> leftSon, int cb, K key, int height, V value) {
        this.daddy = daddy;
        this.children[0] = leftSon;
        this.children[1] = rightSon;
        this.cb = cb;
        this.key = key;
        this.height = height;
        this.value = value;
    }

    public Node(Node<K, V> daddy, Node<K, V> rightSon, Node<K, V> leftSon, int cb, K key, V value) {
        this(daddy, rightSon, leftSon, cb, key, 1, value);
    }
    public Node(Node<K, V> daddy, K key, V value) {
        this(daddy, null, null, 0, key, value);
    }

    public Node<K, V> getDaddy() {
        return daddy;
    }

    public void setDaddy(Node<K, V> daddy) {
        this.daddy = daddy;
    }

    public Node<K, V> getRightSon() {
        return children[1];
    }

    public void setRightSon(Node<K, V> rightSon) {
        this.children[1] = rightSon;
    }

    public Node<K, V> getLeftSon() {
        return children[0];
    }

    public void setLeftSon(Node<K, V> leftSon) {
        this.children[0] = leftSon;
    }

    public void changeChild(int pos, Node<K, V> node) {
        this.children[pos] = node;
    }

    public Node<K, V> getChild(int pos) {
        return children[pos];
    }

    public int getCb() {
        return cb;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }

    public K getKey() {
        return key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public V getValue() {
        return value;
    }

    public void updateHeightCb() {
        int left = this.getLeftSon() == null ? 0 : this.getLeftSon().getHeight();
        int right = this.getRightSon() == null ? 0 : this.getRightSon().getHeight();
        this.cb = left-right;
        this.height = 1 + Integer.max(left, right);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Node { this=");
        out.append(this.hashCode());
        out.append(", daddy=");
        if (daddy != null) {
            out.append(daddy.hashCode());
        }
        out.append(", leftSon=");
        if (children[0] != null) {
            out.append(children[0].hashCode());
        }
        out.append(", rightSon=");
        if (children[1] != null) {
            out.append(children[1].hashCode());
        }
        out.append(", cb=");
        out.append(cb);
        out.append(", key=");
        out.append(key);
        out.append(", height=");
        out.append(height);
        out.append(" }");
        return out.toString();
    }
}
