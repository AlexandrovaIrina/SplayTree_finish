import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.print();
        System.out.println("");

        tree.add(1);
        tree.print();
        System.out.println("");

        tree.add(2);
        tree.print();
        System.out.println("");

        tree.add(3);
        tree.print();
        System.out.println("");

        tree.add(4);
        tree.print();
        System.out.println("");

        tree.addAll(Arrays.asList(6, 7, 8, 9));
        tree.print();
        System.out.println("");

        tree.remove(0);
        tree.print();
        System.out.println("");

        tree.remove(2);
        tree.print();
        System.out.println("");

        tree.remove(1);
        tree.print();
        System.out.println("");

        tree.removeAll(Arrays.asList(6, 7, 8, 9));
        tree.print();
        System.out.println("");

        System.out.println(tree.contains(3));
        System.out.println(tree.contains(5));
    }
}
