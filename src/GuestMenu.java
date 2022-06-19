import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class GuestMenu {

    @FXML
    private Button LogoutButton;
    @FXML
    void LogoutButtonF(ActionEvent event) {
        Stage stage = (Stage) LogoutButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxmls/LoginPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Hotel Management App");
        stage.setMaxHeight(768);
        stage.setMaxWidth(1366);
        stage.setScene(new Scene(root));
        //
        stage.show();
    }
    @FXML
    private TextField Gum;

    @FXML
    private TextField RoomNum;

    @FXML
    private DatePicker date;

    @FXML
    private TextField resNo;

    @FXML
    private Button reserveRoom;

    PreparedStatement p1,p2,p3 = null;
    ResultSet r = null;
    Connecter con1 = new Connecter();
    Connecter con2 = new Connecter();
    Connecter con3 = new Connecter();

    Connection conA = con1.connection;
    Connection conB = con2.connection;
    Connection conC = con3.connection;
    @FXML
    void resRoomF(ActionEvent event) throws SQLException {
        try {
            String q1 = "Select isRes from RoomsData where RoomNo = ?";
            String q2 = "insert into Reservations(ResNum,GNumber,RoomNo,BookingDate)values(?,?,?,?)";
            String q3 = "update RoomsData set isRes = 'Yes' where RoomNo = ?";
            p1=conA.prepareStatement(q1);
            p2=conB.prepareStatement(q2);
            p3=conC.prepareStatement(q3);
            p1.setString(1,RoomNum.getText());
            r = p1.executeQuery();
            if(r.next()){
                String str = r.getString(1);
                if(str.equals("Yes")){
                    JOptionPane.showMessageDialog(null,"Room Not Available, Try Another Room!");
                }
                else{
                    p2.setString(1,resNo.getText());
                    p2.setString(2,Gum.getText());
                    p2.setString(3,RoomNum.getText());
                    p2.setString(4,date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    p2.execute();
                    p3.setString(1,RoomNum.getText());
                    p3.execute();
                    JOptionPane.showMessageDialog(null,"Room No."+RoomNum.getText()+" Allocated to: "+Gum.getText());
                }
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid Data, Please Try Again");
        }
    }
}
