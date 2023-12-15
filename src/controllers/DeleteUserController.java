package controllers;

import Classes.AlertDialog;
import sql.UserQueries;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteUserController {

    @FXML private TextField nameField;
    @FXML private Button closeButton;

    UserQueries sql = new UserQueries();

    @FXML
    void deleteUserButtonPressed(ActionEvent event) {
        AlertDialog alert = new AlertDialog();
        Optional<ButtonType> result;
        result = alert.displayAlert(Alert.AlertType.CONFIRMATION,
                "Confirmación", "¿Desea eliminar este usuario?");
        Stage stage = (Stage) nameField.getScene().getWindow();

        if (result.get() == ButtonType.OK) {
            int res = sql.DeleteUser(nameField.getText());
            if (res == 0) {
                alert.displayAlert(Alert.AlertType.ERROR,
                        "Error", "Ocurrió un problema, inténtelo de nuevo.");
            }else{
                alert.displayAlert(Alert.AlertType.INFORMATION,"Exito",
                    "Usuario eliminado exitosamente");
                stage.close();
            }
        }
    }

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
