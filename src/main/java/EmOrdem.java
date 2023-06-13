import java.util.NoSuchElementException;

public class EmOrdem<K extends Comparable<K>, V> implements Caminhamento<K, V> {
    private Node<K, V> next;
    private boolean goLeft = true;

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

        if (goLeft) {
            while (current.getLeftSon() != null)
                current = current.getLeftSon();
            goLeft = false;
        }

        if (current.getRightSon() != null) {
            this.next = current.getRightSon();
            goLeft = true;
        } else {
            if (current.getDaddy() != null) {
                Node<K, V> dad = current.getDaddy();
                Node<K, V> child = current;
                while (dad != null && dad.getRightSon() == child) {
                    child = dad;
                    dad = child.getDaddy();
                }
                this.next = dad;
            }
        }

        return current.getValue();
    }
}
