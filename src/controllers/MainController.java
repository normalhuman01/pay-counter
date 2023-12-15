package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController {

    @FXML BorderPane borderPane;

    @FXML
    public void initialize() {
        loadUI("/FXMLfiles/addEntry.fxml");
    }

    @FXML
    void userListMenu(ActionEvent e) {
        loadFXML("/FXMLfiles/Userlist.fxml", "Lista de usuarios");
    }

    @FXML
    void adduserMenu(ActionEvent e) {
        loadFXML("/FXMLfiles/addUser.fxml", "Añadir usuario");
    }

    @FXML
    void deleteUserMenu(ActionEvent e) {
        loadFXML("/FXMLfiles/deleteUser.fxml", "Eliminar usuario");
    }

    @FXML
    void addEntryMenu(ActionEvent e) {
        loadUI("/FXMLfiles/addEntry.fxml");
    }

    @FXML
    void viewEntriesMenu(ActionEvent e) {
        loadUI("/FXMLfiles/viewEntries.fxml");
    }
    
    @FXML
    void aboutMenu(ActionEvent e) {
        loadFXMLUndecorated("/FXMLfiles/about.fxml");
    }
    
    @FXML
    void insMenu(ActionEvent e){
         loadFXMLUndecorated("/FXMLfiles/ins.fxml");
    }
    
    private void loadFXML(String source, String title) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(source));
            Parent addUserParent = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.getIcons().add(new Image("/img/icons8-idea-bank-100.png"));
            stage.setScene(new Scene(addUserParent));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadFXMLUndecorated(String source){
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(source));
            Parent addUserParent = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/img/icons8-idea-bank-100.png"));
            stage.setScene(new Scene(addUserParent));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadUI(String ui) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(ui));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        borderPane.setCenter(root);

    }
}
