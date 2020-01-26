package EduGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Menu {
    private Pane _root;
    private Pane _cipherPane;
    public Menu(Pane root) {
        _root = root;
        _cipherPane = new Pane();
        this.createBackground();
        this.createButtons();
        Text text1 = new Text("Secret Agent Starter Kit");
        text1.setLayoutY(200);
        Text text = new Text("Created by Ashley Chung and Brynn Chernosky");
        text.setLayoutX(200);
        text.setLayoutY(400);
        _root.getChildren().addAll(text1, text);
    }

    private void createButtons() {
        VBox vbox = new VBox();
        Button cipherButton = new Button("Caesar Cipher");
        cipherButton.setOnAction(new CipherHandler());
        Button binaryButton = new Button("Decimal-to-Binary Conversion");
        binaryButton.setOnAction(new BinaryHandler());
        Button modButton = new Button("Modulos");
        modButton.setOnAction(new ModHandler());
        Button vigenereButton = new Button("Vigenère Cipher");
        vigenereButton.setOnAction(new VigenereHandler());
        vbox.getChildren().addAll(cipherButton, vigenereButton, binaryButton, modButton);
        _root.getChildren().add(vbox);
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

    private void createBackground() {

    }
}
