import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

class SplayTree<V extends Comparable<V>> extends AbstractSet<V> implements Set<V> {
    private int size;
    private SplayNode<V> root;

    public SplayTree() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) return false;
        V v = null;
        try {
            v = (V) o;
        } catch (Exception ignored) {
        }
        return search(v) != null;
    }

    @Override
    public Iterator<V> iterator() {
        return new SplayTreeIterator(this, root);
    }

    public void rotateLeft(SplayNode c, SplayNode p) {
        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p)) throw new IllegalArgumentException();
        if (p.parent != null) {
            if (p == p.parent.left) p.parent.left = c;
            else p.parent.right = c;
        }
        if (c.right != null) c.right.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }

    public void rotateRight(SplayNode c, SplayNode p) {
        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p)) throw new IllegalArgumentException();
        if (p.parent != null) {
            if (p == p.parent.left) p.parent.left = c;
            else p.parent.right = c;
        }
        if (c.left != null) c.left.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    private void splay(SplayNode<V> s) {
        while (s.parent != null) {
            SplayNode parent = s.parent;
            SplayNode grandParent = parent.parent;
            if (grandParent == null) {
                if (s == parent.left) rotateLeft(s, parent);
                else rotateRight(s, parent);
            } else {
                if (s == parent.left) {
                    if (parent == grandParent.left) {
                        rotateLeft(parent, grandParent);
                        rotateLeft(s, parent);
                    } else {
                        rotateLeft(s, s.parent);
                        rotateRight(s, s.parent);
                    }
                } else {
                    if (parent == grandParent.left) {
                        rotateRight(s, s.parent);
                        rotateLeft(s, s.parent);
                    } else {
                        rotateRight(parent, grandParent);
                        rotateRight(s, parent);
                    }
                }
            }
        }
        root = s;
    }

    @Override
    public boolean add(V v) {
        if (v == null) return false;
        SplayNode a = root;
        SplayNode p = null;
        while (a != null) {
            p = a;
            a = v.compareTo((V) p.value) > 0 ? a.right : a.left;
        }
        a = new SplayNode();
        a.value = v;
        a.parent = p;
        if (p == null) root = a;
        else if (v.compareTo((V) p.value) > 0) p.right = a;
        else p.left = a;
        splay(a);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) return false;
        V v = null;
        try {
            v = (V) o;
        } catch (Exception ignored) {
        }
        SplayNode node = search(v);
        if (node == null) return false;
        splay(node);
        if ((node.left != null) && (node.right != null)) {
            SplayNode min = node.left;
            while (min.right != null) min = min.right;
            min.right = node.right;
            node.right.parent = min;
            node.left.parent = null;
            root = node.left;
        } else if (node.right != null) {
            node.right.parent = null;
            root = node.right;
        } else if (node.left != null) {
            node.left.parent = null;
            root = node.left;
        } else {
            root = null;
        }
        node.parent = null;
        node.left = null;
        node.right = null;
        size--;
        return true;
    }

    private SplayNode search(V v) {
        if (v == null) return null;
        SplayNode p = null;
        SplayNode s = root;
        while (s != null) {
            p = s;
            if (v.compareTo((V) s.value) > 0) s = s.right;
            else if (v.compareTo((V) s.value) < 0) s = s.left;
            else if (v.compareTo((V) s.value) == 0) {
                splay(s);
                return s;
            }
        }
        if (p != null) {
            splay(p);
            return null;
        }
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public void print() {
        print(root);
    }

    private void print(SplayNode node) {
        if (node != null) {
            print(node.left);
            System.out.print(node.value + " ");
            print(node.right);
        }
    }
}