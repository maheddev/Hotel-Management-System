import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;

public class addNewGuest {

    @FXML
    private TextField addressField;

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
    private Button backButton;
    @FXML
    private Button saveDataButton;
    PreparedStatement p = null;
    @FXML
    void saveDataF(ActionEvent event) {
        String Query = "insert into Guests(GName,GNumber,GCnic,GContact,GAddress,GCity,GMail,GDOB)values(?,?,?,?,?,?,?,?)";
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
            p.execute();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
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

}
