package pt.pa.view;

import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartGraphProperties;
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.pa.controller.GraphController;
import pt.pa.datasets_handler.Path;
import pt.pa.datasets_handler.Reader;
import pt.pa.graph.Edge;
import pt.pa.graph.Graph;
import pt.pa.graph.GraphAdjacencyList;
import pt.pa.graph.Vertex;
import pt.pa.model.Route;
import pt.pa.model.Stop;
import pt.pa.model.busModelInterface;
import pt.pa.observerpattern.Observable;

import java.util.List;


public class busView extends BorderPane implements busViewUI {
    private Pane presentationPane;

    private  Button btnMapChange;

    private busModelInterface model;
    private SmartGraphPanel<Stop, Route> graphPanel;

    private Label lblError;

    private Button btAddStop;
    private Button btRemoveStop;
    private Button btAddRoute;
    private Button btRemoveRoute;
    private Label lblStopCount;
    private Button buttonUndo;
    private Button buttonExport;

    private Button btForceUpdate;

    private ComboBox<String> cbSourceStop;
    private ComboBox<String> cbDestStop;

    private TextField txtStopCode;
    private TextField txtStopName;
    private TextField txtStopPop;
    private TextField txtStopLongitude;
    private TextField txtStopLatitude;
    private TextField txtStopX;
    private TextField txtStopY;
    private TextField txtRouteDistance;
    private TextField txtRouteDuration;
    private Button btnNewNetwork;
    private Button btnAddStop;

    private Label lblRouteCount;
    private Label lblSubRouteCount;
    private Button btTop7Stops;
    private Popup top7Popup;
    private Button btCentralStops;
    private Popup centralStopsPopup;
    private Button btImportData;
    private ComboBox<String> cbPath;
    private Button btShortestRoute;
    private Button btMaxRouteDistance;
    private Popup popupNRoutesFromH;
    private ListView listNRoutesFromH;
    private Label lblListViewTitle;
    private BarChart top7BarChart;


    public busView(busModelInterface model) {
        this.model = model;
        createLayout();

    }

    public void update(Observable subject, Object arg) {
        if (subject == model) {
            /* update graph panel */
            System.out.println("update");
            graphPanel.update();
            setCoords();
        }
    }

    public void initGraphDisplay() {
        graphPanel.init();
        System.out.println(model.getMap());
        graphPanel.updateAndWait();
    }

