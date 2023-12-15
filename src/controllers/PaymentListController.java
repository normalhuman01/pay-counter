package controllers;

import Classes.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PaymentListController {
    
    @FXML TableView tableView;
    public static ObservableList<Report> data = FXCollections.observableArrayList();
    
    public void initialize(){
        TableColumn<String, Report> usernameC = new TableColumn<>("Usuario");
        usernameC.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameC.prefWidthProperty().bind(tableView.widthProperty().multiply(0.50));
        
        TableColumn<Double, Report> paymentC = new TableColumn<>("Monto");
        paymentC.setCellValueFactory(new PropertyValueFactory<>("payment"));
        paymentC.prefWidthProperty().bind(tableView.widthProperty().multiply(0.50));
    
        tableView.getColumns().addAll(usernameC, paymentC);
        tableView.setItems(data);
    }
    
    @FXML 
    void OKbutton(ActionEvent e) {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.close();
    }
}
