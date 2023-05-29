import java.util.function.Consumer;

public class Node {
    private Node daddy;

    private final Node[] children = new Node[]{null, null};
    private int cb;
    final private int key;
    private int height;

    public Node(Node daddy, Node rightSon, Node leftSon, int cb, int key, int height) {
        this.daddy = daddy;
        this.children[0] = leftSon;
        this.children[1] = rightSon;
        this.cb = cb;
        this.key = key;
        this.height = height;
    }

    public Node(Node daddy, Node rightSon, Node leftSon, int cb, int key) {
        this(daddy, rightSon, leftSon, cb, key, 1);
    }

    public Node(Node daddy, int key) {
        this(daddy, null, null, 0, key);
    }

    public Node getDaddy() {
        return daddy;
    }

    public void setDaddy(Node daddy) {
        this.daddy = daddy;
    }

    public Node getRightSon() {
        return children[1];
    }

    public void setRightSon(Node rightSon) {
        this.children[1] = rightSon;
    }

    public Node getLeftSon() {
        return children[0];
    }

    public void setLeftSon(Node leftSon) {
        this.children[0] = leftSon;
    }

    public void changeChild(int pos, Node node) {
        this.children[pos] = node;
    }

    public Node getChild(int pos) {
        return children[pos];
    }

    public int getCb() {
        return cb;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }

    public int getKey() {
        return key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
