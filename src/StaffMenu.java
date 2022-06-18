import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffMenu {

    @FXML
    private Button checkOut;

    @FXML
    private Button checkin;
    PreparedStatement p,p1,p2 = null;
    ResultSet r = null;
    Connecter conC = new Connecter();
    Connection con = conC.connection;
    Connection con1 = conC.connection;
    Connection con2 = conC.connection;
    @FXML
    void checkOutF(ActionEvent event) {
        String str = JOptionPane.showInputDialog(null,"Enter Reservation No. ");
        String query = "delete from checkIn where ResNum = ?";
        String queryM = "select Re.RoomNo from Reservations Re where Re.ResNum = ?";
        String query1 = "update RoomsData set isRes = 'No' where RoomNo = ?";
        try {
            p = con.prepareStatement(query);
            p1 = con1.prepareStatement(queryM);
            p2 = con2.prepareStatement(query1);
            p1.setString(1,str);
            ResultSet r = p1.executeQuery();
            if(r.next()){
                String room = r.getString(1);
                p2.setString(1,room);
                p2.execute();
                p.setString(1, str);
                p.execute();
                String str1 = "Check Out Confirmed of: "+str+"!";
                JOptionPane.showMessageDialog(null, str1);

            }
            else{
                JOptionPane.showMessageDialog(null, "Reservation Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private Button LogoutButton;
    @FXML
    void LogoutButtonF(ActionEvent event) {
        Stage stage = (Stage) LogoutButton.getScene().getWindow();
        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxmls/LoginPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.setTitle("Hotel Management App");
        stage.setScene(new Scene(root));
        //
        stage.show();
    }
    @FXML
    void checkinf(ActionEvent event) {
        Stage stage = (Stage) checkin.getScene().getWindow();
        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxmls/CheckIn.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.setTitle("Hotel Management App");
        stage.setScene(new Scene(root));
        //
        stage.show();
    }

}
