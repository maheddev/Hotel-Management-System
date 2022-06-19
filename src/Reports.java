import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports {

    @FXML
    private TextField setDesignation;

    @FXML
    private TableColumn<?, ?> City;

    @FXML
    private TableColumn<?, ?> ContactT;

    @FXML
    private TableColumn<?, ?> EmailT;

    @FXML
    private TableColumn<?, ?> GuestNameT;

    @FXML
    private Button backButton;

    @FXML
    private Button getReservationB;

    @FXML
    private TableColumn<?, ?> stafftNumT;
    @FXML
    private TableView<ReportsAide> tableViewT;

    Connecter conC = new Connecter();
    Connection con = conC.connection;
    ResultSet r = null;
    PreparedStatement p = null;
    ObservableList<ReportsAide> data;

    @FXML
    void backButtonF(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Fxmls/MainPage.fxml"));
        stage.setTitle("Hotel Management App");
        stage.setMaxHeight(768);
        stage.setMaxWidth(1366);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void setCellTable(){
        stafftNumT.setCellValueFactory(new PropertyValueFactory<>("StaffNumberR"));
        GuestNameT.setCellValueFactory(new PropertyValueFactory<>("StaffNameR"));
        ContactT.setCellValueFactory(new PropertyValueFactory<>("StaffContactR"));
        EmailT.setCellValueFactory(new PropertyValueFactory<>("StaffEmailR"));
        City.setCellValueFactory(new PropertyValueFactory<>("StaffCityR"));
    }

    @FXML
    void getReservationF(ActionEvent event) throws SQLException {
        String query = "select SNumber,SName,SContact,SMail,SCity from Staff where Spos = ?";
        setCellTable();
        Connection con = conC.connection;
        data = FXCollections.observableArrayList();
        p = con.prepareStatement(query);
        p.setString(1,setDesignation.getText());
        r = p.executeQuery();
        while(r.next()){
            data.add(new ReportsAide(r.getString(1),r.getString(2),r.getString(3),r.getString(4),r.getString(5)));
        }
        tableViewT.setItems(data);
        tableViewT.setVisible(true);
    }

}
