package graphics;

import coords.Reader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import locals.DistributionCenter;
import locals.Local;
import locals.StorageLocal;
import products.Product;
import vehicles.AGV;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CenterContainer extends StackPane {

    public CenterContainer(){
        super();
        create();
    }

    /**
     * Create all the components for the distribution center.
     */
    public void create(){
        Reader reader = new Reader();

        DistributionCenter distributionCenter = reader.readDistributionCenter(Reader.LOCALS_APP_PATH);

        distributionCenter.addLocals(reader.readLocals(Reader.LOCALS_APP_PATH));
        distributionCenter.addVehicles(reader.readVehicles(Reader.VEHICLES_APP_PATH));

        distributionCenter.getWarehouse().setStorageLocalList(reader.readStorageLocals(Reader.LOCALS_APP_PATH));

        List<Product> products = reader.readProducts(Reader.PRODUCTS_PATH);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10 ));
        vbox.setSpacing(20);

        StackPane distributionPane = new StackPane();
        HBox dpPane = createPickUpDropOffPaneInfo(distributionCenter);

        setPadding(new Insets(10,10,10,10 ));
        setWidth(distributionCenter.getDimension().getY());
        setHeight(distributionCenter.getDimension().getX());
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

        StackPane warehousePane = createWarehouse(distributionCenter);
        StackPane pickUpPane = createPickUp(distributionCenter);
        StackPane dropOffPane = createDropOff(distributionCenter);
        StackPane storageLocalsShapes = createStorageLocals(distributionCenter);

        VehicleContainer vehicleContainer = new VehicleContainer(distributionCenter.getVehicles());

        distributionPane.getChildren().add(warehousePane);
        distributionPane.getChildren().add(pickUpPane);
        distributionPane.getChildren().add(dropOffPane);
        distributionPane.getChildren().addAll(storageLocalsShapes);
        distributionPane.getChildren().addAll(vehicleContainer);

        vbox.getChildren().addAll(distributionPane, dpPane);
        getChildren().add(vbox);
    }

    private HBox createPickUpDropOffPaneInfo(DistributionCenter distributionCenter){
        HBox hBox = new HBox();
        Label lblDropOff = new Label(distributionCenter.getDropOffLocal().toString());
        Label lblDropOff2 = new Label("List of Products in DropOff: " + FXCollections.observableArrayList(distributionCenter.getDropOffLocal().getPackageList()));
        Label lblPickUp = new Label(distributionCenter.getPickUpLocal().toString());
        Label lblPickUp2 = new Label("List of Products in PickUp: " + FXCollections.observableArrayList(distributionCenter.getPickUpLocal().getProductList()));

        hBox.setPadding(new Insets(10,10,10,10 ));
        hBox.setSpacing(20);

        hBox.getChildren().addAll(lblDropOff,lblDropOff2, lblPickUp, lblPickUp2);
        return hBox;
    }


    /**
     * Auxiliary method for creating the storage locals container.
     *
     * @param distributionCenter
     * @return storages stack pane
     */
    private StackPane createStorageLocals(DistributionCenter distributionCenter){
        List<Rectangle> storageLocalsShapes = new ArrayList<>();
        StackPane storageLocals = new StackPane();

        for (StorageLocal storageLocal : distributionCenter.getWarehouse().getStorageLocalList()) {
            Rectangle storageLocalShape = new Rectangle();
            storageLocalShape.setWidth(storageLocal.getDimension().getY());
            storageLocalShape.setHeight(storageLocal.getDimension().getX());
            storageLocalShape.setFill(Color.LIGHTBLUE);
            storageLocalShape.setStroke(Color.GRAY);
            storageLocalsShapes.add(storageLocalShape);
            storageLocalShape.setTranslateY(storageLocal.getPosition().getX());

            storageLocalShape.setOnMouseEntered(event -> {
                Stage stage = new Stage();
                stage.setTitle("Storage Information");

                VBox content = new VBox();
                Label label = new Label(storageLocal.toString());
                Label label2 = new Label("List of Products: " + FXCollections.observableArrayList(storageLocal.getPackageList()));
                content.getChildren().addAll(label,label2);
                content.setStyle("-fx-background-color: white; -fx-padding: 10px;");

                Scene scene = new Scene(content, 250, 200);
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();

                Bounds shapeBounds = storageLocalShape.localToScreen(storageLocalShape.getBoundsInLocal());
                Point2D shapeCenter = new Point2D((shapeBounds.getMinX() + shapeBounds.getMaxX()) / 2, (shapeBounds.getMinY() + shapeBounds.getMaxY()) / 2);
                double offsetX = shapeCenter.getX() - stage.getWidth() - 50;
                double offsetY = shapeCenter.getY() - stage.getHeight() / 2;
                stage.setX(offsetX);
                stage.setY(offsetY);

                storageLocalShape.setOnMouseExited(closeStageEvent(stage));
            });
        }
        storageLocals.getChildren().addAll(storageLocalsShapes);
        return storageLocals;
    }

    /**
     * Auxiliary method for creating the warehouse container.
     *
     * @param distributionCenter
     * @return warehouse stack pane
     */
    private StackPane createWarehouse(DistributionCenter distributionCenter){
        Rectangle warehouseShape = new Rectangle();
        warehouseShape.setWidth(distributionCenter.getWarehouse().getDimension().getY());
        warehouseShape.setHeight(distributionCenter.getWarehouse().getDimension().getX());
        warehouseShape.setFill(Color.TRANSPARENT);
        warehouseShape.setStroke(Color.GRAY);

        Text warehouseText = new Text("W");
        warehouseText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        warehouseText.setFill(Color.GRAY);
        warehouseText.setLayoutX(distributionCenter.getWarehouse().getDimension().getX() - distributionCenter.getWarehouse().getDimension().getX() / 2);
        warehouseText.setLayoutY(distributionCenter.getWarehouse().getDimension().getY() - distributionCenter.getWarehouse().getDimension().getY() / 2);

        StackPane warehousePane = new StackPane();
        warehousePane.getChildren().addAll(warehouseShape,warehouseText);
        return warehousePane;
    }

    /**
     * Auxiliary method for creating the pickup local container.
     *
     * @param distributionCenter
     * @return pickup stack pane
     */
    private StackPane createPickUp(DistributionCenter distributionCenter){
        Rectangle pickUpShape = new Rectangle();
        pickUpShape.setHeight(distributionCenter.getPickUpLocal().getDimension().getX());
        pickUpShape.setWidth(distributionCenter.getPickUpLocal().getDimension().getY());
        pickUpShape.setFill(Color.LIGHTGRAY);;
        pickUpShape.setStroke(Color.RED);
        //pickUpShape.setTranslateY(distributionCenter.getPickUpLocal().getPosition().getY()*20);
        pickUpShape.setTranslateX(distributionCenter.getPickUpLocal().getPosition().getX());

        Text pickUpText = new Text("P");
        pickUpText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        pickUpText.setFill(Color.RED);
        pickUpText.setTranslateX(distributionCenter.getPickUpLocal().getPosition().getX());

        StackPane pickUpPane = new StackPane();
        pickUpPane.getChildren().addAll(pickUpShape,pickUpText);
        return pickUpPane;
    }

    /**
     * Auxiliary method for creating the dropoff local container.
     *
     * @param distributionCenter
     * @return dropoff stack pane
     */
    private StackPane createDropOff(DistributionCenter distributionCenter){
        Rectangle dropOffShape = new Rectangle();
        dropOffShape.setHeight(distributionCenter.getDropOffLocal().getDimension().getX());
        dropOffShape.setWidth(distributionCenter.getDropOffLocal().getDimension().getY());
        dropOffShape.setFill(Color.LIGHTGRAY);
        dropOffShape.setStroke(Color.BLUE);
        //dropOffShape.setTranslateY(distributionCenter.getDropOffLocal().getPosition().getY()*20);
        dropOffShape.setTranslateX(distributionCenter.getDropOffLocal().getPosition().getX());

        Text dropOffText = new Text("D");
        dropOffText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        dropOffText.setFill(Color.BLUE);
        dropOffText.setTranslateX(distributionCenter.getDropOffLocal().getPosition().getX());

        StackPane dropOffPane = new StackPane();
        dropOffPane.getChildren().addAll(dropOffShape,dropOffText);
        return dropOffPane;
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
