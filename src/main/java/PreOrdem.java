import java.util.NoSuchElementException;

public class PreOrdem<T extends Comparable<T>> implements Caminhamento<T> {
    private Node<T> next;

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
        final Node<T> current = this.next;

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

        Node<T> dad = current.getDaddy();
        Node<T> child = current;
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
