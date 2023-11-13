package com.example.prac_1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static javafx.application.Application.launch;




public class HelloApplication extends Application{
    private Person person;
    private Text scoreText;
    public void start(Stage stage) throws Exception {
        person = new Person();
        Group root = new Group();
        Image backgroundImage = new Image("C:\\Users\\dy704\\IdeaProjects\\Prac_1\\target\\classes\\com\\example\\prac_1\\diag_1.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(Bindings.selectDouble(root.sceneProperty(), "width"));
        backgroundImageView.fitHeightProperty().bind(Bindings.selectDouble(root.sceneProperty(), "height"));


        backgroundImageView.setPreserveRatio(false);
        StackPane.setAlignment(backgroundImageView, Pos.CENTER);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Update the score each second
            person.incrementScore();
            updateScoreTextOnUI();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Create Text node for displaying the score
        scoreText = new Text();
        scoreText.setFont(Font.font(20));

        // Bind the scoreText to the person's score property
        scoreText.textProperty().bind(Bindings.concat("Score: ", person.scoreProperty()));
        VBox vBox = new VBox(scoreText);

        // Add nodes to the StackPane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, vBox);
        root.getChildren().add(stackPane);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Stick Hero");
        stage.show();
    }
    private void updateScoreTextOnUI() {
        Platform.runLater(() -> {
            scoreText.setText("Score: " + person.getScore());
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}
