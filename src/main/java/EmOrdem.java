import java.util.NoSuchElementException;

public class EmOrdem<T extends Comparable<T>> implements Caminhamento<T> {
    private Node<T> next;
    private boolean goLeft = true;

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
                Node<T> dad = current.getDaddy();
                Node<T> child = current;
                while (dad != null && dad.getRightSon() == child) {
                    child = dad;
                    dad = child.getDaddy();
                }
                this.next = dad;
            }
        }

        return current.getKey();
    }
}
