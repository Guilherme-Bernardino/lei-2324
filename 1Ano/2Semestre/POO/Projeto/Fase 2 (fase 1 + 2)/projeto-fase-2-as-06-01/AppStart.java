import graphics.CenterContainer;
import graphics.SimulationMenu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * EN
 * Main app class.
 *
 * PT
 * Classe principal da applicação.
 *
 * @author guilh
 */
public class AppStart extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    CenterContainer centerContainer;

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = new BorderPane();
        centerContainer = new CenterContainer();
        SimulationMenu menuBar = new SimulationMenu(centerContainer);

        root.setTop(menuBar);
        root.setCenter(centerContainer);

        BackgroundFill backgroundFill = new BackgroundFill(Color.GREY, CornerRadii.EMPTY, new Insets(0,0,0,0));
        Background background = new Background(backgroundFill);
        centerContainer.setBackground(background);

        Scene scene =  new Scene(root, centerContainer.getWidth(), centerContainer.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation!");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
