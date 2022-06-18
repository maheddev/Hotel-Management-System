import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpPage {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordLogin;

    @FXML
    private ToggleGroup role;

    @FXML
    private RadioButton guest;

    @FXML
    private RadioButton manager;

    @FXML
    private TextField usernameLogin;
    ResultSet resultSet = null;
    PreparedStatement prep = null;
    Stage stage;

    String welcomeNote;
    @FXML
    void loginClicked(ActionEvent event) {
        if(manager.isSelected()){
            String query = "Select * from login where userName = ? and password=?";
            try {
                Connecter conC = new Connecter();
                Connection con = conC.connection;
                prep = con.prepareStatement(query);
                prep.setString(1,usernameLogin.getText());
                prep.setString(2,passwordLogin.getText());
                resultSet = prep.executeQuery();
                if(resultSet.next()){
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("Fxmls/MainPage.fxml"));
                    stage = new Stage();
                    stage.setTitle("Hotel Management App");
                    stage.setScene(new Scene(root));

                    welcomeNote = "Welcome Dear, "+usernameLogin;
                    stage.show();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Username or Password Incorrect!");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(guest.isSelected()){
            String query = "Select * from Guestslogin l, Guests G where G.GNumber = l.GNumber and GName = ? and passwordS=?";
            try {
                Connecter conC = new Connecter();
                Connection con = conC.connection;
                prep = con.prepareStatement(query);
                prep.setString(1,usernameLogin.getText());
                prep.setString(2,passwordLogin.getText());
                resultSet = prep.executeQuery();
                if(resultSet.next()){
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("Fxmls/GuestMenu.fxml"));
                    stage = new Stage();
                    stage.setTitle("Hotel Management App");
                    stage.setScene(new Scene(root));
                    //
                    stage.show();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Username or Password Incorrect!");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
