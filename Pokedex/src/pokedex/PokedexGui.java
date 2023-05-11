package pokedex;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * PokedexGUI that starts the application and sets the stage and scene
 * @author Bethany
 */
public class PokedexGui extends Application {

    /**
     * Start method to load the application FXML and CSS files
     * @param stage 
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method method to launch the application
     * @param args 
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
