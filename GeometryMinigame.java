package EduGame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/*
 * SPEED MATH!!
 * This is the GeometryMinigame class, which contains all game logic of
 * the mathematics-oriented question-answering speed mini game
 * contained in our educational game project.
 * Contains score label, objects, buttons that the user can press
 * to answer the true-false questions, and 'remaining lives' value that
 * the user can reference. You can always restart the game
 * after the game is over. (I achieve this functionality by graphically
 * removing all content from the game pane, then logically resetting all important
 * values of the game (like 'remaining lives' value).
 */
public class GeometryMinigame {
    private Shape _shape;
    private Label _scoreLabel;
    private Pane _geoPane;
    private int _lives; // 3 lives, if all lives are lost, game over
    private boolean _isGameOver;
    private Label _gameOverLabel;
    private ImageView _bground; // ImageView for background image (PNG file).

    private Rectangle _true; // true and false rectangular nodes
    private Rectangle _false; // that user can move objects to. (true or false q)

    private Timeline _geoTimeline;

    private Button _trueButton; // true option
    private Button _falseButton; // false option
    private Button _restartButton; // restart button (appears with game over screen)

    public GeometryMinigame(Pane pane) { // constructor (that takes in a pane)
        _shape = new Shape(); // instantiation (s)
        _gameOverLabel = new Label(" ");
        _geoPane = pane;
        this.setUpBackground(pane);
        _trueButton = new Button("TRUE"); // instantiating user-pressed buttons
        _falseButton = new Button ("FALSE");
        _restartButton = new Button("RESTART"); // instantiating restart button (not added to gamepane graphically yet)

        _lives = 3; // initial lives = 3
        _isGameOver = false; // initial game over state: false
        this.setUpRects(); // setting up shapes that are containers (user's goal)
        this.setUpLabel(); // setting up label
        pane.getChildren().addAll(_bground, _scoreLabel, _true,
        _false, _trueButton, _falseButton); // add to pane
        pane.getChildren().add(_shape.getShape());
        this.setUpTimeline();
        pane.getChildren().addAll(_shape.get_randomQuestion()); // add all the question labels.
        this.setUpButtons();
    }

    /*
     * helper method for setting up button
     */
    private void setUpButtons() {
        _trueButton.setFocusTraversable(true);
        _falseButton.setFocusTraversable(true);
        _restartButton.setFocusTraversable(true);
        _trueButton.setLayoutX(_true.getX() + 50);
        _trueButton.setLayoutY(_true.getY() + 50);
        _restartButton.setLayoutX(440);
        _restartButton.setLayoutY(490);
        _falseButton.setLayoutX(_false.getX() + 50);
        _falseButton.setLayoutY(_false.getY() + 50);
        _trueButton.setOnAction(new ClickHandlerTrue());
        _falseButton.setOnAction(new ClickHandlerFalse());
        _restartButton.setOnAction(new ClickHandlerRestart());
    }

    /*
     * helper method for setting up label
     */
    private void setUpLabel() {
        _scoreLabel = new Label("Lives left: ");
        _scoreLabel.setStyle("-fx-background-color: #FFFF33");
        _scoreLabel.setScaleX(2);
        _scoreLabel.setScaleY(2);
        _scoreLabel.setLayoutX(80);
        _scoreLabel.setLayoutY(30);
    }

    /*
     * helper method for setting up 'lives left' label
     */
    private void updateLivesLabel() {
        _scoreLabel.setText("Lives left: "+_lives);
    }

    /*
     * helper method for setting up true or false rectangles
     */
    private void setUpRects() {
        _true = new Rectangle(140, 140, Color.CORAL);
        _true.setX(200);
        _true.setY(700);
        _false = new Rectangle(140, 140, Color.TEAL);
        _false.setX(600);
        _false.setY(700);
    }

    /*
     * helper method for checking the state of the game (whether the game is over or not)
     */
    private void checkGameOver() {
        if (_lives <= 0) { // covering all cases (including negative lives), in case the variable skips number 0 for some reason.
           _isGameOver = true; // game is over
        } else if (_lives > 0) { // if there are lives left.
            _isGameOver = false; // the game is still going on
        }
    } // end of checkGameOver() method

