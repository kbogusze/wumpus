import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = new FXMLLoader(getClass().getClassLoader().getResource("fxml/window.fxml")).load();
        Image applicationIcon = new Image(getClass().getResource("/icon/favicon.png").toString());
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setTitle("HUNT THE WUMPUS");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
