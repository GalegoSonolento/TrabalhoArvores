package src;

public class Node {
    private Node daddy, rightSon, leftSon;
    private int cb;
    private int key;
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

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
