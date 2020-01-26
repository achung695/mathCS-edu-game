package EduGame;

import javafx.scene.layout.Pane;

public class PaneOrganizer {
    private Pane _root;

    public PaneOrganizer() {
        _root = new Pane();
        Menu menu = new Menu(_root);
    }

    public Pane getRoot() {
        return _root;
    }

}