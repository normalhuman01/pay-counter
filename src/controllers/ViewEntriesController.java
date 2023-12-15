package controllers;

import Classes.Report;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sql.*;

public class ViewEntriesController {

    @FXML TableView tableView;
    @FXML ComboBox comboUser;

    ObservableList<String> users = FXCollections.observableArrayList();
    ObservableList<Report> list = FXCollections.observableArrayList();
    UserQueries SQLuser = new UserQueries();
    ReportQueries SQLreport = new ReportQueries();

    @FXML
    public void initialize() {

        comboUser.setPromptText("Usuario");
        comboUser.setVisibleRowCount(4);
        comboUser.setOnMouseClicked((MouseEvent event) -> {
            users.removeAll(users);
            comboUser.setItems(users);
            users.addAll(SQLuser.getAllUsers());
        });
        comboUser.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String u = comboUser.getValue().toString();
                if (u != null){
                    tableView.getItems().clear();
                    list.addAll(SQLreport.ReportList(u));
                    tableView.setItems(list);
                    System.out.println(u);
                }
            }
        });
        Table();
    }
    
    public ObservableList<Report> getList(){
        return list;
    }

    private void Table() {

        TableColumn<String, Report> c1 = new TableColumn("Usuario");
        c1.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<LocalTime, Report> c2 = new TableColumn("Hora");
        c2.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<LocalDate, Report> c3 = new TableColumn("Fecha");
        c3.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Double, Report> c4 = new TableColumn("Lectura");
        c4.setCellValueFactory(new PropertyValueFactory<>("lecturaMes"));

        TableColumn<Double, Report> c5 = new TableColumn("Pago");
        c5.setCellValueFactory(new PropertyValueFactory<>("payment"));

        tableView.getColumns().addAll(c1, c2, c3, c4, c5);

        c1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        c2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        c3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        c4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        c5.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));

        c1.setResizable(false);
        c2.setResizable(false);
        c3.setResizable(false);
        c4.setResizable(false);
        c5.setResizable(false);
    }
}