    /*
     * Private inner class: ClickHandler that assess whether the question
     * is right or wrong when the user presses the 'true' button.
     * Answer: 1-false, 2-true, 3-false, 4-true, 5-true 6- true, 7-false, 8-true, 9-false.
     */
    private class ClickHandlerTrue implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (_shape.get_whichQIsIt() == 1 || _shape.get_whichQIsIt() == 3
                    || _shape.get_whichQIsIt() == 7 || _shape.get_whichQIsIt() == 9) { // if the user gets question wrong
                _lives--; // life decreases by 1
            } else if (_shape.get_whichQIsIt() == 2 || _shape.get_whichQIsIt() == 4
                    || _shape.get_whichQIsIt() == 5 || _shape.get_whichQIsIt() == 6
                    || _shape.get_whichQIsIt() == 8) { // if the user gets question right
                // nothing happens to life value, the question just gets reloaded. (which is specified after this if-statement)
            }
            // after question is removed, and the life score is accounted for,
            _geoPane.getChildren().remove(_shape.get_randomQuestion());
            _shape.produceQuestion();
            _geoPane.getChildren().add(_shape.get_randomQuestion());
            _shape.loadAnotherQ(); // reset Question!
        }
    }

    /*
     * Private inner class: ClickHandler that assess whether the question
     * is right or wrong when the user presses the 'false' button.
     * Answer: 1-false, 2-true, 3-false, 4-true, 5-true 6- true, 7-false, 8-true, 9-false.
     */
    private class ClickHandlerFalse implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (_shape.get_whichQIsIt() == 2 || _shape.get_whichQIsIt() == 4
                    || _shape.get_whichQIsIt() == 5|| _shape.get_whichQIsIt() == 6
                    || _shape.get_whichQIsIt() == 8) { // if the user gets question wrong
                _lives--; // life decreases by 1
            } else if (_shape.get_whichQIsIt() == 1 || _shape.get_whichQIsIt() == 3
                    || _shape.get_whichQIsIt() == 7 || _shape.get_whichQIsIt() == 9) { // if the user gets question right
                // nothing happens to life value, the question just gets reloaded. (which is specified after this if-statement)
            }
            // after question is removed, and the life score is accounted for,
            _geoPane.getChildren().remove(_shape.get_randomQuestion());
            _shape.produceQuestion();
            _geoPane.getChildren().add(_shape.get_randomQuestion());
            _shape.loadAnotherQ(); // reset Question!
        }
    }

    /*
     * Private inner class: ClickHandler that restarts the game.
     * Graphical removal of all elements from the game pane and
     * logical resetting of all important game values. (life)
     */
    private class ClickHandlerRestart implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
        // go from game over screen-> to "reset all the values", and "add everything to the pane again".

              _geoPane.getChildren().clear(); // remove game over label and button
             _geoPane.getChildren().addAll(_bground, _scoreLabel, _true,
                    _false, _trueButton, _falseButton);
             _geoPane.getChildren().addAll(_shape.getShape(), _shape.get_randomQuestion());
             _lives = 3; // reset remaining lives
        }
    }

    /*
     * helper method for setting up the timeline. (for animating the falling question)
     */
    private void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(0.75), new TimeHandler()); // duration: 0.75 second
        _geoTimeline = new Timeline(kf); // passing in keyframe
        _geoTimeline.setCycleCount(Animation.INDEFINITE);
        _geoTimeline.play();
    }

    /*
     * Private inner class: TimeHandler that animates the game
     * and checks for important updates.
     */
    private class TimeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GeometryMinigame.this.checkGameOver();
            GeometryMinigame.this.updateLivesLabel();

            if (_isGameOver == false) {
                _shape.fallDown();
                _shape.get_randomQuestion().setLayoutX(_shape.getX() + 30);
                _shape.get_randomQuestion().setLayoutY(_shape.getY() + 20);

            }

            // when a question reaches the bottom of the game window, deduct 1 from life score and re-load the question.
            if (_shape.get_randomQuestion().getLayoutY()>=1000) { // check for cases of y-coordinate greater than 1000 or more.
                _lives--; // subtract one from life value
                _geoPane.getChildren().remove(_shape.get_randomQuestion());
                _shape.produceQuestion();
                _geoPane.getChildren().add(_shape.get_randomQuestion());
                _shape.loadAnotherQ(); // reset Question!
            }
            // if one question has been answered. load another question.

           if (_isGameOver == true) { // if game is over, load the 'game over screen'.
               _geoPane.getChildren().clear();
               _gameOverLabel = new Label("GAME OVER!");
               _gameOverLabel.setStyle("-fx-background-color: #F51B00");
               _gameOverLabel.setAlignment(Pos.CENTER);
               _gameOverLabel.setScaleX(3);
               _gameOverLabel.setScaleY(3);
               _gameOverLabel.setLayoutX(440);
               _gameOverLabel.setLayoutY(400);
               _geoPane.getChildren().addAll(_gameOverLabel, _restartButton);
           }
        }
    }

    /*
     * helper method for setting up the background image.
     * I pull my PNG file from the same directory, and get the resource stream before I
     * put it into an ImageView to display the drawing. (This drawing is done by one of the creators!)
     */
    private void setUpBackground(Pane pane) { // why null pointer exception?? :(
        // setting up background image
        // below, I am obtaining the saved png file from the same file directory.
        Image image = new Image(this.getClass().getResourceAsStream("minigame_background.PNG"));
        _bground = new ImageView(); // instantiate new ImageView
        _bground.setImage(image); // pass in image into ImageView
        _bground.setFitWidth(1000); // set width as game window's width
        _bground.setFitHeight(1000); // set height of ImageView
        _bground.setPreserveRatio(true); // preserve ratio
        _bground.setSmooth(true);
        _bground.setCache(true);
        _bground.setOpacity(0.75); // a little translucent
        _bground.setFocusTraversable(false); // diverting focus from this ImageView node
    }


