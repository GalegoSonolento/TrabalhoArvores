import java.util.NoSuchElementException;

public class PosOrdem implements Caminhamento {
    private Node next;
    private boolean goLeft = true;

    private boolean goUp = false;

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

        // TODO

        return current.getKey();
    }
}
