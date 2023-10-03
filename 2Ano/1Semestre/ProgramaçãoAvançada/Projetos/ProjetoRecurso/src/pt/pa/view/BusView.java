package pt.pa.view;

import com.brunomnsilva.smartgraph.graph.Edge;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartGraphProperties;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.pa.controller.BusController;
import pt.pa.datasets_handler.reader_strategy.ReadDemoStrategy;
import pt.pa.datasets_handler.reader_strategy.ReadEuropeStrategy;
import pt.pa.datasets_handler.reader_strategy.ReadExportedStrategy;
import pt.pa.datasets_handler.reader_strategy.ReadIberiaStrategy;
import pt.pa.factorymethod.FullTicketFactory;
import pt.pa.factorymethod.OneWayTicketFactory;
import pt.pa.model.BusModel;
import pt.pa.model.BusModelException;
import pt.pa.model.Route;
import pt.pa.model.Stop;
import pt.pa.observerpattern.Observable;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Bus View class, belongs to MVC pattern, responsible for creating a user interface of the graph and respective buttons, text fields and labels.
 *
 * @author Guilherme B. 202001870
 */
public class BusView extends BorderPane implements BusNetworkUI {

    private static final double FONT_SIZE = 15;
    private BusModel model;

    //Buttons
    private Button btnAddStop;
    private Button btnAddRoute;
    private Button btnRemoveStop;
    private Button btnRemoveRoute;
    private Button btnReplaceRoute;
    private Button btnExportGraph;
    private Button btnSelectDemo;
    private Button btnSelectIberia;
    private Button btnSelectEurope;
    private Button btnSelectExported;
    private Button btnSelectDarkView;
    private Button btnSelectMapView;
    private Button btnSelectSatelliteView;
    private Button btnSelectTerrainView;
    private Button btnCalculateShortestPath;

    private Button btnGenerateOneWayTicketBasic;
    private Button btnGenerateOneWayTicketDetailed;
    private Button btnGenerateFullTicketBasic;
    private Button btnGenerateFullTicketDetailed;
    private Button btnUndo;

    //Text Fields
    private TextField txtStopName;
    private TextField txtStopCode;
    private TextField txtStopLongitude;
    private TextField txtStopLatitude;
    private TextField txtStopXCoord;
    private TextField txtStopYCoord;
    private TextField txtRouteDistance;
    private TextField txtRouteDuration;
    private TextField txtRouteCodeStart;
    private TextField txtRouteCodeEnd;
    private TextField txtStopCodeStartForShortestPath;
    private TextField txtStopCodeEndForShortestPath;

    //Stats Labels
    private Label lblStopCount;
    private Label lblRouteCount;
    private Label lblShortestPathDuration;
    private Label lblShortestPathDistance;

    //Error Message Label
    private Label lblError;

    //Smart Graph
    private SmartGraphPanel<Stop, Route> graphPanel;
    private Scene scene;
    private Stage stage;
    private HBox sidePanel;
    private VBox statsPane;
    private VBox countTuplesPane;
    private Image currentBackGroundImage;

    public BusView(BusModel model) {
        this.model = model;
        createLayout();
    }

    /**
     * Initiate Graph Display, creates a new scene with default dataset graph.
     */
    public void initGraphDisplay() {

        currentBackGroundImage = model.changeBackgroundImage(0);

        this.scene = new Scene(createStackPane(), 1680, 850);
        this.stage = new Stage(StageStyle.DECORATED);
        this.stage.setTitle("Bus Network");
        this.stage.setResizable(false);
        this.stage.setScene(this.scene);
        this.stage.show();

        updateBackgroundImage();

        graphPanel.init();
        System.out.println(model.getMap());
        graphPanel.updateAndWait();

        setVertexPosition();
        setStyles();

        update(model, null);
    }

    /**
     * Creates a new Stack Box for holding the graph and the rest of the view object.
     *
     * @return root pane for both graph panel and view object
     */
    public HBox createStackPane(){
        HBox rootPane = new HBox(graphPanel, this);
        return rootPane;
    }

