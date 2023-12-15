package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class insController {

    @FXML BorderPane borderPane;

    @FXML
    public void initialize() {

    }

    @FXML
    void OKbutton(ActionEvent e) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }
}
