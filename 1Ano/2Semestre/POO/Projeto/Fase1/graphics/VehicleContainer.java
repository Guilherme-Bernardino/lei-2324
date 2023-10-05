package graphics;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import locals.StorageLocal;
import vehicles.AGC;
import vehicles.AGV;
import vehicles.TowingVehicle;
import vehicles.ULC;

import java.util.ArrayList;
import java.util.List;

public class VehicleContainer extends ArrayList<Circle>{


    public VehicleContainer(List<AGV> vehicles){
        create(vehicles);
    }

    /**
     * Create all AGV present in the graphic interface.
     *
     * @param vehicles
     */
    public void create(List<AGV> vehicles){
        for (AGV vehicle : vehicles) {
            Circle vehicleShape = new Circle();

            if(vehicle instanceof AGC){
                vehicleShape.setRadius(7);
                vehicleShape.setFill(Color.GREEN);
            }
            if(vehicle instanceof ULC){
                vehicleShape.setRadius(7);
                vehicleShape.setFill(Color.PURPLE);
            }
            if(vehicle instanceof TowingVehicle){
                vehicleShape.setRadius(7);
                vehicleShape.setFill(Color.YELLOW);
            }

            vehicleShape.setCenterX(vehicle.getPositionProperty().getX());
            vehicleShape.setCenterY(vehicle.getPositionProperty().getY());
            vehicleShape.setTranslateX(vehicle.getPositionProperty().getX());
            vehicleShape.setTranslateY(vehicle.getPositionProperty().getY());

            vehicleShape.setOnMouseEntered(createMouseHoverEvent(vehicle, vehicleShape));

            add(vehicleShape);
        }
    }

    private EventHandler<MouseEvent> createMouseHoverEvent(AGV vehicle, Circle vehicleShape) {
        return event -> {
            Stage stage = new Stage();
            stage.setTitle("Vehicle Information");

            VBox content = new VBox();
            Label label = new Label(vehicle.toString());
            Label label2 = new Label("List of Products: " + FXCollections.observableArrayList(vehicle.getPackages()));
            content.getChildren().addAll(label,label2);
            content.setStyle("-fx-background-color: white; -fx-padding: 10px;");

            Scene scene = new Scene(content, 250, 100);
            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();

            Bounds shapeBounds = vehicleShape.localToScreen(vehicleShape.getBoundsInLocal());
            Point2D shapeCenter = new Point2D((shapeBounds.getMinX() + shapeBounds.getMaxX()) / 2, (shapeBounds.getMinY() + shapeBounds.getMaxY()) / 2);
            double offsetX = shapeCenter.getX() - stage.getWidth() - 10;
            double offsetY = shapeCenter.getY() - stage.getHeight() / 2;
            stage.setX(offsetX);
            stage.setY(offsetY);

            vehicleShape.setOnMouseExited(closeStageEvent(stage));
        };
    }

    /**
     * Auxiliary method for closing info windows.
     *
     * @param stage
     * @return
     */
    private EventHandler<MouseEvent> closeStageEvent(Stage stage) {
        return event -> stage.close();
    }
}