    public void setTriggers(GraphController controller) {
        btAddStop.setOnAction(event -> controller.doAddStop());
        btRemoveStop.setOnAction(event -> controller.doRemoveStop());
        btAddRoute.setOnAction(event -> controller.doAddRoute());
        btRemoveRoute.setOnAction(event -> controller.doRemoveRoute());
        buttonUndo.setOnAction(event -> controller.undo());
        btForceUpdate.setOnAction(event -> {
            resetStyles();
            graphPanel.updateAndWait();
            setCoords();
        });

        btTop7Stops.setOnAction(event -> {
            if (!top7Popup.isShowing()) {
                top7BarChart.getData().add(controller.getTop7Data());
                top7Popup.show(graphPanel.getScene().getWindow());
            } else {
                top7Popup.hide();
                top7BarChart.getData().clear();
            }
        });

        btCentralStops.setOnAction(event -> {
            if (!centralStopsPopup.isShowing()) {
                VBox centralPopupLayout = new VBox();
                Label lblTitle = new Label("List of Hubs Centrality");
                lblTitle.setStyle("-fx-font-weight: bold;");
                centralPopupLayout.getChildren().add(0, lblTitle);
                centralPopupLayout.getChildren().add(1, controller.getCentralStops());
                centralStopsPopup.getContent().add(centralPopupLayout);
                centralStopsPopup.show(graphPanel.getScene().getWindow());
            } else
                centralStopsPopup.hide();
        });

//        cbPath.valueProperty().addListener((ov, oldVal, newVal) -> {
//            cbRoute.getItems().clear();
//            cbRoute.getItems().addAll(new FileManager().getFiles("dataset/" + newVal));
//        });
//
//        btImportData.setOnAction(event -> {
//            controller.loadDataFromDataset();
//            graphPanel.updateAndWait();
//            setCoords();
//        });
//
//        btShortestRoute.setOnAction(event -> {
//            clearError();
//            resetStyles();
//            DijkstraResult<Vertex<Stop>> result = controller.getMinDistance();
//            if (result == null || result.getCost() == -1) return;
//            List<Vertex<Stop>> vertexList = result.getPath();
//            for (int i = 0; i < vertexList.size(); i++) {
//                graphPanel.getStylableVertex(vertexList.get(i)).addStyleClass("myVertex");
//                if (i + 1 < vertexList.size()) {
//                    graphPanel.getStylableEdge(model.findRoute(vertexList.get(i).element().getStopName(),
//                            vertexList.get(i + 1).element().getStopName())).setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
//                }
//                txtRouteDistance.setText(String.format("%s", result.getCost()));
//            }
//        });
//
//        btMaxRouteDistance.setOnAction(event -> {
//            clearError();
//            List<Vertex<Stop>> vertexList = controller.pathDistance();
//            if (vertexList == null || vertexList.isEmpty()) {
//                displayError("No hubs found with that route distance");
//                return;
//            }            ;
//
//            if (!popupNRoutesFromH.isShowing()) {
//                resetStyles();
//                VBox NRoutesFromHPopupLayout = new VBox();
//                lblListViewTitle.setText("List of Hubs with " + getDistanceAdd() + " of distance from Hub " + getRouteSourceStop());
//                lblListViewTitle.setStyle("-fx-font-weight: bold;");
//
//                for (int i = 0; i < vertexList.size(); i++) {
//                    graphPanel.getStylableVertex(vertexList.get(i)).addStyleClass("myVertex");
//                    listNRoutesFromH.getItems().add(vertexList.get(i).element().getStopName());
//                }
//
//                NRoutesFromHPopupLayout.getChildren().add(0, lblListViewTitle);
//                NRoutesFromHPopupLayout.getChildren().add(1, listNRoutesFromH);
//                popupNRoutesFromH.getContent().add(NRoutesFromHPopupLayout);
//                popupNRoutesFromH.setX(800);
//                popupNRoutesFromH.setY(300);
//                popupNRoutesFromH.show(graphPanel.getScene().getWindow());
//            } else {
//                popupNRoutesFromH.hide();
//            }
//        });

//        buttonExport.setOnAction(event -> controller.exportRoutes());
    }

    private void resetStyles() {
        for (Vertex<Stop> hub : model.getMap().vertices()) {
            graphPanel.getStylableVertex(hub).removeStyleClass("myVertex");
        }
        for (Edge<Route, Stop> edge : model.getMap().edges()) {
            graphPanel.getStylableEdge(edge).setStyle("");
        }
    }

    private void createLayout() {
        graphPanel = new SmartGraphPanel<>(model.getMap(), new SmartCircularSortedPlacementStrategy());
        graphPanel.setAutomaticLayout(false);
        setCenter(graphPanel);
        VBox sidePanel = createSidePanel();
        sidePanel.setPrefSize(300, 800);
        setLeft(sidePanel);

        for (Vertex<Stop> h : model.getMap().vertices()) {
            graphPanel.setVertexPosition(h, h.element().getX(), h.element().getY());
        }

        graphPanel.setVertexDoubleClickAction((SmartGraphVertex<Stop> graphVertex) -> {
            resetStyles();
            System.out.println("Vertex contains element: " + graphVertex.getUnderlyingVertex().element());
            if (!graphVertex.removeStyleClass("myVertex")) {
                graphVertex.addStyleClass("myVertex");
            }
            txtStopName.setText(String.valueOf(graphVertex.getUnderlyingVertex().element().getStopName()));
        });

        graphPanel.setEdgeDoubleClickAction(graphEdge -> {
            resetStyles();
            System.out.println("Edge contains element: " + graphEdge.getUnderlyingEdge().element());
            graphEdge.setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
            int val = 0;
            //gets the vertices that connect to an edge and select them on the cb
            for (Vertex<Stop> hub : graphEdge.getUnderlyingEdge().vertices()) {
                if (val == 0) cbSourceStop.getSelectionModel().select(hub.element().getStopName());
                if (val == 1) cbDestStop.getSelectionModel().select(hub.element().getStopName());
                val++;
            }
        });
    }