/*
 * Start of my private inner 'Shape' class.
 * This is a wrapper class of Java's Rectangle shape,
 * which has a Label overlapped on top of it.
 * I used a wrapper class because I wanted my 'Shape' to have
 * some additional properties on top of the original functionalities and
 * methods of 'Rectangle', such as an ability to randomly re-load
 * any of the 9 questions, or keep track of which question has been
 * selected (this is helpful for getting a question number for grading in ClickHandler class,
 * and I save this 'question number' with an instance variable of int).
 */
    private class Shape {
        private Rectangle _rect;
        private Label _question1; // instantiation of private labels (question labels)
        private Label _question2;
        private Label _question3;
        private Label _question4;
        private Label _question5;
        private Label _question6; // advanced questions. (smiles)
        private Label _question7;
        private Label _question8;
        private Label _question9;

        private int _whichQIsIt; // int value that tells us which question was randomly selected (for answer-checking)
        private Label _randomQuestion;
        private Node _shape;

        public Shape() { // constructor
            _rect = new Rectangle(300, 50);
            _rect.setStyle("Color.gray");
            _rect.setX(400); // initial position x and y.
            _rect.setY(0);
            this.setupLabels(); // setting up question labels
            _shape = _rect;
            this.produceQuestion();  // sets current question as a random question.

        }

        /*
         * accessor methods for nodes and double values (x-, y-coordinates)
         */
        public Node getShape() {
            return _shape;
        }

        public double getX() {
            return _rect.getX();
        }
        public double getY() {
            return _rect.getY();
        }

        /*
         * helper method for animating rectangle
         */
        public void fallDown() {
            _rect.setY(_rect.getY() + 20);
        }

        /*
         * Helper method to create another random question! (To be called in the TimeHandler)
         */
        public void loadAnotherQ() {
            _rect.setX(400); // initial position x and y.
            _rect.setY(0);
            _randomQuestion.setLayoutX(400);
            _randomQuestion.setLayoutY(0);

        }

        /*
         * Factored out helper method for setting up question labels
         */
        private void setupLabels(){
            _question1 = new Label("An isosceles triangle is a right triangle."); // answer: false
            _question1.setStyle("-fx-background-color: #FFFFFF");
            _question1.setScaleX(1.5);
            _question1.setScaleY(1.5);

            _question2 = new Label("A right triangle is an isosceles triangle."); // answer: true
            _question2.setStyle("-fx-background-color: #FFFFFF");
            _question2.setScaleX(1.5);
            _question2.setScaleY(1.5);

            _question3 = new Label("A rectangle is a square."); // answer: false
            _question3.setStyle("-fx-background-color: #FFFFFF");
            _question3.setScaleX(1.5);
            _question3.setScaleY(1.5);

            _question4 = new Label("5*25 is 125."); // answer: true
            _question4.setStyle("-fx-background-color: #FFFFFF");
            _question4.setScaleX(1.5);
            _question4.setScaleY(1.5);

            _question5 = new Label("The derivative of 4x^2 is 8x."); // answer: true
            _question5.setStyle("-fx-background-color: #FFFFFF");
            _question5.setScaleX(1.5);
            _question5.setScaleY(1.5);

            _question6 = new Label("Sin of theta is 'Opposite over Hypotenuse'."); // answer: true
            _question6.setStyle("-fx-background-color: #FFFFFF");
            _question6.setScaleX(1.5);
            _question6.setScaleY(1.5);

            _question7 = new Label("The sum of three times a number and 2 less than 4 times that same number is 61." +
                    "Is that number 8?"); // answer: false, it's 9. Sorry, I wanted to include one impossible question.
            _question7.setStyle("-fx-background-color: #FFFFFF");
            _question7.setScaleX(1.5);
            _question7.setScaleY(1.5);

            _question8 = new Label("6 = 2(y+2). y = 1 ?"); // answer: true
            _question8.setStyle("-fx-background-color: #FFFFFF");
            _question8.setScaleX(1.5);
            _question8.setScaleY(1.5);

            _question9 = new Label("The derivative of 2x^(1/2) is x."); // answer: false
            _question9.setStyle("-fx-background-color: #FFFFFF");
            _question9.setScaleX(1.5);
            _question9.setScaleY(1.5);
        }

        /*
         * Accessor method for getting the 'random question' instance variable,
         * which represents the current question being displayed on the screen.
         */
        public Label get_randomQuestion() {
            return _randomQuestion;
        }

        /*
         * Helper method that selects a random question and keeps track of which question was
         * selected. It uses a switch statement to achieve this.
         */
        public void produceQuestion() { // sets _shape as a random thing, and returns a random q!
            int rand_int = (int) (Math.random()*10);
            switch (rand_int) { // switch statement to produce random question
                case 0:
                    _randomQuestion = _question1;
                    _whichQIsIt = 1;
                    break;
                case 1:
                    _randomQuestion = _question2;
                    _whichQIsIt = 2;
                    break;
                case 2:
                    _randomQuestion = _question3;
                    _whichQIsIt = 3;
                    break;
                case 3:
                    _randomQuestion = _question4;
                    _whichQIsIt = 4;
                    break;
                case 4:
                    _randomQuestion = _question5;
                    _whichQIsIt = 5;
                    break;
                case 5:
                    _randomQuestion = _question6;
                    _whichQIsIt = 6;
                    break;
                case 6:
                    _randomQuestion = _question7;
                    _whichQIsIt = 7;
                    break;
                case 7:
                    _randomQuestion = _question8;
                    _whichQIsIt = 8;
                    break;
                case 8:
                    _randomQuestion = _question9;
                    _whichQIsIt = 9;
                    break;
                case 9:
                    _randomQuestion = _question5;
                    _whichQIsIt = 5;
                    break;
                default:
                    _randomQuestion = _question6;
                    _whichQIsIt = 6;
                    break;
            }

        } // end of switch statement

        /*
         * Accessor method for question number.
         */
        public int get_whichQIsIt() { // returning question number, for answer-checking.
            return _whichQIsIt;
        }

    } // end of Shape class
} // end of GeometryMinigame class
