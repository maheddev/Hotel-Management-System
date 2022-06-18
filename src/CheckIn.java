import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class CheckIn {

    @FXML
    private Button Confirm;

    @FXML
    private Button backButton;

    @FXML
    private DatePicker dateArrival;

    @FXML
    private TextField guestNameField;

    @FXML
    private TextField guestNumField1;

    @FXML
    private Label label;
    PreparedStatement p = null;
    PreparedStatement ps = null;
    ResultSet r = null;
    String S1,S2;
    @FXML
    void Confirmf(ActionEvent event) throws SQLException {
        String Q = "insert into CheckIn(resNum,Checkind) values(?,?)";
        try {
            Connecter conC = new Connecter();
            Connection con = conC.connection;
            p = con.prepareStatement(Q);
            p.setString(1, guestNumField1.getText());
            p.setString(2, dateArrival.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            p.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String query = "select G.Gname,Rm.RoomNo from guests G, RoomsData Rm, Reservations  Rs, CheckIn C  where G.GNumber = Rs.GNumber AND Rs.resNum = C.resNum AND Rm.RoomNo = Rs.RoomNo AND C.resNum = ?";
        Connecter conC = new Connecter();
        Connection con1 = conC.connection;
        ps = con1.prepareStatement(query);
        ps.setString(1,guestNumField1.getText());
        r = ps.executeQuery();
        if(r.next()){
            S1 = r.getString( 1);
            S2 = r.getString( 2);
            String m = "Room No." + S2 + " Allocated to: "+S1;
            label.setText(m);
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
