import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;

public class App extends javafx.application.Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Fxmls/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //stage.setMaximized(true);
        stage.setTitle("Hotel Management App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Started");
        launch();
    }

}
