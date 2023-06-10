import java.util.NoSuchElementException;

public class PosOrdem<K extends Comparable<K>, V> implements Caminhamento<K, V> {
    private Node<K, V> next;
    private boolean goLeft = true;

    private boolean goUp = false;

    @Override
    public void setRoot(Node<K, V> root) {
        this.next = root;
    }

    @Override
    public boolean hasNext() {
        return  next != null;
    }

    @Override
    public K next() {
        Node<K, V> current = this.next;

        if (current == null) {
            throw new NoSuchElementException();
        }

        // TODO

        return current.getKey();
    }
}
