package Classes;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/FXMLfiles/main.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Contador");
        stage.getIcons().add(new Image("img/icons8-idea-bank-100.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
