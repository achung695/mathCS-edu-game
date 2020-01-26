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

/*
 Superclass for all games; contains methods for returning to main menu, going to next question,
 creating help section, and creating correct label.
 */

public class Game {
    private Pane _root;
    private String _help;
    private Text _helpText;
    private Rectangle _helpRectangle;
    private Button _continueButton;
    private Rectangle _helpBackground;

    //Creates button to return to main menu
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

    //Moves to next question ONLY once given event handler by subclass of Game
    public Button createResetButton() {
        Button button = new Button("Next question!");
        button.setLayoutY(10);
        button.setLayoutX(680);
        return button;
    }

    //Opens help window that contains string used as argument
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

    //Creates correct label
    public Text createCorrectLabel(Pane root) {
        Text correct = new Text("That's correct!");
        correct.setStroke(Color.WHITE);
        correct.setFill(Color.WHITE);
        correct.setLayoutX(350);
        correct.setLayoutY(750);
        correct.setFont(new Font("Lucida Sans Typewriter Bold", 20));
        root.getChildren().add(correct);
        correct.setVisible(false);
        return correct;
    }
}