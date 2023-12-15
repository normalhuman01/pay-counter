package controllers;

import Classes.AlertDialog;
import Classes.Report;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sql.ReportQueries;
import sql.UserQueries;

public class AddEntryController {

    @FXML ComboBox comboUser;
    @FXML TextField LMField;
    @FXML TextField CFField;
    @FXML TextField MCField;
    @FXML TextField CEField;
    @FXML TextField APField;
    @FXML TextField ICField;
    @FXML TextField ERField;
    @FXML TextField opcField;

    public static int nEntries = 0;
    Report report;
    UserQueries SQLuser = new UserQueries();
    ReportQueries SQLreport = new ReportQueries();
    ObservableList<String> users = FXCollections.observableArrayList();
    AlertDialog alert = new AlertDialog();
    
    @FXML
    public void initialize() {
        opcField.setText("0");
        comboUser.setPromptText("Usuario");
        comboUser.setVisibleRowCount(4);
        comboUser.setOnMouseClicked((MouseEvent event) -> {
            users.removeAll(users); 
            comboUser.setItems(users);
            users.addAll(SQLuser.getAllUsers());
        });
    }

    @FXML
    void addButton(ActionEvent e) {    
        if(add() != 0){
            alert.displayAlert(Alert.AlertType.INFORMATION, 
                    "Añadir Entrada", "Entrada añadida exitosamente");
        }else{
            alert.displayAlert(Alert.AlertType.ERROR, 
                    "Añadir Entrada", "Ocurrió un problema al añadir la entrada");
        }
            
    }

    @FXML
    void showButton(ActionEvent e) {
        try {

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/FXMLfiles/paymentList.fxml"));
            Parent listPaymentParent = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Lista de pagos");
            stage.getIcons().add(new Image("/img/icons8-idea-bank-100.png"));
            stage.setScene(new Scene(listPaymentParent));
            stage.setResizable(false);
            
            if(!PaymentListController.data.isEmpty()){
                PaymentListController.data.remove(0, nEntries);
            }
            PaymentListController.data.addAll(SQLreport.PaymentList());
            
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AddEntryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    void modifyButton(ActionEvent e){
        if(SQLreport.modifyEntry(comboUser.getValue().toString())!= 0){
            add();
            alert.displayAlert(Alert.AlertType.INFORMATION, 
                    "Modificar Entrada", "Entrada modificada exitosamente");
        }else{
            alert.displayAlert(Alert.AlertType.ERROR, 
                    "Modificar Entrada", "Ocurrió un problema al añadir la entrada");
        }
        
    }
    
    private int add(){
        nEntries++;
        return SQLreport.AddEntry(new Report(
                SQLuser.getId(comboUser.getSelectionModel().getSelectedItem().toString()),
                LocalTime.now(), LocalDate.now(),
                Double.parseDouble(LMField.getText()), 
                SQLreport.lastMonth(comboUser.getSelectionModel().getSelectedItem().toString()),
                Double.parseDouble(CFField.getText()),
                Double.parseDouble(MCField.getText()),
                Double.parseDouble(CEField.getText()),
                Double.parseDouble(APField.getText()),
                Double.parseDouble(ICField.getText()),
                Double.parseDouble(ERField.getText()),
                opcField.getText()));      
    }
        
        
}
