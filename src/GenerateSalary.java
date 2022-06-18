import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class GenerateSalary {

    @FXML
    private TextField PerHourSalary;

    @FXML
    private TextField StaffNumber;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button saveDataButton;
    PreparedStatement p = null;
    PreparedStatement ps = null;

    ResultSet r = null;
    @FXML
    void saveDataF(ActionEvent event) {
        String Query = "update staffAttendance set HoursWorked = ? , MarkingDate = ? where SNumber = ?";
        String q1 = "Select HoursWorked from staffAttendance where SNumber = ?";
        try {
            Connecter conC = new Connecter();
            Connection con = conC.connection;
            Connection con1 = conC.connection;
            p = con1.prepareStatement(Query);
            ps = con.prepareStatement(q1);
            ps.setString(1,StaffNumber.getText());
            r = ps.executeQuery();
            if(r.next()){
                int hours = r.getInt(1);
                int total = hours * Integer.parseInt(PerHourSalary.getText());
                JOptionPane.showMessageDialog(null,"Salary of StaffNo.: "+StaffNumber.getText()+" is: "+total);
                p.setInt(1, 0);
                p.setString(2, dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                p.setString(3, StaffNumber.getText());
                p.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
