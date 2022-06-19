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
import java.time.format.DateTimeFormatter;

public class AddStaff {

    @FXML
    private TextField DesignationFeild;

    @FXML
    private DatePicker JoiningDate;

    @FXML
    private TextField addressField;

    @FXML
    private Button backButton;

    @FXML
    private TextField cityField;

    @FXML
    private TextField cnicNumField;

    @FXML
    private TextField contactNumField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField emailAddfild;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField guestNumField;

    @FXML
    private Button saveDataButton;
    PreparedStatement p = null;
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

    @FXML
    void saveDataF(ActionEvent event) {
        String Query = "insert into Staff(SName,SNumber,SCnic,SContact,SAddress,SCity,SMail,SDOB,Spos,SDoj)values(?,?,?,?,?,?,?,?,?,?)";
        try {
            Connecter conC = new Connecter();
            Connection con = conC.connection;
            p = con.prepareStatement(Query);
            p.setString(1,fullNameField.getText());
            p.setString(2,guestNumField.getText());
            p.setString(3,cnicNumField.getText());
            p.setString(4,contactNumField.getText());
            p.setString(5,addressField.getText());
            p.setString(6,cityField.getText());
            p.setString(7,emailAddfild.getText());
            p.setString(8,dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            p.setString(9,DesignationFeild.getText());
            p.setString(10,JoiningDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            p.execute();

            JOptionPane.showMessageDialog(null,"Data Saved!");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"An Error Occurred");
        }
    }

}