package graphics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import products.Transportable;
import vehicles.AGC;
import vehicles.AGV;
import vehicles.TowingVehicle;
import vehicles.ULC;

import java.util.HashMap;
import java.util.List;

/**
 * EN
 * Vehicles Container that creates all vehicles and deals with each position and actions.
 *
 * PT
 * Contentor de Veículos que cria todos os veículos e lida com cada posição e ações.
 *
 * @author guilh
 */
public class VehicleContainer extends HashMap<AGV, Circle>{

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

            vehicleShape.setCenterX(vehicle.getPositionProperty().get().getX());
            vehicleShape.setCenterY(vehicle.getPositionProperty().get().getY());
            vehicleShape.setTranslateX(vehicle.getPositionProperty().get().getX());
            vehicleShape.setTranslateY(vehicle.getPositionProperty().get().getY());

            if(vehicle instanceof ULC){
                vehicleShape.setOnMouseClicked(event -> ((ULC) vehicle).increaseSpeed());
            }

            vehicleShape.setOnMouseEntered(createMouseHoverEvent(vehicle, vehicleShape));

            put(vehicle, vehicleShape);
        }
    }

    /**
     * Create a mouse hover event for vehicle info panel.
     *
     * @param vehicle
     * @param vehicleShape
     * @return event
     */
    private EventHandler<MouseEvent> createMouseHoverEvent(AGV vehicle, Circle vehicleShape) {
        return event -> {
            Stage stage = new Stage();
            stage.setTitle("Vehicle Information");

            VBox content = new VBox();
            Label label = new Label(vehicle.toString());
            Label label2 = new Label("List of Products: ");
            ObservableList<Transportable> vList = FXCollections.observableArrayList(vehicle.getPackages());
            ListView<Transportable> vListView = new ListView<>();
            vListView.setItems(vList);

            HBox ulcSpeedbox = new HBox();
            if(vehicle instanceof ULC){
                Label lblSpeed = new Label("Speed: ");
                TextField txtSpeedCount = new TextField(vehicle.getSpeed() + "");
                ulcSpeedbox.getChildren().addAll(lblSpeed, txtSpeedCount);
            }

            content.getChildren().addAll(label,label2,vListView,ulcSpeedbox);
            content.setStyle("-fx-background-color: white; -fx-padding: 10px;");

            Scene scene = new Scene(content, 300, 250);
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
