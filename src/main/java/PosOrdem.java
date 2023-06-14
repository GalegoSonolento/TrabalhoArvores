import java.util.NoSuchElementException;

public class PosOrdem<K extends Comparable<K>, V> implements Caminhamento<K, V> {
    private Node<K, V> next;

    private boolean first = true;

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
        Node<K, V> current = this.next;

        if (current == null) {
            throw new NoSuchElementException();
        }

        if (first) {
            while (current.getLeftSon() != null)
                current = current.getLeftSon();
            if (current.getRightSon() != null)
                current = current.getRightSon();
        }
        first = false;

        this.next = current.getDaddy();

        if (this.next != null && this.next.getRightSon() != current && this.next.getRightSon() != null){
            this.next = this.next.getRightSon();
            while (this.next.getLeftSon() != null)
                this.next = this.next.getLeftSon();
            if (this.next.getRightSon() != null)
                this.next = this.next.getRightSon();
        }

        return current.getValue();
    }
}
