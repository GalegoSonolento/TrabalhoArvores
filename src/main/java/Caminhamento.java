import java.util.Iterator;

public interface Caminhamento<T extends Comparable<T>> extends Iterator<T> {
    public void setRoot(Node<T> root);
}
