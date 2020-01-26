package EduGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
  Height and width of window are fixed because games are optimized for 800x800 window
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot());
        stage.setHeight(800);
        stage.setWidth(800);
        stage.setMaxHeight(800);
        stage.setMaxWidth(800);
        stage.setMinHeight(800);
        stage.setMinWidth(800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
