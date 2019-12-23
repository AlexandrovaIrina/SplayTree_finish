import java.util.Iterator;

public class SplayTreeIterator<T extends Comparable> implements Iterator<T> {

    private SplayTree tree;

    public SplayTreeIterator(SplayTree tree) {
        this.tree = tree;
        // need more code
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
