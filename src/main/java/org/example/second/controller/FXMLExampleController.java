package org.example.second.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.example.second.model.DatabaseMock;
import org.example.second.model.GymClient;

import java.util.List;

public class FXMLExampleController {

    private final static Integer MAX_CLIENTS_IN_GYM = 3;

    @FXML
    private ListView<Text> activeListView;

    @FXML
    private ListView<Text> registeredListView;

    @FXML
    private ListView<Text> queueListView;

    @FXML
    private Button checkOutButton;

    @FXML
    private Button checkInButton;

    @FXML
    private Button deleteButton;

    @FXML
    public void initialize() {
        DatabaseMock databaseMock = new DatabaseMock();
        List<GymClient> list = databaseMock.loadClient();

        for (GymClient gymClient : list) {
            Text text = new Text();
            text.setText(gymClient.getName() + " " + gymClient.getSurname() + " " + gymClient.getIdCode());

            registeredListView.getItems().add(text);

        }

        deleteButton.setOnMouseClicked(t -> {
            Text selectedText = registeredListView.getSelectionModel().getSelectedItem();
            if (selectedText == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No client is selected!");
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to terminate the contract?");

            if (alert.showAndWait().get().getButtonData().name().equals("OK_DONE")) {
                registeredListView.getItems().remove(selectedText);
            }
        });


        checkOutButton.setOnMouseClicked(t -> {
            Text selectedText = activeListView.getSelectionModel().getSelectedItem();
            activeListView.getItems().remove(selectedText);
            if (selectedText == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No client is selected!");
                alert.show();
                return;
            }
            activeListView.getItems().remove(selectedText);
            registeredListView.getItems().add(selectedText);
            if(!queueListView.getItems().isEmpty()){
                Text firstSelectedText =
                        queueListView.getItems().get(0);
                queueListView.getItems().remove(firstSelectedText);
                activeListView.getItems().add(firstSelectedText);
            }

        });

        checkInButton.setOnMouseClicked(t -> {
            Text selectedText = registeredListView.getSelectionModel().getSelectedItem();
            if (selectedText == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No client is selected");
                alert.show();
            } else {
                registeredListView.getItems().remove(selectedText);
                if (activeListView.getItems().size() > 2) {
                    queueListView.getItems().add(selectedText);
                } else {
                    activeListView.getItems().add(selectedText);
                }
            }

        });

    }

}