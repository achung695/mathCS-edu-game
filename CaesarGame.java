package EduGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*
  Creates the Caesar cipher game
 */
public class CaesarGame extends Game {
    private Pane _root;
    private Text _cipherText;
    private String _plainText;
    private Text _keyText;
    private TextField _translated;
    private ArrayList<String> _array;
    private ArrayList<String> _wordArray;
    private Text _correct;
    private Text _hint;

    public CaesarGame(Pane root) {
        _root = root;
        this.createBackground();
        this.setupWordArray();
        _correct = this.createCorrectLabel(root);
        this.createTranslateText();
        this.createAlphabet();
        this.createSliderAlphabet();
        this.createArrows();
        this.createEnterText();
        this.createButtons();
        this.resetCypher();
        this.createHelpButton();
    }

    //Creates word array plain text is drawn from, layout for hint
    private void setupWordArray() {
        List<String> wordList = Arrays.asList("HELLO", "GOODBYE", "SECRET", "MESSAGE", "DECODE", "CAESAR",
                "CIPHER", "ENCODE", "COMPUTER", "SCIENCE", "LEARN", "ALPHABET",
                "TRANSLATE", "ENCRYPT");
        _wordArray = new ArrayList<String>();
        _wordArray.addAll(wordList);
        _hint = new Text();
        _hint.setFont(new Font("Lucida Sans Typewriter Bold", 20));
        _hint.setLayoutX(670);
        _hint.setLayoutY(270);
        _hint.setStroke(Color.WHITE);
        _hint.setFill(Color.WHITE);
        _root.getChildren().add(_hint);
    }

    //Creates image background, title
    private void createBackground() {
        ImageView view = new ImageView(new Image(this.getClass().getResourceAsStream("CipherBackground.jpg")));
        view.setFitHeight(800);
        view.setFitWidth(800);
        Rectangle rectangle = new Rectangle(0, 0, 800, 800);
        rectangle.setFill(new Color(0, 0, 0, .5));
        Text title = new Text("Caesar Cipher");
        title.setFont(new Font("Lucida Sans Typewriter Bold", 40));
        title.setStroke(Color.WHITE);
        title.setFill(Color.WHITE);
        title.setLayoutX(270);
        title.setLayoutY(120);
        _root.getChildren().addAll(view, rectangle, title);
    }

    //Creates layout for text to be translated
    private void createTranslateText() {
        Rectangle rectangle = new Rectangle(200, 150, 400, 100);
        rectangle.setFill(Color.WHITE);
        _cipherText = new Text();
        _cipherText.setFont(new Font("Lucida Sans Typewriter Bold", 60));
        _cipherText.setLayoutX(220);
        _cipherText.setLayoutY(220);
        _root.getChildren().addAll(rectangle, _cipherText);
    }

    //Creates alphabet that doesn't move
    private void createAlphabet() {
        Text text = new Text("A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z");
        text.setStroke(Color.WHITE);
        text.setFill(Color.WHITE);
        text.setFont(new Font("Lucida Sans Typewriter Bold", 24));
        text.setLayoutX(20);
        text.setLayoutY(350);
        _root.getChildren().add(text);
    }

    //Creates alphabet that moves when user clicks arrows
    private void createSliderAlphabet() {
        _array = new ArrayList<String>();
        _array.add("A  ");
        _array.add("B  ");
        _array.add("C  ");
        _array.add("D  ");
        _array.add("E  ");
        _array.add("F  ");
        _array.add("G  ");
        _array.add("H  ");
        _array.add("I  ");
        _array.add("J  ");
        _array.add("K  ");
        _array.add("L  ");
        _array.add("M  ");
        _array.add("N  ");
        _array.add("O  ");
        _array.add("P  ");
        _array.add("Q  ");
        _array.add("R  ");
        _array.add("S  ");
        _array.add("T  ");
        _array.add("U  ");
        _array.add("V  ");
        _array.add("W  ");
        _array.add("X  ");
        _array.add("Y  ");
        _array.add("Z  ");
        String string = "";
        for (int i = 0; i < 26; i++) {
            string = string + _array.get(i);
        }
        _keyText = new Text(string);
        _keyText.setFont(new Font("Lucida Sans Typewriter Bold", 24));
        _keyText.setStroke(Color.WHITE);
        _keyText.setFill(Color.WHITE);
        _keyText.setLayoutX(20);
        _keyText.setLayoutY(400);
        _root.getChildren().add(_keyText);
    }

