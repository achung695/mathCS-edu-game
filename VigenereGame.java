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
  Creates Vigenere cipher game. Most code is repeated from the Caesar cipher.
 */
public class VigenereGame extends Game {
    private Pane _root;
    private Text _cipherText;
    private String _plainText;
    private Text _keyText;
    private Text _keyText2;
    private Text _keyText3;
    private TextField _translated;
    private ArrayList<String> _array;
    private ArrayList<String> _array2;
    private ArrayList<String> _array3;
    private ArrayList<String> _wordArray;
    private ArrayList<String> _keyArray;
    private Text _correct;
    private Text _hint;

    public VigenereGame(Pane root) {
        _root = root;
        this.createBackground();
        this.setupWordArray();
        _correct = this.createCorrectLabel(root);
        this.createTranslateText();
        this.createAlphabet();

        _array = new ArrayList<String>();
        _keyText = new Text();
        _array2 = new ArrayList<String>();
        _keyText2 = new Text();
        _array3 = new ArrayList<String>();
        _keyText3 = new Text();
        this.createSliderAlphabet(_array, _keyText, 0);
        this.createSliderAlphabet(_array2, _keyText2, 40);
        this.createSliderAlphabet(_array3, _keyText3, 80);

        this.createArrows(0);
        this.createArrows(40);
        this.createArrows(80);
        this.createEnterText();
        this.createButtons();
        this.resetCypher();
        this.createHelpButton();
    }

    //Creates array of words to choose from for plaintext, for key, and creates hint layout
    private void setupWordArray() {
        List<String> wordList = Arrays.asList("HELLO", "GOODBYE", "SECRET", "MESSAGE", "DECODE", "VIGENERE",
                "CIPHER", "ENCODE", "COMPUTER", "SCIENCE", "LEARN", "ALPHABET",
                "TRANSLATE", "ENCRYPT");
        _wordArray = new ArrayList<String>();
        _wordArray.addAll(wordList);
        List<String> wordList1 = Arrays.asList("DOG", "CAT", "RAT", "CAR", "BEE", "SEA", "SUN", "ANT", "FOX", "OWL",
                "ICE", "HAT", "RED", "FUN", "TEN");
        _keyArray = new ArrayList<String>();
        _keyArray.addAll(wordList1);

        _hint = new Text();
        _hint.setFont(new Font("Lucida Sans Typewriter Bold", 20));
        _hint.setLayoutX(670);
        _hint.setLayoutY(270);
        _hint.setStroke(Color.WHITE);
        _hint.setFill(Color.WHITE);
        _root.getChildren().add(_hint);
    }

    //Creates background
    private void createBackground() {
        ImageView view = new ImageView(new Image(this.getClass().getResourceAsStream("VigenereBackground.jpg")));
        view.setFitHeight(800);
        view.setFitWidth(800);
        Rectangle rectangle = new Rectangle(0, 0, 800, 800);
        rectangle.setFill(new Color(0, 0, 0, .5));
        Text title = new Text("Vigenère Cipher");
        title.setFont(new Font("Lucida Sans Typewriter Bold", 40));
        title.setStroke(Color.WHITE);
        title.setFill(Color.WHITE);
        title.setLayoutX(270);
        title.setLayoutY(120);
        _root.getChildren().addAll(view, rectangle, title);
    }

    //Sets layout for cipher text
    private void createTranslateText() {
        Rectangle rectangle = new Rectangle(200, 150, 400, 100);
        rectangle.setFill(Color.WHITE);
        _cipherText = new Text();
        _cipherText.setFont(new Font("Lucida Sans Typewriter Bold", 60));
        _cipherText.setLayoutX(220);
        _cipherText.setLayoutY(220);
        _root.getChildren().addAll(rectangle, _cipherText);
    }

    //Creates alphabet that does not move
    private void createAlphabet() {
        Text text = new Text("A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z");
        text.setStroke(Color.WHITE);
        text.setFill(Color.WHITE);
        text.setFont(new Font("Lucida Sans Typewriter Bold", 20));
        text.setLayoutX(20);
        text.setLayoutY(350);
        _root.getChildren().add(text);
    }

    //Creates alphabet that moves when user clicks arrows
    private void createSliderAlphabet(ArrayList<String> array, Text text, int x) {
        array.add("A  ");
        array.add("B  ");
        array.add("C  ");
        array.add("D  ");
        array.add("E  ");
        array.add("F  ");
        array.add("G  ");
        array.add("H  ");
        array.add("I  ");
        array.add("J  ");
        array.add("K  ");
        array.add("L  ");
        array.add("M  ");
        array.add("N  ");
        array.add("O  ");
        array.add("P  ");
        array.add("Q  ");
        array.add("R  ");
        array.add("S  ");
        array.add("T  ");
        array.add("U  ");
        array.add("V  ");
        array.add("W  ");
        array.add("X  ");
        array.add("Y  ");
        array.add("Z  ");
        String string = "";
        for (int i = 0; i < 26; i++) {
            string = string + _array.get(i);
        }
        text.setText(string);
        text.setFont(new Font("Lucida Sans Typewriter Bold", 20));
        text.setStroke(Color.WHITE);
        text.setFill(Color.WHITE);
        text.setLayoutX(20);
        text.setLayoutY(400+x);
        _root.getChildren().add(text);
    }

