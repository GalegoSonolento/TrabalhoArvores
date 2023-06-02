import java.util.NoSuchElementException;

public class PosOrdem<T extends Comparable<T>> implements Caminhamento<T> {
    private Node<T> next;
    private boolean goLeft = true;

    private boolean goUp = false;

    @Override
    public void setRoot(Node<T> root) {
        this.next = root;
    }

    @Override
    public boolean hasNext() {
        return  next != null;
    }

    @Override
    public T next() {
        Node<T> current = this.next;

        if (current == null) {
            throw new NoSuchElementException();
        }

        // TODO

        return current.getKey();
    }
}
