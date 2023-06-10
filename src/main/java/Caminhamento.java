import java.util.Iterator;

public interface Caminhamento<K extends Comparable<K>, V> extends Iterator<K> {
    public void setRoot(Node<K, V> root);
}