    //Creates arrows to move slider alphabet
    private void createArrows() {
        Polygon leftTriangle = new Polygon(0, 0, 30, -20, 30, 20);
        leftTriangle.setLayoutX(300);
        leftTriangle.setLayoutY(500);
        leftTriangle.setFill(Color.WHITE);
        leftTriangle.setOnMouseClicked(new LeftShiftHandler());
        Polygon rightTriangle = new Polygon(0, 0, -30, -20, -30, 20);
        rightTriangle.setLayoutX(450);
        rightTriangle.setLayoutY(500);
        rightTriangle.setFill(Color.WHITE);
        rightTriangle.setOnMouseClicked(new RightShiftHandler());
        _root.getChildren().addAll(leftTriangle, rightTriangle);
    }

    //Controls slider alphabet movement
    private class LeftShiftHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            String string = _array.get(0);
            _array.remove(0);
            _array.add(string);
            String s = "";
            for (int i = 0; i < 26; i++) {
                s = s + _array.get(i);
            }
            _keyText.setText(s);
        }
    }
    private class RightShiftHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            String string = _array.get(25);
            _array.remove(25);
            _array.add(0, string);
            String s = "";
            for (int i = 0; i < 26; i++) {
                s = s + _array.get(i);
            }
            _keyText.setText(s);
        }
    }

    //Creates text field to enter translated text
    private void createEnterText() {
        _translated = new TextField();
        _translated.setPrefHeight(100);
        _translated.setPrefWidth(400);
        _translated.setLayoutX(200);
        _translated.setLayoutY(550);
        _translated.setFont(new Font("Lucida Sans Typewriter Bold", 60));
        _root.getChildren().add(_translated);
    }

    //Creates buttons
    private void createButtons() {
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(350);
        submitButton.setLayoutY(700);
        submitButton.setPrefWidth(100);
        submitButton.setOnAction(new SubmitHandler());
        this.createQuitButton(_root);
        Button continueButton = this.createResetButton();
        continueButton.setOnAction(new ContinueHandler());

        _root.getChildren().addAll(submitButton, continueButton);
    }

    //Creates help button
    private void createHelpButton() {
        Button helpButton = this.createHelpButton("A Caesar cipher is a type of simple substition cipher, in" +
                " which each plain text letter is replaced by another cipher letter. In the Caesar cipher, a letter" +
                " is shifted a certain number of places to create the cipher text from the plain text. For example, a single" +
                " shift would result in the letter A being replaced with Z, the letter B being replaced with A, and so on." +
                " The hint at the top of the screen tells you how many shifts from the standard alphabet each message was encoded with.");
        _root.getChildren().add(helpButton);
    }

    //Generates new plain text with new number of shifts
    private void resetCypher() {
        int shift = (int) (Math.random()*25+1);
        int word = (int) (Math.random()*_wordArray.size());
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        _plainText = _wordArray.get(word);
        String text = "";
        for (int i = 0; i < _plainText.length(); i++) {
            int alphaIndex = alphabet.indexOf(_plainText.charAt(i));
            alphaIndex += shift;
            alphaIndex = alphaIndex % 26;
            text = text + alphabet.charAt(alphaIndex);
        }
        _cipherText.setText(text);
        _hint.setText("Hint: " + shift);
        _translated.setText("");
        _correct.setVisible(false);
    }

    //Checks if answer is correct
    private class SubmitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (_translated.getText().toUpperCase().contains(_plainText)) {
                _correct.setVisible(true);
            }
        }
    }

    //Generates new question
    private class ContinueHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            CaesarGame.this.resetCypher();
        }
    }
}