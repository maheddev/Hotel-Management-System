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
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddRoom implements Initializable {

    @FXML
    private RadioButton ACno;

    @FXML
    private RadioButton ACyes;

    @FXML
    private RadioButton BarNo;

    @FXML
    private RadioButton Baryes;

    @FXML
    private TextField BedNo;

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
    }

    @FXML
    private Button backButton;

    @FXML
    private RadioButton isResNo;

    @FXML
    private RadioButton isResYes;

    @FXML
    private RadioButton kitchenYes;

    @FXML
    private Button saveDataButton;

    @FXML
    void backButtonF(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Fxmls/MainPage.fxml"));
        stage.setMaxHeight(768);
        stage.setMaxWidth(1366);
        stage.setTitle("Hotel Management App");
        stage.setScene(new Scene(root));
        stage.show();
    }

    PreparedStatement p = null;
    String stAC, stBar, stKitchen, stIsRes;
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
        if(kitchenYes.isSelected()){
            stKitchen = "yes";
        }
        else{
            stKitchen = "No";
        }
        return stKitchen;
    }
    String getisResSelection(){
        if(isResYes.isSelected()){
            stIsRes = "yes";
        }
        else{
            stIsRes = "No";
        }
        return stIsRes;
    }
    @FXML
    void saveDataF(ActionEvent event) {
        String Query = "insert into RoomsData(RoomNo,Floor,roomType,AC,Beds,MiniBar,MiniKitchen,isRes)values(?,?,?,?,?,?,?,?)";
        try {
            Connecter conC = new Connecter();
            Connection con = conC.connection;
            p = con.prepareStatement(Query);
            p.setString(1, RoomNoF.getText());
            p.setString(2, String.valueOf(FloorNo.getText()));
            p.setString(3, RoomType.getSelectionModel().getSelectedItem().toString());
            p.setString(4, getACSelection());
            p.setString(5, BedNo.getText());
            p.setString(6, getBarSelection());
            p.setString(7, getKitchenSelection());
            p.setString(8, getisResSelection());
            p.execute();
            JOptionPane.showMessageDialog(null,"Data Saved!");
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null,"Operation Failed!");
        }

    }
}