package src;

public class Node {
    private Node daddy, rightSon, leftSon;
    private int fb;
    private int key;

    public Node(Node daddy, Node rightSon, Node leftSon, int fb, int key) {
        this.daddy = daddy;
        this.rightSon = rightSon;
        this.leftSon = leftSon;
        this.fb = fb;
        this.key = key;
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

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
