import java.util.function.Consumer;

public class Node<T extends Comparable<T>> {
    private Node<T> daddy;

    private final Node<T>[] children = new Node[]{null, null};
    private int cb;
    final private T key;
    private int height;

    public Node(Node<T> daddy, Node<T> rightSon, Node<T> leftSon, int cb, T key, int height) {
        this.daddy = daddy;
        this.children[0] = leftSon;
        this.children[1] = rightSon;
        this.cb = cb;
        this.key = key;
        this.height = height;
    }

    public Node(Node<T> daddy, Node<T> rightSon, Node<T> leftSon, int cb, T key) {
        this(daddy, rightSon, leftSon, cb, key, 1);
    }
    public Node(Node<K, V> daddy, K key, V value) {
        this(daddy, null, null, 0, key, value);
    }

    public Node<T> getDaddy() {
        return daddy;
    }

    public void setDaddy(Node<T> daddy) {
        this.daddy = daddy;
    }

    public Node<T> getRightSon() {
        return children[1];
    }

    public void setRightSon(Node<T> rightSon) {
        this.children[1] = rightSon;
    }

    public Node<T> getLeftSon() {
        return children[0];
    }

    public void setLeftSon(Node<T> leftSon) {
        this.children[0] = leftSon;
    }

    public void changeChild(int pos, Node<T> node) {
        this.children[pos] = node;
    }

    public Node<T> getChild(int pos) {
        return children[pos];
    }

    public int getCb() {
        return cb;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }

    public T getKey() {
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
