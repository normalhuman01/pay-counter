
package Classes;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertDialog {
    public Optional<ButtonType> displayAlert(
            javafx.scene.control.Alert.AlertType type, String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        
        alert.getDialogPane().getStylesheets().add(getClass().
                getResource("/css/DialogPane.css").toExternalForm());
        
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/img/icons8-idea-bank-100.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        return alert.showAndWait();
    }
}
