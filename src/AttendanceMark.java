import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class AttendanceMark {

    @FXML
    private TextField StaffNumber;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField hoursWorked;

    @FXML
    private RadioButton newStaff;

    @FXML
    private RadioButton newStaff1;

    @FXML
    private Button saveDataButton;

    @FXML
    private ToggleGroup staffcat;


    PreparedStatement p = null;
    PreparedStatement ps = null;

    ResultSet r = null;
    @FXML
    void saveDataF(ActionEvent event) throws SQLException {
        if(newStaff.isSelected()){
            String Query = "insert into staffAttendance(SNumber,HoursWorked,MarkingDate)values(?,?,?)";
            try {
                Connecter conC = new Connecter();
                Connection con = conC.connection;
                p = con.prepareStatement(Query);
                p.setString(1, StaffNumber.getText());
                p.setInt(2, Integer.parseInt(hoursWorked.getText()));
                p.setString(3, dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                p.execute();
                JOptionPane.showMessageDialog(null,"Attendance Marked!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Operation Failed!");
            }
        }
        else {
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
                    int total = hours + Integer.parseInt(hoursWorked.getText());
                    p.setInt(1, total);
                    p.setString(2, dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    p.setString(3, StaffNumber.getText());
                    p.execute();
                    JOptionPane.showMessageDialog(null,"Attendance Updated!");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Operation Failed!");
            }
        }


    }
}