    public SmartGraphDemoContainer createGraph() {

        GridPane gridPaneContents = new GridPane();
        HBox hbOptions = new HBox(10);
        BorderPane root = new BorderPane();
        btnAddStop = new Button("Add");

        btnNewNetwork = new Button("Add Bus Network");
        hbOptions.getChildren().add(btnNewNetwork);
        root.setTop(btnAddStop);
        Reader reader = new Reader();
        Graph<Stop, Route> graph = new GraphAdjacencyList<>();
        reader.readStops(graph, Path.DEMO);
        reader.readRoutes(graph, Path.DEMO);
        reader.readXY(graph, Path.DEMO);
        List<Image> images = reader.readAllImages(Path.DEMO);

        String customProp = "edge.label = true" + "\n" + "edge.arrow = false";
        SmartGraphProperties properties = new SmartGraphProperties(customProp);

        SmartGraphPanel<Stop, Route> graphView = new SmartGraphPanel<>(graph, properties);
        SmartGraphDemoContainer graphDemoContainer = new SmartGraphDemoContainer(graphView);

        graphDemoContainer.setTop(btnAddStop);


        BackgroundImage bImg = new BackgroundImage(images.get(0),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);

        //graphView.init();
        for (Vertex<Stop> vertex : graph.vertices()) {
            graphView.setVertexPosition(vertex, vertex.element().getX(), vertex.element().getY());
        }


        graphView.backgroundProperty().set(bGround);

        return graphDemoContainer;
    }

    private void setTriggerBtnNewNetwork() {
        btnNewNetwork.setOnAction(actionEvent -> {
            presentationPane.getChildren().removeAll(presentationPane.getChildren());
            Button importDistance = new Button ("Import Routes Distance");
            Button importDuration = new Button ("Import Routes Duration");
            Button importStops = new Button ("Import Stops");
            Button importXY = new Button ("Import XY");

            GridPane gPane = new GridPane();
            gPane.add(importDistance, 0, 0);
            gPane.add(importDuration, 1, 0);
            gPane.add(importStops, 2, 0);
            gPane.add(importXY, 3, 0);

            presentationPane.getChildren().add(gPane);

            importDistance.setOnAction(actionEvent1 -> {

            });


        });

    }



//    private void createLayout() {
//
//        graphPanel = new SmartGraphPanel<>(model.getNetwork(), new SmartCircularSortedPlacementStrategy());
//        graphPanel.setAutomaticLayout(false);
//        setCenter(graphPanel);
//
//        VBox sidePanel = createSidePanel();
//        sidePanel.setPrefSize(300, 800);
//        setLeft(sidePanel);
//
//        for (Vertex<Stop> h : model.getMap().vertices()) {
//            graphPanel.setVertexPosition(h, h.element().getX(), h.element().getY());
//        }
//
//
//        GridPane gridPaneContents = new GridPane();
//        HBox hbOptions = new HBox(10);
//
//        btnNewNetwork = new Button("Add Bus Network");
//        hbOptions.getChildren().add(btnNewNetwork);
//        Reader reader = new Reader();
//        Graph<Stop, Route> graph = new GraphAdjacencyList<>();
//        reader.readStops(graph, Path.DEMO);
//        reader.readRoutes(graph, Path.DEMO);
//        reader.readXY(graph, Path.DEMO);
//        List<Image> images = reader.readAllImages(Path.DEMO);
//
//        String customProp = "edge.label = true" + "\n" + "edge.arrow = false";
//        SmartGraphProperties properties = new SmartGraphProperties(customProp);
//
//        SmartGraphPanel<Stop, Route> graphView = new SmartGraphPanel<>(graph, properties);
//        Scene scene = new Scene(new SmartGraphDemoContainer(graphView), 1024, 768);
//
//        Stage stage = new Stage(StageStyle.DECORATED);
//        stage.setTitle("Projeto PA 2223 - Bus Network");
//        stage.setMinHeight(768);
//        stage.setMinWidth(1200);
//        stage.setResizable(false);
//        stage.setScene(scene);
//        stage.show();
//
//        BackgroundImage bImg = new BackgroundImage(images.get(0),
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        Background bGround = new Background(bImg);
//
//        graphView.init();
//        for (Vertex<Stop> vertex : graph.vertices()) {
//            graphView.setVertexPosition(vertex, vertex.element().getX(), vertex.element().getY());
//        }
//
//
//
//
//        graphView.backgroundProperty().set(bGround);
//
//
//    }

