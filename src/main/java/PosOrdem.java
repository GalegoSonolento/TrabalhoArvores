import java.util.NoSuchElementException;

public class PosOrdem<K extends Comparable<K>, V> implements Caminhamento<K, V> {
    private Node<K, V> next;
    private boolean goRight = false;

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
    public V next() {
        Node<K, V> current = this.next;
        // TODO implement
        // throw new RuntimeException("not implemented");
        if (current == null) {
            throw new NoSuchElementException();
        }

        if (!goUp) {
            if (goRight) {
                if (current.getRightSon() != null)
                    current = current.getRightSon();
                goUp = true;
            }
            goRight = true;
            while (current.getLeftSon() != null)
                current = current.getLeftSon();
            if (current.getRightSon() != null)
                current = current.getRightSon();
        } else {
            goUp = false;
        }

        System.out.println(current);
        this.next = current.getDaddy();

        return current.getValue();
    }
}
