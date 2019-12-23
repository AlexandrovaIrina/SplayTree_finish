import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

public class SplayTree<T extends Comparable<T>> implements Set<T> {

    private int size;
    private Node root;

    public SplayTree() {
        root = null;
        size = 0;
    }

    public Node<T> getRoot(){
        return root;
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (root == null) return false;
        T obj = (T) o;
        root = this.splay(root, obj);
        return root.value.equals(obj);
    }

    @Override
    public Iterator<T> iterator() {
        return new SplayTreeIterator<T>(this);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (T t: this) {
            array[i] = t;
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return (T[])this.toArray();
    }
// использовать астрактное дерево
    @Override
    // not done
    public boolean add(T t) {

        if (root == null) {
            root = new Node<T>(t);
            size = 0;
            return true;
        }

        root = splay(root, t);
        Node<T> node = new Node<T>(t);
        if(t.compareTo(node.value) < 0) {
            node.left = root.left;

        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (root == null) return false;

        T t = (T) o;
        root = splay(root, t);
        if (t.equals(root.value)) {
            if (root.left == null) {
                root = root.right;
            }
            else {
                Node<T> x = root.right;
                root = root.left;
                splay(root, t);
                root.right = x;
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    // not done
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return collection.stream().allMatch(this::add);
    }

    @Override
    // not done
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    // not done
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        this.forEach(this::remove);
    }

    @Override
    // not done
    public Spliterator<T> spliterator() {
        return null;
    }

    // like usual binary tree + splay()
    public Node search(T value) {
        return null;
    }


    // 3 variants - zig, zig-zig, zag
    private Node<T> splay(Node<T> node, T value) {
        if (root == null) return null;

        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                return node;
            }

            if (value.compareTo(node.left.value) < 0) {
                node.left.left = splay(node.left.left, value);
                node = rotateRight(node);
            }
            else {
                if (value.compareTo(node.left.value) > 0) {
                    node.left.right = splay(node.left.right, value);
                    if (node.left.right != null)
                        node.left = rotateLeft(node.left);
                }
            }
            return node.left == null ? node : rotateRight(node);
        }

        if (value.compareTo(node.value) > 0) {
            if (node.right == null) {
                return node;
            }

            if (value.compareTo(node.right.value) < 0) {
                node.right.left = splay(node.right.left, value);
                if (node.right.left != null)
                    node.right = rotateRight(node.right);
            }
            else {
                if (value.compareTo(node.right.value) > 0) {
                    node.right.right = splay(node.right.right, value);
                    node = rotateRight(node);
                }
            }
            return node.right == null ? node : rotateLeft(node);
        }
        return node;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> newNode = node.right;
        node.left = newNode.right;
        newNode.right = node;
        return newNode;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> newNode = node.left;
        node.right = newNode.left;
        newNode.left = node;
        return newNode;
    }
}
