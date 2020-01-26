package EduGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game {
    private Pane _root;
    private String _help;
    private Text _helpText;
    private Rectangle _helpRectangle;
    private Button _continueButton;
    private Rectangle _helpBackground;

    public void createQuitButton(Pane root) {
        _root = root;
        Button button = new Button("Return to menu");
        button.setOnAction(new QuitHandler());
        button.setLayoutY(10);
        button.setLayoutX(10);
        _root.getChildren().add(button);
    }

    private class QuitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            _root.getChildren().clear();
            Menu menu = new Menu(_root);
        }
    }
    public Button createResetButton() {
        Button button = new Button("Next question!");
        button.setLayoutY(10);
        button.setLayoutX(680);
        return button;
    }

    public Button createHelpButton(String string) {
        _help = string;
        Button button = new Button("?");
        button.setShape(new Circle(25));
        button.setLayoutX(10);
        button.setLayoutY(750);
        button.setOnAction(new HelpHandler());

        _helpBackground = new Rectangle(0, 0, 800, 800);
        _helpBackground.setFill(Color.BLACK);
        _helpRectangle = new Rectangle(150,150, 500, 500);
        _helpRectangle.setFill(Color.WHITE);
        _helpText = new Text(_help);
        _helpText.setFont(new Font("Lucida Sans Typewriter Bold", 20));
        _helpText.setLayoutY(220);
        _helpText.setLayoutX(180);
        _helpText.setWrappingWidth(440);
        _continueButton = new Button("Continue");
        _continueButton.setLayoutX(370);
        _continueButton.setLayoutY(700);
        _continueButton.setOnAction(new ContinueHandler());
        _root.getChildren().addAll(_helpBackground, _helpRectangle, _helpText, _continueButton);
        _helpRectangle.setVisible(false);
        _helpText.setVisible(false);
        _continueButton.setVisible(false);
        _helpBackground.setVisible(false);

        return button;
    }

    private class HelpHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            _helpRectangle.setVisible(true);
            _helpText.setVisible(true);
            _continueButton.setVisible(true);
            _helpBackground.setVisible(true);
        }
    }

    private class ContinueHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            _helpRectangle.setVisible(false);
            _helpText.setVisible(false);
            _continueButton.setVisible(false);
            _helpBackground.setVisible(false);
        }
    }
}