    //Creates arrows to move alphabet
    private void createArrows(int x) {
        Polygon leftTriangle = new Polygon(0, 0, 20, -10, 20, 10);
        leftTriangle.setLayoutX(680);
        leftTriangle.setLayoutY(395+x);
        leftTriangle.setFill(Color.WHITE);
        Polygon rightTriangle = new Polygon(0, 0, -20, -10, -20, 10);
        rightTriangle.setLayoutX(750);
        rightTriangle.setLayoutY(395+x);
        rightTriangle.setFill(Color.WHITE);
        switch(x) {
            case 0:
                leftTriangle.setOnMouseClicked(new LeftShiftHandler());
                rightTriangle.setOnMouseClicked(new RightShiftHandler());
                break;
            case 40:
                leftTriangle.setOnMouseClicked(new LeftShiftHandler2());
                rightTriangle.setOnMouseClicked(new RightShiftHandler2());
                break;
            case 80:
                leftTriangle.setOnMouseClicked(new LeftShiftHandler3());
                rightTriangle.setOnMouseClicked(new RightShiftHandler3());
                break;
        }

        _root.getChildren().addAll(leftTriangle, rightTriangle);
    }

    //Move the alphabet when arrows are clicked
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
    private class LeftShiftHandler2 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            String string = _array2.get(0);
            _array2.remove(0);
            _array2.add(string);
            String s = "";
            for (int i = 0; i < 26; i++) {
                s = s + _array2.get(i);
            }
            _keyText2.setText(s);
        }
    }
    private class LeftShiftHandler3 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            String string = _array3.get(0);
            _array3.remove(0);
            _array3.add(string);
            String s = "";
            for (int i = 0; i < 26; i++) {
                s = s + _array3.get(i);
            }
            _keyText3.setText(s);
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
    private class RightShiftHandler2 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            String string = _array2.get(25);
            _array2.remove(25);
            _array2.add(0, string);
            String s = "";
            for (int i = 0; i < 26; i++) {
                s = s + _array2.get(i);
            }
            _keyText2.setText(s);
        }
    }
    private class RightShiftHandler3 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            String string = _array3.get(25);
            _array3.remove(25);
            _array3.add(0, string);
            String s = "";
            for (int i = 0; i < 26; i++) {
                s = s + _array3.get(i);
            }
            _keyText3.setText(s);
        }
    }

    //Creates textfield for translation to be entered
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
        Button helpButton = this.createHelpButton("The Vigenère cipher is another type of substitution cipher. " +
                "Specifically, it's a repeated Caesar cipher! If two people were communicating with a Vigenère cipher, " +
                "Person A would use a special word, or key to encode their message . For example, if the key were CAT, Person A" +
                " would use a Caesar cipher that lined up A with C to encode the first letter of the message, a Caesar" +
                " cipher that lined up A with A to encode the second letter of the message, a Caesar cipher that lined" +
                " up A with T to encode the third letter of the message, a Caesar cipher that lined up A with C to encode " +
                " the fourth letter, and so on. Person B could decrypt the coded message using these same ciphers if they knew" +
                " the key Person A used.");
        _root.getChildren().add(helpButton);
    }

    //Generates new cipher text with new plain text/key
    private void resetCypher() {
        int keyInt = (int) (Math.random()*15);
        String key = _keyArray.get(keyInt);
        int word = (int) (Math.random()*_wordArray.size());
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        _plainText = _wordArray.get(word);
        String text = "";
        for (int i = 0; i < _plainText.length(); i++) {
            int alphaIndex = alphabet.indexOf(_plainText.charAt(i));
            int shift = alphabet.indexOf(key.charAt(i%3));
            alphaIndex += shift;
            alphaIndex = alphaIndex % 26;
            text = text + alphabet.charAt(alphaIndex);
        }
        _cipherText.setText(text);
        _hint.setText("Hint: " + key);
        _translated.setText("");
        _correct.setVisible(false);
        System.out.println(_plainText + " into " + _cipherText.getText());
    }

    //Checks if answer is right
    private class SubmitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (_translated.getText().toUpperCase().contains(_plainText)) {
                _correct.setVisible(true);
            }
        }
    }
    //New question
    private class ContinueHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            VigenereGame.this.resetCypher();
        }
    }
}