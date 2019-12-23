public class Node<T> {

    final T value;
    Node<T> left;
    Node<T> right;

    Node(T value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    Node(T value, Node<T> left){
        this.value = value;
        this.left = left;
        this.right = null;
    }

    Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public boolean equals(Node<T> other) {
        return this.value == other.value;
    }
}