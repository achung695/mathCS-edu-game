package EduGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ModGame extends Game {
    private Pane _root;
    private int _a;
    private int _b;
    private TextField _answer;
    private Text _correct;
    private Text _equation;

    public ModGame(Pane root) {
        _root = root;
        this.createBackground();

        _correct = new Text("That's correct!");
        _correct.setStroke(Color.WHITE);
        _correct.setFill(Color.WHITE);
        _correct.setLayoutX(200);
        _correct.setLayoutY(750);
        _correct.setFont(new Font("Lucida Sans Typewriter Bold", 20));
        _root.getChildren().add(_correct);
        _correct.setVisible(false);

        this.createEnterText();
        this.createEquation();
        this.createButtons();
        this.resetEquation();
        this.createHelpButton();
    }

    private void createBackground() {
        ImageView view = new ImageView(new Image(this.getClass().getResourceAsStream("ModBackground.jpg")));
        view.setFitHeight(800);
        view.setFitWidth(800);
        Rectangle rectangle = new Rectangle(0, 0, 800, 800);
        rectangle.setFill(new Color(0, 0, 0, .7));
        Text title = new Text("Modulo Solver");
        title.setFont(new Font("Lucida Sans Typewriter Bold", 40));
        title.setStroke(Color.WHITE);
        title.setFill(Color.WHITE);
        title.setLayoutX(260);
        title.setLayoutY(170);
        _root.getChildren().addAll(view, rectangle, title);
    }

    private void createEquation() {
        Rectangle rectangle = new Rectangle(200, 270, 400, 100);
        rectangle.setFill(Color.WHITE);
        _equation = new Text("");
        _equation.setFont(new Font("Lucida Sans Typewriter Bold", 60));
        _equation.setLayoutX(270);
        _equation.setLayoutY(340);
        _root.getChildren().addAll(rectangle, _equation);

    }

    private void resetEquation() { _answer.setText("");
        _a = (int) (Math.random()*100);
        _b = (int) (Math.random()*10) + 1;
        _equation.setText(_a + "mod" + _b);
        _answer.setText("");
        _correct.setVisible(false);
    }

    private void createEnterText() {
        _answer = new TextField();
        _answer.setPrefHeight(100);
        _answer.setPrefWidth(400);
        _answer.setLayoutX(200);
        _answer.setLayoutY(450);
        _answer.setFont(new Font("Lucida Sans Typewriter Bold", 60));
        _root.getChildren().add(_answer);
    }

    private void createButtons() {
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(350);
        submitButton.setLayoutY(600);
        submitButton.setPrefWidth(100);
        submitButton.setOnAction(new SubmitHandler());
        this.createQuitButton(_root);
        Button continueButton = this.createResetButton();
        continueButton.setOnAction(new ContinueHandler());
        _root.getChildren().addAll(submitButton, continueButton);
    }

    private void createHelpButton() {
        Button helpButton = this.createHelpButton("You've probably ended up with remainders doing long division before," +
                " right? Well that's where modulos come in! A modulo operator solves for the remainder after division takes place." +
                " For example, when we divide 4 by 3, 3 goes in 1 time fully, and there's 1 remaining. So 4mod3 is 1, 5mod3 is 2," +
                " 6mod3 is 0, and so on!");
        _root.getChildren().add(helpButton);
    }

    private class SubmitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (_answer.getText().contains(String.valueOf(_a % _b))) {
                _correct.setVisible(true);
            }
        }
    }

    private class ContinueHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            ModGame.this.resetEquation();
        }
    }
}