    /**
     * Sets a label the desired style.
     *
     * @param label
     */
    private void setLabelStyles(Label label){
        label.setStyle("-fx-font-weight: bold;");
        label.setFont(new Font(FONT_SIZE));
    }

    /**
     * Creates a layout for the side panel and the graph panel.
     */
    private void createLayout() {

        this.sidePanel = createSidePanel();
        this.sidePanel.setPrefSize(700, 800);
        setRight(sidePanel);

        String customProp = "edge.label = true" + "\n" + "edge.arrow = true" + "\n" + "vertex.allow-user-move = true" + "\n" + "vertex.radius = 8 ";
        SmartGraphProperties properties = new SmartGraphProperties(customProp);

        this.graphPanel = new SmartGraphPanel<>(model.getMap(), properties);
        this.graphPanel.setAutomaticLayout(false);
    }

    /**
     * Creates a new side panel, a panel that holds buttons, text fields and labels.
     *
     * @return box that holds all the group panels.
     */
    private HBox createSidePanel() {

        GridPane actionsPane = createActionsPane();
        GridPane datasetsPane = createDatasetsPane();
        VBox statsPane = createStatsPane();
        GridPane mapViewPane = createMapViewPane();
        GridPane shortestPathPane = createShortestPathPane();
        GridPane generateTicketsPane = createTicketGeneratingPane();

        //Export Button
        btnExportGraph = new Button("Export...");

        Label lblStats = new Label ("Statistics");
        setLabelStyles(lblStats);

        Label lblActions = new Label("Actions");
        setLabelStyles(lblActions);

        Label lblDatasets = new Label("Maps");
        setLabelStyles(lblDatasets);

        Label lblExports = new Label("Export");
        setLabelStyles(lblExports);

        Label lblMapViews = new Label("Map Views");
        setLabelStyles(lblMapViews);

        Label lblShortestPath = new Label("Shortest Path");
        setLabelStyles(lblShortestPath);

        Label lblDistanceTitle = new Label("Distance (km): ");
        lblDistanceTitle.setStyle("-fx-font-weight: bold;");
        Label lblDurationTitle = new Label("Duration (min): ");
        lblDurationTitle.setStyle("-fx-font-weight: bold;");
        lblShortestPathDistance = new Label("");
        lblShortestPathDuration = new Label("");

        Label lblTicketGeneration = new Label("Generate Ticket");
        lblTicketGeneration.setStyle("-fx-font-weight: bold;");
        lblTicketGeneration.setFont(new Font(15));

        lblError = new Label("");
        setLabelStyles(lblError);
        lblError.setTextFill(Color.color(1, 0, 0));

        HBox actionBox = new HBox(lblActions,lblError);
        actionBox.setSpacing(100);

        btnUndo = new Button("Undo");

        /* COMPOSE */
        VBox leftPanel = new VBox(
                lblStats,
                statsPane
        );

        /* COMPOSE */
        VBox rightPanel = new VBox(
                actionBox,
                actionsPane,
                lblShortestPath,
                shortestPathPane,
                lblDistanceTitle,
                lblShortestPathDistance,
                lblDurationTitle,
                lblShortestPathDuration,
                lblDatasets,
                datasetsPane,
                lblMapViews,
                mapViewPane,
                lblExports,
                btnExportGraph,
                lblTicketGeneration,
                generateTicketsPane,
                btnUndo
        );

        leftPanel.setPadding(new Insets(10, 50, 10, 30));
        leftPanel.setSpacing(15);
        rightPanel.setPadding(new Insets(10, 10, 10, 10));
        rightPanel.setSpacing(10);

        HBox box = new HBox(leftPanel,rightPanel);

        return box;
    }