    private VBox createSidePanel() {
        GridPane actionsPane = createActionsPane();
        GridPane hubPane = createHubPane();
        GridPane routePane = createRoutePane();

        /* ERROR */
        lblError = new Label("");
        lblError.setStyle("-fx-background-color: red;");
        lblError.setWrapText(true);
        lblError.setMaxWidth(275);

        btForceUpdate = new Button("Force Update");

        /* STATS */
        Label labelHubCount = new Label("Number of Stops: ");
        labelHubCount.setStyle("-fx-font-weight: bold;");
        Label labelRouteCount = new Label("Number of Routes: ");
        labelRouteCount.setStyle("-fx-font-weight: bold;");
        Label labelSubRoutesCount = new Label("Number of Sub-Routes: ");
        labelSubRoutesCount.setStyle("-fx-font-weight: bold;");
        lblStopCount = new Label();
        lblRouteCount = new Label();
        lblSubRouteCount = new Label();
        updateLabelsInfo();
        btTop7Stops = new Button("Top 5 Stops");
        btCentralStops = new Button("Stops Centrality");


        /*Create Popup for bar chart*/
        top7Popup = new Popup();
        top7Popup.setWidth(100);
        top7Popup.setHeight(200);
        HBox popupLayout = new HBox();
        popupLayout.getAlignment();
        popupLayout.setStyle("-fx-border-color: black");
        popupLayout.setStyle("-fx-background-color: white");
        top7Popup.getContent().addAll(popupLayout);

        /*Create Barchart*/
        CategoryAxis hubAxis = new CategoryAxis();
        hubAxis.setLabel("Hubs");

        NumberAxis numRoutesAxis = new NumberAxis();
        numRoutesAxis.setLabel("Number of Adjacent Hubs");

        top7BarChart = new BarChart(hubAxis, numRoutesAxis);

        popupLayout.getChildren().add(top7BarChart);

        /*Create Popup for List of Hubs centrality*/
        centralStopsPopup = new Popup();

        /*Create Popup for List of Hubs with specific distance from sourceHub */
        popupNRoutesFromH = new Popup();
        lblListViewTitle = new Label("");
        listNRoutesFromH = new ListView();

        HBox countsHubPane = new HBox(labelHubCount, lblStopCount);
        HBox countsRoutePane = new HBox(labelRouteCount, lblRouteCount);
        HBox countsSubRoutePane = new HBox(labelSubRoutesCount, lblSubRouteCount);
        VBox statsPane = new VBox(countsHubPane, countsRoutePane, countsSubRoutePane, btCentralStops, btTop7Stops);
        statsPane.setSpacing(10);

        /* COMPOSE */
        VBox panel = new VBox(
                new Label("Actions"),
                actionsPane,
                new Separator(),
                new Label("Manage Hubs"),
                hubPane,
                new Separator(),
                new Label("Manage Routes"),
                routePane,
                new Separator(),
                new Label("Statistics"),
                statsPane,
                new Separator(),
                btForceUpdate,
                lblError);
        panel.setPadding(new Insets(10, 10, 10, 10));
        panel.setSpacing(10);

        return panel;
    }

