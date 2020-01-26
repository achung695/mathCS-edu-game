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

/*
  Creates the decimal-to-binary conversion game
 */

public class BinaryGame extends Game {
    private Pane _root;
    private int _decimal;
    private Text _decimalText;
    private TextField _binary;
    private Text _correct;

    public BinaryGame(Pane root) {
        _root = root;
        this.createBackground();
        _correct = this.createCorrectLabel(root);
        this.createTranslateText();
        this.createEnterText();
        this.createButtons();
        this.resetDecimal();
        this.createHelpButton();
    }

    //Creates background, title
    private void createBackground() {
        ImageView view = new ImageView(new Image(this.getClass().getResourceAsStream("BinaryBackground.jpg")));
        view.setFitHeight(800);
        view.setFitWidth(800);
        Rectangle rectangle = new Rectangle(0, 0, 800, 800);
        rectangle.setFill(new Color(0, 0, 0, .7));
        Text title = new Text("Decimal-to-Binary Convertor");
        title.setFont(new Font("Lucida Sans Typewriter Bold", 40));
        title.setStroke(Color.WHITE);
        title.setFill(Color.WHITE);
        title.setLayoutX(130);
        title.setLayoutY(200);
        _root.getChildren().addAll(view, rectangle, title);
    }

    //Creates layout for text to be translated
    private void createTranslateText() {
        Rectangle rectangle = new Rectangle(200, 270, 400, 100);
        rectangle.setFill(Color.WHITE);
        _decimalText = new Text();
        _decimalText.setFont(new Font("Lucida Sans Typewriter Bold", 60));
        _decimalText.setLayoutX(370);
        _decimalText.setLayoutY(340);
        _root.getChildren().addAll(rectangle, _decimalText);
    }

    //Creates textfield to enter translation
    private void createEnterText() {
        _binary = new TextField();
        _binary.setPrefHeight(100);
        _binary.setPrefWidth(400);
        _binary.setLayoutX(200);
        _binary.setLayoutY(450);
        _binary.setFont(new Font("Lucida Sans Typewriter Bold", 60));
        _root.getChildren().add(_binary);
    }

    //Creates buttons
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

    //Generates new problem
    private void resetDecimal() {
        _decimal = (int) (Math.random()*33);
        _decimalText.setText(Integer.toString(_decimal));
        _binary.setText("");
        _correct.setVisible(false);
    }

    //Creates help button
    private void createHelpButton() {
        Button helpButton = this.createHelpButton("In a normal decimal system, there are 10 digits—0 through 9. In" +
                " binary, there are only two—0 and 1! Each place value in binary represents a power of two. The first place" +
                " is two to the zero power, the second place is two to the first power, and so on. So to convert a number from" +
                " decimal form to binary, you need to add powers of two! For example, 1 in binary is 1, because it's two" +
                " to the zero power. 2 in binary is 10, because it's two to the first power. 3 in binary is 11, because it's " +
                " two to the first power PLUS two to the zero power.");
        _root.getChildren().add(helpButton);
    }

    //Handles answers submission
    private class SubmitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (_binary.getText().contains(Integer.toBinaryString(_decimal))) {
                _correct.setVisible(true);
            }
        }
    }

    //Generates new question when user clicks continue
    private class ContinueHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            BinaryGame.this.resetDecimal();
        }
    }
}