package pt.pa.view;

import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.pa.model.*;
import pt.pa.model.busModelInterface;
import pt.pa.view.busView;
import pt.pa.controller.GraphController;

public class busNewPanel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        busModelInterface model = new busModelInterface();
        busView view = new busView(model);

        SmartGraphDemoContainer graphView = view.createGraph();

        new GraphController(model, view);

        StackPane rootPane = new StackPane();
        rootPane.getChildren().addAll(view ,graphView);

        Scene scene = new Scene(rootPane, 1280, 850);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Teste Network");
        stage.setMinHeight(768);
        stage.setMinWidth(1200);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        view.initGraphDisplay();

    }

}
