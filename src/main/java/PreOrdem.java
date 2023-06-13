import java.util.NoSuchElementException;

public class PreOrdem<K extends Comparable<K>, V> implements Caminhamento<K, V> {
    private Node<K, V> next;

    @Override
    public void setRoot(Node<K, V> root) {
        this.next = root;
    }

    @Override
    public boolean hasNext() {
        return  next != null;
    }

    @Override
    public V next() {
        final Node<K, V> current = this.next;

        if (current == null) {
            throw new NoSuchElementException();
        }

        if (current.getLeftSon() != null) {
            this.next = current.getLeftSon();
            return current.getValue();
        }

        if (current.getRightSon() != null) {
            this.next = current.getRightSon();
            return current.getValue();
        }

        Node<K, V> dad = current.getDaddy();
        Node<K, V> child = current;
        while (dad != null && dad.getRightSon() == child) {
            child = dad;
            dad = child.getDaddy();
        }
        if (dad == null)
            this.next = null;
        else
            this.next = dad.getRightSon();

        return current.getValue();
    }

}