    private GridPane createRoutePane() {
        /* Route Controls */
        GridPane routePane = new GridPane();
        routePane.setAlignment(Pos.CENTER);
        routePane.setHgap(5);
        routePane.setVgap(5);
        routePane.setPadding(new Insets(10, 10, 10, 10)); // set top, right, bottom, left

        txtRouteDistance = new TextField("");
        cbSourceStop = new ComboBox<>();
        cbSourceStop.setMaxWidth(Double.MAX_VALUE); //hack to hgrow
        cbDestStop = new ComboBox<>();
        cbDestStop.setMaxWidth(Double.MAX_VALUE); //hack to hgrow

        txtStopName.setPrefColumnCount(12);

        Label labelSourceHub = new Label("Source Hub: ");
        Label labelDestHub = new Label("Destination Hub: ");
        Label labelDistance = new Label("Distance: ");

        routePane.add(labelSourceHub, 0, 1);
        routePane.add(cbSourceStop, 1, 1);
        routePane.add(labelDestHub, 0, 2);
        routePane.add(cbDestStop, 1, 2);
        routePane.add(labelDistance, 0, 3);
        routePane.add(txtRouteDistance, 1, 3);

        btAddRoute = new Button("Add");
        btRemoveRoute = new Button("Remove");
        btRemoveRoute.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        btShortestRoute = new Button("Shortest Route");
        btMaxRouteDistance = new Button("Max Route Dist");
        routePane.add(btAddRoute, 0, 4);
        routePane.add(btRemoveRoute, 1, 4);
        routePane.add(btMaxRouteDistance, 0, 5);
        routePane.add(btShortestRoute, 1, 5);
        return routePane;
    }

    private void updateLabelsInfo() {
        lblStopCount.setText(String.format("%d", model.getMap().vertices().size()));
        lblRouteCount.setText(String.format("%d", model.getMap().edges().size()));
        lblSubRouteCount.setText("0");
    }

    private GridPane createHubPane() {
        GridPane hubPane = new GridPane();
        hubPane.setAlignment(Pos.CENTER);
        hubPane.setHgap(5);
        hubPane.setVgap(5);
        hubPane.setPadding(new Insets(10, 10, 10, 10)); // set top, right, bottom, left

        txtStopName = new TextField("");
        txtStopPop = new TextField("");
        txtStopX = new TextField("");
        txtStopY = new TextField("");

        txtStopName.setPrefColumnCount(10);

        Label labelName = new Label("Name: ");
        Label labelPopulation = new Label("Population: ");
        Label labelX = new Label("X coord: ");
        Label labelY = new Label("Y coord: ");

        hubPane.add(labelName, 0, 0);
        hubPane.add(txtStopName, 1, 0);
        hubPane.add(labelPopulation, 0, 1);
        hubPane.add(txtStopPop, 1, 1);
        hubPane.add(labelX, 0, 2);
        hubPane.add(txtStopX, 1, 2);
        hubPane.add(labelY, 0, 3);
        hubPane.add(txtStopY, 1, 3);

        btAddStop = new Button("Add");
        hubPane.add(btAddStop, 0, 4);

        btRemoveStop = new Button("Remove");
        btRemoveStop.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        hubPane.add(btRemoveStop, 1, 4);
        return hubPane;
    }

