package EduGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*
  Creates menu to select game
 */
public class Menu {
    private Pane _root;
    private Pane _cipherPane;
    public Menu(Pane root) {
        _root = root;
        _cipherPane = new Pane();
        this.createBackground();
        this.createButtons();
        this.createText();
    }

    //Creates title and acknowledgments
    private void createText() {
        Text text1 = new Text("Secret Agent Starter Kit");
        text1.setFont(new Font("Lucida Sans Typewriter Bold", 40));
        text1.setLayoutY(50);
        text1.setLayoutX(190);
        text1.setStroke(Color.WHITE);
        Text text = new Text("Created by Ashley Chung and Brynn Chernosky");
        text.setFont(new Font("Lucida Sans Typewriter Bold", 15));
        text.setLayoutX(260);
        text.setLayoutY(770);
        text.setStroke(Color.WHITE);
        _root.getChildren().addAll(text1, text);
    }

    //Creates buttons for games
    private void createButtons() {
        Button cipherButton = new Button("Caesar Cipher");
        cipherButton.setOnAction(new CipherHandler());
        cipherButton.setLayoutY(200);
        cipherButton.setLayoutX(500);

        Button binaryButton = new Button("Decimal-to-Binary");
        binaryButton.setOnAction(new BinaryHandler());
        binaryButton.setLayoutY(200);
        binaryButton.setLayoutX(50);

        Button modButton = new Button("Modulos");
        modButton.setOnAction(new ModHandler());
        modButton.setLayoutY(600);
        modButton.setLayoutX(330);

        Button vigenereButton = new Button("Vigenère Cipher");
        vigenereButton.setOnAction(new VigenereHandler());
        vigenereButton.setLayoutX(0);
        vigenereButton.setLayoutY(500);

        Button mathButton = new Button("Speed Math");
        mathButton.setOnAction(new MathHandler());
        mathButton.setLayoutX(545);
        mathButton.setLayoutY(500);

        this.buttonPresets(cipherButton);
        this.buttonPresets(vigenereButton);
        this.buttonPresets(binaryButton);
        this.buttonPresets(modButton);
        this.buttonPresets(mathButton);
        _root.getChildren().addAll(cipherButton, vigenereButton, binaryButton, modButton, mathButton);
    }

    //Sets button style
    private void buttonPresets(Button button) {
        button.setFont(new Font("Lucida Sans Typewriter Bold", 30));
        button.setTextFill(Color.WHITE);
        button.setBackground(null);
    }

    //Creates game
    private class MathHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            _root.getChildren().clear();
            GeometryMinigame game = new GeometryMinigame(_root);
        }
    }
    private class CipherHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            _root.getChildren().clear();
            CaesarGame cipher = new CaesarGame(_root);
        }
    }
    private class BinaryHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            _root.getChildren().clear();
            BinaryGame binary = new BinaryGame(_root);
        }
    }
    private class ModHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            _root.getChildren().clear();
            ModGame mod = new ModGame(_root);
        }
    }
    private class VigenereHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            _root.getChildren().clear();
            VigenereGame vigenere = new VigenereGame(_root);
        }
    }

    //Creates image background
    private void createBackground() {
        ImageView view = new ImageView(new Image(this.getClass().getResourceAsStream("CoverArt.png")));
        view.setFitHeight(700);
        view.setFitWidth(800);
        view.setLayoutY(75);
        Rectangle titleBar = new Rectangle(0,0,800,80);
        titleBar.setFill(Color.BLACK);
        Rectangle creditBar = new Rectangle(0, 750, 800, 50);
        creditBar.setFill(Color.BLACK);
        Rectangle rectangle = new Rectangle(0, 0, 800, 800);
        rectangle.setFill(new Color(0, 0, 0, .5));
        _root.getChildren().addAll(view, titleBar, creditBar, rectangle);
    }
}
