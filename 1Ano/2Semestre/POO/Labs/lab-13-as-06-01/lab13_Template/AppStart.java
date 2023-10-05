


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Escreva uma descrição da classe JavaFX AppStart aqui.
 *
 * @author (seu nome aqui)
 * @version (número da versão ou data aqui)
 */
public class AppStart extends Application
{

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(new MenuPane(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
