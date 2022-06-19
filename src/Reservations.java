import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class Reservations {

    @FXML
    private TableColumn<?, ?> ContactT;

    @FXML
    private TableColumn<?, ?> GuestNameT;

    @FXML
    private TableColumn<?, ?> GuestNumT;

    @FXML
    private TableColumn<?, ?> RoomT;

    @FXML
    private TableColumn<?, ?> DateT;

    @FXML
    private Button backButton;

    @FXML
    private DatePicker endDateR;

    @FXML
    private Button getReservationB;

    @FXML
    private DatePicker startDateR;


    @FXML
    private TableView<ReservationAide> tableViewT;

    Connecter conC = new Connecter();
    Connection con = conC.connection;
    ResultSet r = null;
    PreparedStatement p = null;
    ObservableList<ReservationAide> data;

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
        GuestNumT.setCellValueFactory(new PropertyValueFactory<>("GNumberRA"));
        GuestNameT.setCellValueFactory(new PropertyValueFactory<>("GNameRA"));
        ContactT.setCellValueFactory(new PropertyValueFactory<>("GContactRA"));
        RoomT.setCellValueFactory(new PropertyValueFactory<>("RoomNoRA"));
        DateT.setCellValueFactory(new PropertyValueFactory<>("BookingDate"));
    }

    @FXML
    void getReservationF(ActionEvent event) throws SQLException {
        String query = "select g.GName,g.GNumber,g.GContact,Rm.RoomNo,Rs.BookingDate from guests g, RoomsData Rm, Reservations Rs  where g.GNumber = Rs.GNumber AND Rm.RoomNo = Rs.RoomNo AND Rs.BookingDate > ? and Rs.BookingDate < ?";
        setCellTable();
        Connection con = conC.connection;
        data = FXCollections.observableArrayList();
        p = con.prepareStatement(query);
        p.setString(1,startDateR.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        p.setString(2,endDateR.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        r = p.executeQuery();
        while(r.next()){
            data.add(new ReservationAide(r.getString(1),r.getString(2),r.getString(3),r.getString(4),r.getString(5)));
        }
        tableViewT.setItems(data);
        tableViewT.setVisible(true);
    }
}
