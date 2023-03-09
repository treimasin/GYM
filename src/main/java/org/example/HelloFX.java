package org.example;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class HelloFX extends Application {




    public void doOutput(VBox topVBox, Button button) {
        button.setOnMouseClicked(
                mouseEvent -> {
                    ObservableList<Node> list
                            = topVBox.getChildren();
                    //Find children from vBox by ID: ID = name-Label
                    Label label = (Label) list.stream()
                            .filter(t -> t.getId() != null)
                            .filter(t -> t.getId().equals("name-Label"))
                            .findFirst().get();
                    label.setText("Text - changed (filter)");
                }

        );

        doOutput(topVBox, button);

    }

    @Override
    public void start(Stage stage) {

        VBox rootVBox = new VBox();

        HBox hBox = new HBox();
        hBox.setSpacing(10);

        TextField textFieldA = new TextField();
        TextField textFieldB = new TextField();

        Label plus = new Label("+");
        Label equals = new Label("=");
        Label result = new Label();

        rootVBox.getChildren().add(hBox);
        rootVBox.setSpacing(15);
        hBox.getChildren().add(textFieldA);
        hBox.getChildren().add(plus);
        hBox.getChildren().add(textFieldB);
        hBox.getChildren().add(equals);
        hBox.getChildren().add(result);

        Button button = new Button("Calculate");

        Button clear = new Button("Clear");
        clear.setOnMouseClicked(t -> {
            textFieldA.setText("");
            textFieldB.setText("");
            result.setText("");
        });

        button.setOnMouseClicked(t -> {
            try {
                Long numberA = Long.parseLong(textFieldA.getText());
                Long numberB = Long.parseLong(textFieldB.getText());
                result.setText(String.valueOf(numberA + numberB));

            } catch (NumberFormatException e) {
                result.setText("Error");
            }

        });

        rootVBox.getChildren().add(button);
        rootVBox.getChildren().add(clear);


        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(rootVBox);
        Scene scene = new Scene(stackPane, 640, 480);
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(HelloFX.class, args);
    }

}
