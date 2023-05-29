import java.util.NoSuchElementException;

public class PreOrdem implements Caminhamento {
    private Node next;

    @Override
    public void setRoot(Node root) {
        this.next = root;
    }
    @Override
    public boolean hasNext() {
        return  next != null;
    }

    @Override
    public Integer next() {
        Node current = this.next;

        if (current == null) {
            throw new NoSuchElementException();
        }

        if (current.getLeftSon() != null) {
            this.next = current.getLeftSon();
            return current.getKey();
        }

        if (current.getRightSon() != null) {
            this.next = current.getRightSon();
            return current.getKey();
        }

        Node dad = current.getDaddy();
        Node child = current;
        while (dad != null && dad.getRightSon() == child) {
            child = dad;
            dad = child.getDaddy();
        }
        if (dad == null)
            this.next = null;
        else
            this.next = dad.getRightSon();

        return current.getKey();
    }

}
