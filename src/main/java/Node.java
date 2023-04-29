import java.util.function.Consumer;

public class Node {
    private Node daddy, rightSon, leftSon;
    private int cb;
    final private int key;
    private int height;

    public Node(Node daddy, Node rightSon, Node leftSon, int cb, int key, int height) {
        this.daddy = daddy;
        this.rightSon = rightSon;
        this.leftSon = leftSon;
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
        return rightSon;
    }

    public void setRightSon(Node rightSon) {
        this.rightSon = rightSon;
    }

    public Node getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(Node leftSon) {
        this.leftSon = leftSon;
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
            out.append(daddy.getKey());
        }
        out.append(", leftSon=");
        if (leftSon != null) {
            out.append(leftSon.getKey());
        }
        out.append(", rightSon=");
        if (rightSon != null) {
            out.append(rightSon.getKey());
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
