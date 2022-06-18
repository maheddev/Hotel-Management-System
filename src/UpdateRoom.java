import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateRoom implements Initializable {

    @FXML
    private ToggleGroup AC1;

    @FXML
    private RadioButton ACno;

    @FXML
    private RadioButton ACyes;

    @FXML
    private ToggleGroup Bar1;

    @FXML
    private RadioButton BarNo;

    @FXML
    private RadioButton Baryes;

    @FXML
    private TextField BedNo;

    @FXML
    private Button FetchData;

    @FXML
    private TextField FloorNo;

    @FXML
    private RadioButton KitchenNo;

    @FXML
    private TextField RoomNoF;

    @FXML
    private ComboBox<String> RoomType;
    ObservableList<String> data = FXCollections.observableArrayList("Economy", "Executive", "Apartment", "Suite");
    public void initialize(URL location, ResourceBundle resources) {
        RoomType.setItems(data);
        RoomType.setEditable(true);
        RoomType.setVisibleRowCount(3);
    }

    @FXML
    private Button backButton;

    @FXML
    private RadioButton isResNo;

    @FXML
    private RadioButton isResYes;

    @FXML
    private ToggleGroup kitchen1;

    @FXML
    private RadioButton kitchenYes;


    @FXML
    private Button saveDataButton;


    @FXML
    void backButtonF(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("Fxmls/MainPage.fxml"));
        stage = new Stage();
        stage.setTitle("Hotel Management App");
        stage.setScene(new Scene(root));
        
        stage.show();
    }
    PreparedStatement p = null;
    PreparedStatement ps = null;
    ResultSet r = null;
    Connecter conC = new Connecter();
    Connection con = conC.connection;
    Connection con1 = conC.connection;

    @FXML
    void fetchDataF(ActionEvent event) {
        String query = "select * from RoomsData where RoomNo = ?";
        try {
            p = con.prepareStatement(query);
            p.setString(1, RoomNoF.getText());
            r = p.executeQuery();
            if(r.next()){
                JOptionPane.showMessageDialog(null,"Data Fetched!");
                FloorNo.setText(r.getString(2));
                BedNo.setText(r.getString(5));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }String stAC, stBar, stKitchen, stIsRes;
    String getACSelection(){
        if(ACyes.isSelected()){
            stAC = "yes";
        }
        else{
            stAC = "No";
        }
        return stAC;
    }
    String getBarSelection(){
        if(Baryes.isSelected()){
            stBar = "yes";
        }
        else{
            stBar = "No";
        }
        return stBar;
    }
    String getKitchenSelection(){
        if(KitchenNo.isSelected()){
            stKitchen = "No";
        }
        else{
            stKitchen = "Yes";
        }
        return stKitchen;
    }
    @FXML
    void saveDataF(ActionEvent event) throws SQLException {
        String q = "UPDATE RoomsData Set Floor = ?, roomType = ?, AC=? , Beds = ?, MiniBar = ?, MiniKitchen= ? where RoomNo= ?";
        ps = con1.prepareStatement(q);
        ps.setString(1, String.valueOf(FloorNo.getText()));
        FloorNo.setEditable(false);
        ps.setString(2, RoomType.getSelectionModel().getSelectedItem().toString());
        ps.setString(3, getACSelection());
        ps.setString(4, BedNo.getText());
        ps.setString(5, getBarSelection());
        ps.setString(6, getKitchenSelection());
        ps.setString(7, RoomNoF.getText());
        ps.execute();

    }



}
