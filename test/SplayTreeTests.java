import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Test;

import java.util.Set;

import static junit.framework.Assert.assertEquals;

public class SplayTreeTests extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    private SplayTree<Integer> generateSplayTree(Set<Integer> values) {
        SplayTree<Integer> tree = new SplayTree<Integer>();
        tree.addAll(values);
        return tree;
    }

    @Test
    public void addTest() {
        SplayTree<Integer> splayTree = generateSplayTree(Set.of(1, 2, 3, 4));
        splayTree.add(5);
        assertEquals(5, splayTree.size());
    }

    @Test
    public void removeTest() {

    }

    @Test
    public void splayTest() {

    }
}
