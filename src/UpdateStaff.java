import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UpdateStaff implements Initializable {

    @FXML
    private TextField DesignationFeild;

    @FXML
    private Button FetchData;

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
    PreparedStatement p = null;
    PreparedStatement ps = null;
    ResultSet r = null;
    Connecter conC = new Connecter();
    Connection con = conC.connection;
    Connection con1 = conC.connection;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    void fetchDataF(ActionEvent event) throws SQLException {
        String query = "select * from Staff where SNumber = ?";
        try {
            p = con.prepareStatement(query);
            p.setString(1,guestNumField.getText());
            r = p.executeQuery();
            if(r.next()){
                fullNameField.setText(r.getString( 2));
                cnicNumField.setText(r.getString( 3));
                contactNumField.setText(r.getString( 4));
                addressField.setText(r.getString( 5));
                cityField.setText(r.getString( 6));
                emailAddfild.setText(r.getString( 7));
                dateField.getEditor().setText(String.format(r.getString( 8), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                DesignationFeild.setText(r.getString( 9));
                JoiningDate.getEditor().setText(String.format(r.getString(10), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
            else{

                JOptionPane.showMessageDialog(null,"Operation Failed, Try Again");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
        @FXML
    void saveDataF(ActionEvent event) throws SQLException {
            String q = "UPDATE Staff Set SName = ?, SCnic = ?, SContact=? , SAddress = ?, SCity = ?, SMail= ?, SDOB= ? ,Spos= ? ,SDoj= ? where SNumber= ?";
            try{
                ps = con1.prepareStatement(q);
                ps.setString(1,fullNameField.getText());
                ps.setString(2,cnicNumField.getText());
                ps.setString(3,contactNumField.getText());
                ps.setString(4,addressField.getText());
                ps.setString(5,cityField.getText());
                ps.setString(6,emailAddfild.getText());
                ps.setString(7,dateField.getEditor().getText());
                ps.setString(8,DesignationFeild.getText());
                ps.setString(9,JoiningDate.getEditor().getText());
                ps.setString(10,guestNumField.getText());
                ps.execute();
                JOptionPane.showMessageDialog(null, "Data Updated");
            }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Operation Failed!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
