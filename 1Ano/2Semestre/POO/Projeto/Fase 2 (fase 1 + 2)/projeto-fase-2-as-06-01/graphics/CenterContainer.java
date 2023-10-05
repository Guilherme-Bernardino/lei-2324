package graphics;

import coords.Reader;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import locals.DistributionCenter;
import locals.StorageLocal;
import products.Transportable;

import java.util.ArrayList;
import java.util.List;

/**
 * EN
 * Center container for visual elements pertaining to all locals and vehicles.
 * Includes panels for vehicles, all locals, information about these objects.
 *
 * PT
 * Contentor central para elementos visuais pertencentes a todos os locais e veículos.
 * Inclui painéis para veículos, todos os locais, informações sobre esses objetos.
 *
 * @author guilh
 */
public class CenterContainer extends StackPane {

    DistributionCenter distributionCenter;
    VehicleContainer vehicleContainer;

    ObservableList<Transportable> dropOffList;
    ObservableList<Transportable> pickUpList;

    public CenterContainer(){
        super();
        create();
    }

    /**
     * Create all the components for the distribution center.
     */
    public void create(){
        Reader reader = new Reader();

        distributionCenter = reader.readDistributionCenter(Reader.LOCALS_APP_PATH);

        distributionCenter.addLocals(reader.readLocals(Reader.LOCALS_APP_PATH));
        distributionCenter.addVehicles(reader.readVehicles(Reader.VEHICLES_APP_PATH));

        distributionCenter.getWarehouse().setStorageLocalList(reader.readStorageLocals(Reader.LOCALS_APP_PATH));

        distributionCenter.getDropOffLocal().addProducts(reader.readProducts(Reader.PRODUCTS_PATH));
        distributionCenter.getDropOffLocal().packProducts();
        distributionCenter.getDropOffLocal().packPallet();

        for (StorageLocal storageLocal: distributionCenter.getWarehouse().getStorageLocalList()) {
            storageLocal.setPackageList(reader.readPackages(Reader.PACKAGES_PATH));
        }

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

        vehicleContainer = new VehicleContainer(distributionCenter.getVehicles());

        distributionPane.getChildren().add(warehousePane);
        distributionPane.getChildren().add(pickUpPane);
        distributionPane.getChildren().add(dropOffPane);
        distributionPane.getChildren().addAll(storageLocalsShapes);
        distributionPane.getChildren().addAll(vehicleContainer.values());

        vbox.getChildren().addAll(distributionPane, dpPane);
        getChildren().add(vbox);
    }

    /**
     * Creates info panel for drop off and pick up locals info.
     *
     * @param distributionCenter
     * @return hbox
     */
    private HBox createPickUpDropOffPaneInfo(DistributionCenter distributionCenter){
        HBox hBox = new HBox();

        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, null, null);
        Background background = new Background(backgroundFill);
        hBox.setBackground(background);

        BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
        Border border = new Border(borderStroke);
        hBox.setBorder(border);

        Label lblDropOff = new Label(distributionCenter.getDropOffLocal().toString());
        Label lblDropOff2 = new Label("List of Products in DropOff:");

        dropOffList = FXCollections.observableArrayList(distributionCenter.getDropOffLocal().getPackageList());
        ListView<Transportable> dropOffListView = new ListView<>(dropOffList);
        lblDropOff2.setLabelFor(dropOffListView);

        VBox vboxDropOff = new VBox(lblDropOff2,dropOffListView);

        Label lblPickUp = new Label(distributionCenter.getPickUpLocal().toString());
        Label lblPickUp2 = new Label("List of Products in PickUp:");

        pickUpList = FXCollections.observableArrayList(distributionCenter.getPickUpLocal().getProductList());
        ListView<Transportable> pickUpListView = new ListView<>(pickUpList);
        lblPickUp2.setLabelFor(pickUpListView);


        VBox vboxPickUp = new VBox(lblPickUp2,pickUpListView);

        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(20);

        Label lblSimulationText = new Label("Simulation Step");
        Label lblStepCount = new Label();
        lblStepCount.textProperty().bind(GraphicSimulation.getDoubleProperty().asString());

        hBox.getChildren().addAll(lblDropOff, vboxDropOff,lblPickUp, vboxPickUp, lblSimulationText, lblStepCount);

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
            storageLocalShape.setWidth(storageLocal.getDimension().getX());
            storageLocalShape.setHeight(storageLocal.getDimension().getY());
            storageLocalShape.setFill(Color.LIGHTBLUE);
            storageLocalShape.setStroke(Color.GRAY);
            storageLocalsShapes.add(storageLocalShape);
            storageLocalShape.setTranslateY(storageLocal.getPosition().getY());

            storageLocalShape.setOnMouseEntered(event -> {
                Stage stage = new Stage();
                stage.setTitle("Storage Information");

                VBox content = new VBox();
                Label label = new Label(storageLocal.toString());
                Label label2 = new Label("List of Products: ");
                ObservableList<Transportable> slList = FXCollections.observableArrayList(storageLocal.getPackageList());
                ListView<Transportable> slListView = new ListView<>(slList);

                content.getChildren().addAll(label, label2, slListView);
                content.setStyle("-fx-background-color: white; -fx-padding: 10px;");

                Scene scene = new Scene(content, 300, 250);
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();

                Bounds shapeBounds = storageLocalShape.localToScreen(storageLocalShape.getBoundsInLocal());
                Point2D shapeCenter = new Point2D((shapeBounds.getMinX() + shapeBounds.getMaxX()) / 2, (shapeBounds.getMinY() + shapeBounds.getMaxY()) / 2);
                double offsetX = shapeCenter.getX() - stage.getWidth() - 50;
                double offsetY = shapeCenter.getY() - stage.getHeight() / 2;
                stage.setX(offsetX);
                stage.setY(offsetY);

                stage.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        stage.close();
                    }
                });

                storageLocalShape.setOnMouseExited(event2 -> {
                    if (!stage.isFocused()) {
                        stage.close();
                    }
                });
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
        warehouseShape.setFill(Color.LIGHTGRAY);
        warehouseShape.setStroke(Color.BLACK);

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

    /**
     * Returns container's main object.
     *
     * @return distributionCenter
     */
    public DistributionCenter getDistributionCenter(){
        return distributionCenter;
    }

    /**
     * Returns vehicles container.
     *
     * @return vehicleContainer
     */
    public VehicleContainer getVehicleContainer(){
        return vehicleContainer;
    }

    /**
     * Updates observable lists.
     */
    public void updateLists(){

        Platform.runLater(()->{
            dropOffList.setAll(distributionCenter.getDropOffLocal().getPackageList());
            pickUpList.setAll(distributionCenter.getPickUpLocal().getProductList());
        });
    }
}
