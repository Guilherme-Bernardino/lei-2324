

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

/**
 * Escreva uma descrição da classe JavaFX AppStart aqui.
 *
 * @author (seu nome aqui)
 * @version (número da versão ou data aqui)
 */
public class AppStart extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();
        Player player = new Player();
        PlayerPane pPane = new PlayerPane(player);
        PlayerEditPane epPane = new PlayerEditPane(player);
        
        root.setLeft(pPane);
        root.setRight(epPane);
        
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Testar propriedades");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