    private GridPane createActionsPane() {
        /* TOP ACTIONS UNDO/EXPORT/IMPORT */
        GridPane actionsPane = new GridPane();
        actionsPane.setAlignment(Pos.BASELINE_LEFT);
        buttonUndo = new Button("Undo");
        buttonExport = new Button("Export");

        /*IMPORT DATA*/
        btImportData = new Button("Import");
        cbPath = new ComboBox<>();
        cbPath.setMaxWidth(80);
        cbPath.setPromptText("Path");
        //cbPath.getItems().addAll(new FileManager().getFiles("dataset/"));

        actionsPane.add(buttonUndo, 0, 0);
        actionsPane.add(buttonExport, 1, 0);
        actionsPane.add(btImportData, 0, 1);
        actionsPane.add(cbPath, 1, 1);

        return actionsPane;
    }


    public String getCodeStopAdd()  {return this.txtStopCode.getText();}

    public String getNameStopAdd() {
        return this.txtStopName.getText();
    }


    public String getPopStopAdd() {
        return this.txtStopPop.getText();
    }

    public String getLongitudeStopAdd() {
        return this.txtStopLongitude.getText();
    }

    public String getLatitudeStopAdd() {
        return this.txtStopLatitude.getText();
    }

    public String getXStopAdd() {
        return this.txtStopX.getText();
    }

    public String getYStopAdd() {
        return this.txtStopY.getText();
    }


    public String getRouteSourceStop(){
        return this.cbSourceStop.getSelectionModel().getSelectedItem();
    }


    public String getRouteDestStop() {

        return this.cbDestStop.getSelectionModel().getSelectedItem();
    }


    public String getDistanceAdd() {

        return this.txtRouteDistance.getText();
    }

    public String getDurationAdd() {

        return this.txtRouteDuration.getText();
    }



    public String getPathSelection() {
        return this.cbPath.getSelectionModel().getSelectedItem();
    }

    @Override
    public String getRouteSelection() {
        return null;
    }


    @Override
    public Double getGraphWidth() {
        return null;
    }

    @Override
    public Double getGraphHeight() {
        return null;
    }

    @Override
    public void displayError(String var1) {

    }

    @Override
    public void clearError() {

    }


    public void setCoords() {
        for (Vertex<Stop> s : model.getVertices()) {
            System.out.println("vertex: " + s);
            graphPanel.setVertexPosition(s, s.element().getX(), s.element().getY());
            System.out.println("X pos: " + graphPanel.getVertexPositionX(s));
        }
    }



        public void clearControls() {
            this.txtRouteDistance.clear();
            this.txtRouteDuration.clear();
            this.txtStopY.clear();
            this.txtStopX.clear();
            this.txtStopPop.clear();
            this.txtStopName.clear();
            this.txtRouteDistance.clear();
        }

        
}


















/*

private void setTriggerBtnAddRoute() {
    btnAddRoute.setOnAction(actionEvent -> {
        try{
            presentationPane.getChildren().removeAll(presentationPane.getChildren());
            Label labelHubContents = new Label("Route List");
            Label labelHub_1 = new Label("Hub 1");
            Label labelHub_2 = new Label("Hub 2");
            Label labelRoute = new Label("Route");
            TextField txHub_1 = new TextField();
            TextField txHub_2 = new TextField();
            TextField txRoute = new TextField();
            labelHubContents.setStyle("-fx-font-weight: bold");
            hubListView = new ListView<>();
            hubListView.setMinHeight(720);
            GridPane gPane = new GridPane();
            GridPane gPane_2 = new GridPane();
            Button btnAdd = new Button("Add");
            gPane.add(labelHubContents, 0,0);
            gPane.add(hubListView, 0, 1);
            gPane_2.add(labelHub_1,1, 0);
            gPane_2.add(txHub_1,1,1);
            gPane_2.add(labelHub_2,1, 2);
            gPane_2.add(txHub_2,1,3);
            gPane_2.add(labelRoute,1, 4);
            gPane_2.add(txRoute,1,5);
            gPane_2.add(btnAdd, 1,6);
            gPane.add(gPane_2, 1,1);

            presentationPane.getChildren().add(gPane);


        }
    });
}

*/