    /**
     * Create a stats panel, responsible for grouping information about graph statistics.
     *
     * @return statsPane vertical box
     */
    private VBox createStatsPane(){
        Label labelStopCount = new Label("Number of Stops: ");
        labelStopCount.setStyle("-fx-font-weight: bold;");
        Label labelRouteCount = new Label("Number of Routes: ");
        labelRouteCount.setStyle("-fx-font-weight: bold;");
        Label labelTuplesCount = new Label("Centrality of Stops: ");
        labelTuplesCount.setStyle("-fx-font-weight: bold;");

        lblStopCount = new Label("" + model.stopNumber());
        lblRouteCount = new Label("" + model.routesNumber());
        countTuplesPane = new VBox();

        HBox countsStopsPane = new HBox(labelStopCount, lblStopCount);
        HBox countsRoutesPane = new HBox(labelRouteCount, lblRouteCount);

        createCentralityPane();

        Label lblTop10 = new Label("Top10 - Click to open");
        lblTop10.setStyle("-fx-font-weight: bold;");

        lblTop10.setOnMouseClicked((event -> {
            List<Map.Entry<Stop, Integer>> map = model.getTop10Tuples();

            // Defining the x axis
            CategoryAxis xAxis = new CategoryAxis();

            xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList("Number of Stops")));
            xAxis.setLabel("Stop (Code)");

            // Defining the y axis
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Number of Adjacent Stops");

            // Creating the Bar chart
            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

            for (Map.Entry<Stop,Integer> entry : map) {
                // Prepare XYChart.Series objects by setting data
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(entry.getKey().getStopCode());
                series.getData().add(new XYChart.Data<>("Number of Stops", entry.getValue()));

                barChart.getData().add(series);
            }

            // Setting the data to bar chart
            Group root = new Group(barChart);
            Scene scene = new Scene(root, 500, 425);
            Stage primaryStage = new Stage();
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        }));

        statsPane = new VBox(countsStopsPane, countsRoutesPane,labelTuplesCount, lblTop10, countTuplesPane);
        statsPane.setSpacing(5);
        return statsPane;
    }

    /**
     * Creates an actions panel, responsible for grouping the actions button and text fields.
     *
     * @return grid panel that holds actions group interface
     */
    private GridPane createActionsPane() {
        GridPane actionsPane = new GridPane();
        actionsPane.setAlignment(Pos.BASELINE_LEFT);

        btnAddStop = new Button("Add Stop");
        btnAddRoute = new Button("Add Route");
        btnRemoveStop = new Button("Remove Stop");
        btnRemoveRoute = new Button("Remove Route");
        btnReplaceRoute = new Button("Replace Route");

        txtStopCode = new TextField("Stop Code");
        txtStopName = new TextField("Stop Name");
        txtStopLongitude = new TextField("Latitude");
        txtStopLatitude = new TextField("Longitude");
        txtStopXCoord = new TextField("X");
        txtStopYCoord = new TextField("Y");

        txtRouteCodeStart = new TextField("Start Code");
        txtRouteCodeEnd = new TextField("End Code");
        txtRouteDistance = new TextField("Distance");
        txtRouteDuration = new TextField("Duration");

        actionsPane.add(btnAddStop, 0, 0);
        actionsPane.add(btnRemoveStop, 1, 0);

        actionsPane.add(txtStopCode, 0, 1);
        actionsPane.add(txtStopName, 1, 1);
        actionsPane.add(txtStopLongitude, 0, 2);
        actionsPane.add(txtStopLatitude, 1, 2);
        actionsPane.add(txtStopXCoord, 0, 3);
        actionsPane.add(txtStopYCoord, 1, 3);

        actionsPane.add(btnAddRoute, 0, 4);
        actionsPane.add(btnRemoveRoute, 1, 4);
        actionsPane.add(btnReplaceRoute, 2,4);

        actionsPane.add(txtRouteCodeStart,0,5);
        actionsPane.add(txtRouteCodeEnd,1,5);
        actionsPane.add(txtRouteDistance,0,6);
        actionsPane.add(txtRouteDuration,1,6);

        actionsPane.setVgap(10);
        actionsPane.setHgap(10);
        return actionsPane;
    }

    /**
     * Creates the shortest path informational panel, responsible for showing the user the shortest path.
     *
     * @return grid panel that holds shortest group interface.
     */
    private GridPane createShortestPathPane(){
        GridPane shortestPathPane = new GridPane();
        shortestPathPane.setAlignment(Pos.BASELINE_LEFT);

        btnCalculateShortestPath = new Button("Calculate");

        txtStopCodeStartForShortestPath = new TextField("Starting Stop");
        txtStopCodeEndForShortestPath = new TextField("Ending Stop");

        shortestPathPane.add(btnCalculateShortestPath, 0,0);
        shortestPathPane.add(txtStopCodeStartForShortestPath,0,1);
        shortestPathPane.add(txtStopCodeEndForShortestPath,1,1);

        shortestPathPane.setVgap(10);
        shortestPathPane.setHgap(10);
        return shortestPathPane;
    }

    /**
     * Creates a datasets panel, responsible for grouping the buttons regarding the datasets.
     *
     * @return grid panel that holds buttons of datasets
     */
    private GridPane createDatasetsPane(){
        GridPane datasetsPane = new GridPane();
        datasetsPane.setAlignment(Pos.BASELINE_LEFT);
        btnSelectDemo = new Button("Demo");
        btnSelectIberia = new Button("Iberia");
        btnSelectEurope = new Button("Europe");
        btnSelectExported = new Button("Exported");
        datasetsPane.add(btnSelectDemo, 0,0);
        datasetsPane.add(btnSelectIberia, 1,0);
        datasetsPane.add(btnSelectEurope, 2,0);
        datasetsPane.add(btnSelectExported, 3, 0);

        datasetsPane.setVgap(10);
        datasetsPane.setHgap(10);
        return datasetsPane;
    }

    /**
     * Create centrality panel, generates a list of all stops and shows the user by descending order.
     */
    private void createCentralityPane(){
        countTuplesPane.getChildren().clear();
        List<Map.Entry<Stop, Integer>> map = model.getTuples();

        for (Map.Entry<Stop,Integer> entry : map) {
            HBox entryBox = new HBox();
            Label curStop = new Label( entry.getKey().getStopName() + " | " +  entry.getValue());
            entryBox.getChildren().add(curStop);
            countTuplesPane.getChildren().addAll(entryBox);
        }
    }

    /**
     * Creates a map views panel, responsible for grouping the buttons regarding the maps of the datasets.
     *
     * @return grid panel that holds buttons of map views
     */
    private GridPane createMapViewPane(){
        GridPane mapViewPane = new GridPane();
        mapViewPane.setAlignment(Pos.BASELINE_LEFT);

        btnSelectDarkView = new Button("Dark");
        btnSelectMapView = new Button("Map");
        btnSelectSatelliteView = new Button("Satellite");
        btnSelectTerrainView = new Button("Terrain");

        mapViewPane.add(btnSelectDarkView, 0, 0);
        mapViewPane.add(btnSelectMapView, 1, 0);
        mapViewPane.add(btnSelectSatelliteView, 2, 0);
        mapViewPane.add(btnSelectTerrainView, 3, 0);

        mapViewPane.setVgap(10);
        mapViewPane.setHgap(10);
        return mapViewPane;
    }

    /**
     * Create a ticket generation panel, responsible for grouping the buttons regarding creating new tickets.
     *
     * @return grid panel that holds buttons for ticket creation
     */
    private GridPane createTicketGeneratingPane(){
        GridPane ticketGeneratingPane = new GridPane();
        ticketGeneratingPane.setAlignment(Pos.BASELINE_LEFT);

        btnGenerateOneWayTicketBasic = new Button("One-Way Basic Ticket");
        btnGenerateOneWayTicketDetailed = new Button("One-Way Detailed Ticket");
        btnGenerateFullTicketBasic = new Button("Full Basic Ticket");
        btnGenerateFullTicketDetailed = new Button("Full Detailed Ticket ");

        ticketGeneratingPane.add(btnGenerateOneWayTicketBasic,0,0);
        ticketGeneratingPane.add(btnGenerateOneWayTicketDetailed,1,0);
        ticketGeneratingPane.add(btnGenerateFullTicketBasic, 0,1 );
        ticketGeneratingPane.add(btnGenerateFullTicketDetailed, 1,1);

        ticketGeneratingPane.setHgap(10);
        ticketGeneratingPane.setHgap(10);
        return ticketGeneratingPane;
    }

    /**
     * Sets triggers for all buttons included on the user interface and action events for each button associated to the controller.
     * @param controller
     */
    public void setTriggers(BusController controller) {

        //Button for adding a stop
        btnAddStop.setOnAction(event -> {
            controller.doAddStop();
        });

        btnAddRoute.setOnAction(event -> {
            controller.doAddRoute();
        });

        btnRemoveStop.setOnAction(event -> {
            controller.doRemoveStop();
        });

        btnRemoveRoute.setOnAction(event -> {
            controller.doRemoveRoute();
        });

        btnReplaceRoute.setOnAction(event -> {
            controller.doReplaceRoute();
        });

        btnCalculateShortestPath.setOnAction(event -> {
            lblShortestPathDistance.setText(controller.doCalculateDistanceShortestPath().toString());
            lblShortestPathDuration.setText(controller.doCalculateDurationShortestPath().toString());
        });

        //Button for exporting graph
        btnExportGraph.setOnAction(event -> {
            try {
                controller.doExportGraph(model.getPaths());
            } catch (IOException e) {
                throw new BusModelException(e.getMessage());
            }
        });

        //Button for selecting the Demo graph
        btnSelectDemo.setOnAction(event ->{
            controller.doSetReader(new ReadDemoStrategy());
            currentBackGroundImage = controller.doChangeBackgroundImage(0);
            updateGraph();
        });

        //Button for selecting the IBERIA graph
        btnSelectIberia.setOnAction(event ->{
            controller.doSetReader(new ReadIberiaStrategy());
            currentBackGroundImage = controller.doChangeBackgroundImage(0);
            updateGraph();
        });

        //Button for selecting the Europe graph
        btnSelectEurope.setOnAction(event ->{
            controller.doSetReader(new ReadEuropeStrategy());
            currentBackGroundImage = controller.doChangeBackgroundImage(0);
            updateGraph();
        });

        //Button for selecting the Exported graph
        btnSelectExported.setOnAction(event -> {
            controller.doSetReader(new ReadExportedStrategy());
            currentBackGroundImage = controller.doChangeBackgroundImage(0);
            updateGraph();
        });

        btnSelectDarkView.setOnAction(event ->{
            currentBackGroundImage = controller.doChangeBackgroundImage(0);
            updateBackgroundImage();
            update(model, null);
        });

        btnSelectMapView.setOnAction(event -> {
            currentBackGroundImage = controller.doChangeBackgroundImage(1);
            updateBackgroundImage();
            update(model, null);
        });

        btnSelectSatelliteView.setOnAction(event -> {
            currentBackGroundImage = controller.doChangeBackgroundImage(2);
            updateBackgroundImage();
            update(model, null);
        });

        btnSelectTerrainView.setOnAction(event -> {
            currentBackGroundImage = controller.doChangeBackgroundImage(3);
            updateBackgroundImage();
            update(model, null);
        });

        btnGenerateOneWayTicketBasic.setOnAction(event -> {
            controller.doGenerateTicket(new OneWayTicketFactory(), "basic");;
        });

        btnGenerateOneWayTicketDetailed.setOnAction(event -> {
            controller.doGenerateTicket(new OneWayTicketFactory(), "detailed");;
        });

        btnGenerateFullTicketBasic.setOnAction(event -> {
            controller.doGenerateTicket(new FullTicketFactory(), "basic");
        });

        btnGenerateFullTicketDetailed.setOnAction(event -> {
            controller.doGenerateTicket(new FullTicketFactory(), "detailed");
        });

        btnUndo.setOnAction(event -> {
            controller.doUndo();
            updateGraph();
        });
    }

    /**
     * Forces an update on the graph.
     */
    public void updateGraph() {

        String customProp = "edge.label = true" + "edge-label"+ "\n" + "edge.arrow = true" + "\n" + "vertex.allow-user-move = true" + "\n" + "vertex.radius = 8 ";
        SmartGraphProperties properties = new SmartGraphProperties(customProp);

        this.graphPanel = new SmartGraphPanel<>(model.getMap(), properties);
        this.graphPanel.setAutomaticLayout(false);

        this.scene = new Scene(createStackPane(), 1680, 850);
        this.stage.setScene(this.scene);
        this.stage.show();

        updateBackgroundImage();

        graphPanel.init();
        System.out.println(model.getMap());
        graphPanel.updateAndWait();

        setVertexPosition();

        update(model, null);
    }

    /**
     * Updates the background image of the graph pane.
     */
    private void updateBackgroundImage(){

        BackgroundImage bImg = new BackgroundImage(currentBackGroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);

        graphPanel.backgroundProperty().set(bGround);
    }

    /**
     * Sets positions to all vertices on the graph.
     */
    public void setVertexPosition(){
        for (Vertex<Stop> h : model.getMap().vertices()) {
            graphPanel.setVertexPosition(h, h.element().getX(), h.element().getY());
        }
    }

    /**
     * Iterates through all vertices and edges, and sets their style and respective labels.
     */
    private void setStyles() {
        for (Vertex<Stop> v : model.getMap().vertices()) {
            graphPanel.getStylableVertex(v).setStyleClass("busNetworkVertex");
            graphPanel.getStylableLabel(v).setStyleClass("busNetworkVertexLabel");
        }
        for (Edge<Route,Stop> e : model.getMap().edges()) {
            graphPanel.getStylableEdge(e).setStyleClass("busNetworkEdge");
            graphPanel.getStylableLabel(e).setStyleClass("busNetworkEdgeLabel");
        }
    }

    @Override
    public void update(Observable subject, Object arg) {
            if (subject == model) {
            /* update graph panel */
            System.out.println("update");
            graphPanel.update();

            lblStopCount.setText("" + model.stopNumber());
            lblRouteCount.setText("" + model.routesNumber());
            createCentralityPane();
        }
    }

    /**
     * Sets a message error on the error label depending on what error message it gets.
     *
     * @param msg
     */
    public void displayError(String msg) {
        this.lblError.setText(msg);
    }

    /**
     * Clears error label.
     */
    public void clearError() {
        lblError.setText("");
    }

    /**
     * Clears text fields.
     */
    public void clearControls() {
        txtStopName.clear();
        txtStopCode.clear();
        txtStopLatitude.clear();
        txtStopLongitude.clear();
        txtStopXCoord.clear();
        txtStopYCoord.clear();
        txtRouteCodeStart.clear();
        txtRouteCodeEnd.clear();
        txtRouteDuration.clear();
        txtRouteDistance.clear();
    }


    public String getStopName() {
        return txtStopName.getText();
    }

    public String getStopCode() {
        return txtStopCode.getText();
    }

    public String getLatitude() {
        return txtStopLatitude.getText();
    }

    public String getLongitude() {
        return txtStopLongitude.getText();
    }

    public String getXCoord() {
        return txtStopXCoord.getText();
    }

    public String getYCoord() {
        return txtStopYCoord.getText();
    }

    public String getRouteStartCode(){
        return txtRouteCodeStart.getText();
    }

    public String getRouteEndCode(){
        return txtRouteCodeEnd.getText();
    }

    public String getDuration(){
        return txtRouteDuration.getText();
    }

    public String getDistance(){
        return txtRouteDistance.getText();
    }

    public String getStartingCode(){
        return txtStopCodeStartForShortestPath.getText();
    }

    public String getEndingCode(){
        return txtStopCodeEndForShortestPath.getText();
    }
}
