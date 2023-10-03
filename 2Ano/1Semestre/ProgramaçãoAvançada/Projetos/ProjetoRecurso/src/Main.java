import javafx.application.Application;
import javafx.stage.Stage;
import pt.pa.controller.BusController;
import pt.pa.model.BusModel;
import pt.pa.view.BusView;

import java.io.IOException;

/**
 * Initiates the application.
 *
 * @author Guilherme B. 202001870
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        BusModel model = new BusModel();
        BusView view = new BusView(model);
        BusController controller = new BusController(model, view);

        view.initGraphDisplay();
    }


}
