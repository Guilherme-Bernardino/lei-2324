package view;

import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;
import controller.UniversityNetworkController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Person;
import model.Relationship;
import model.UniversityNetwork;
import observerpattern.Observable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brunomnsilva
 */
public class UniversityNetworkView extends BorderPane implements UniversityNetworkUI {

    private UniversityNetwork model;
    private SmartGraphPanel<Person, Relationship> graphPanel;

    private Button btAddPerson;
    private Button btRemovePerson;
    private Button btAddGroupRelationship;
    private Button btAddClassRelationship;
    private Button btRemoveRelationship;
    private Label lblError;
    private ComboBox<String> cbRoles;
    private ComboBox<String> cbPersonId1;
    private ComboBox<String> cbPersonId2;
    private TextField txtPersonId;
    private TextField txtPersonName;
    private TextField txtRelationshipDescription;
    private Label lblPeopleCount;
    private Label lblMostPopular;

    public UniversityNetworkView(UniversityNetwork model) {
        this.model = model;

        createLayout();
    }

    @Override
    public void update(Observable subject, Object arg) {
        if(subject == model) {
            /* update graph panel */
            graphPanel.update();

            /* update ID comboboxes */
            cbPersonId1.getItems().clear();
            cbPersonId2.getItems().clear();
            List<Person> people = new ArrayList<>( model.getPeople() );
            people.sort((h1, h2) -> h1.getId() - h2.getId());
            for (Person p : people) {
                cbPersonId1.getItems().add( String.valueOf(p.getId()) );
                cbPersonId2.getItems().add( String.valueOf(p.getId()) );
            }

            /* TODO Statistics */
            lblPeopleCount.setText(""+model.getPeople().size());
            lblMostPopular.setText(model.getMostPopular().toString());
        }
    }

    @Override
    public void setTriggers(UniversityNetworkController controller) {
        btAddPerson.setOnAction( event -> {
            controller.doAddPerson();
        });

        btRemovePerson.setOnAction( event -> {
            controller.doRemovePerson();
        });

        btAddGroupRelationship.setOnAction(event -> {
            controller.doAddGroupRelationShip();
        });

        btAddClassRelationship.setOnAction(event -> {
            controller.doAddClassRelationShip();
        });

        btRemoveRelationship.setOnAction(event -> {
            Alert alert = makeConfirmationDialog("Delete Relationship", "Are you sure?");
            alert.showAndWait().ifPresent(response -> {

                if (response.getButtonData() == ButtonBar.ButtonData.YES) {
                    controller.doRemoveRelationShip();
                }

            });
        });
    }

    private Alert makeConfirmationDialog(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        return alert;
    }

    @Override
    public String getNamePersonAdd() {
        return txtPersonName.getText();
    }

    @Override
    public String getIdPersonAdd() {
        return txtPersonId.getText();
    }

    @Override
    public String getRolePersonAdd() {
        return cbRoles.getSelectionModel().getSelectedItem();
    }

    @Override
    public String getRelationshipFirstPersonId() {
        return cbPersonId1.getSelectionModel().getSelectedItem();
    }

    @Override
    public String getRelationshipSecondPersonId() {
        return cbPersonId2.getSelectionModel().getSelectedItem();
    }

    @Override
    public String getRelationShipDescriptionAdd() {
        return txtRelationshipDescription.getText();
    }

    @Override
    public void displayError(String msg) {
        lblError.setText(msg);
    }

    @Override
    public void clearError() {
        lblError.setText("");
    }

    @Override
    public void clearControls() {
        txtPersonId.clear();
        txtPersonName.clear();
        txtRelationshipDescription.clear();
    }

    private void createLayout() {
        /* CENTER PANEL */
        graphPanel = new SmartGraphPanel<>(model.getNetwork(), new SmartCircularSortedPlacementStrategy());
        //graphPanel.setAutomaticLayout(true);
        setCenter(graphPanel);

        /* RIGHT PANEL */
        setRight(createSidePanel());

        /* BOTTOM */
        lblError = new Label("Ready");
        HBox bottom = new HBox(lblError);
        bottom.setPadding(new Insets(10, 10, 10, 10));
        setBottom(bottom);

        /* bind double click on vertex */
        graphPanel.setVertexDoubleClickAction((SmartGraphVertex<Person> graphVertex) -> {
            //Fill the person id textfield with the selected person's id
            txtPersonId.setText( String.valueOf( graphVertex.getUnderlyingVertex().element().getId()) );
        });
    }

    private VBox createSidePanel() {

        /* ADD PERSON CONTROLS */
        GridPane personPane = new GridPane();
        personPane.setAlignment(Pos.CENTER);
        personPane.setHgap(5);
        personPane.setVgap(5);
        personPane.setPadding(new Insets(10,10,10,10)); // set top, right, bottom, left

        txtPersonId = new TextField("");
        txtPersonName = new TextField("");
        cbRoles = new ComboBox<>();
        cbRoles.getItems().addAll("STUDENT", "TEACHER");
        cbRoles.setMaxWidth(Double.MAX_VALUE); //hack to hgrow

        txtPersonId.setPrefColumnCount(10);
        txtPersonName.setPrefColumnCount(10);

        Label labelId = new Label("Id: ");
        Label labelName = new Label("Name: ");
        Label labelRole = new Label("Role: ");

        personPane.add(labelId, 0, 1);
        personPane.add(txtPersonId, 1, 1);
        personPane.add(labelName, 0, 2);
        personPane.add(txtPersonName, 1, 2);
        personPane.add(labelRole, 0, 3);
        personPane.add(cbRoles, 1, 3);

        btAddPerson = new Button("Add");
        personPane.add(btAddPerson, 1, 4);

        btRemovePerson = new Button("Remove");
        btRemovePerson.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        personPane.add(btRemovePerson, 1, 5);

        /* Relationship Controls */
        GridPane relationPane = new GridPane();
        relationPane.setAlignment(Pos.CENTER);
        relationPane.setHgap(5);
        relationPane.setVgap(5);
        relationPane.setPadding(new Insets(10,10,10,10)); // set top, right, bottom, left

        txtRelationshipDescription = new TextField("");
        cbPersonId1 = new ComboBox<>();
        cbPersonId1.setMaxWidth(Double.MAX_VALUE); //hack to hgrow
        cbPersonId2 = new ComboBox<>();
        cbPersonId2.setMaxWidth(Double.MAX_VALUE); //hack to hgrow

        txtPersonId.setPrefColumnCount(12);
        txtPersonName.setPrefColumnCount(12);

        Label labelId1 = new Label("Id 1: ");
        Label labelId2 = new Label("Id 2: ");
        Label labelDesc = new Label("Desc.: ");

        relationPane.add(labelId1, 0, 1);
        relationPane.add(cbPersonId1, 1, 1);
        relationPane.add(labelId2, 0, 2);
        relationPane.add(cbPersonId2, 1, 2);
        relationPane.add(labelDesc, 0, 3);
        relationPane.add(txtRelationshipDescription, 1, 3);

        btAddClassRelationship = new Button("Add Class");
        btAddGroupRelationship = new Button("Add Group");
        HBox addRel = new HBox(btAddClassRelationship, btAddGroupRelationship);
        btRemoveRelationship = new Button("Remove");
        btRemoveRelationship.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        relationPane.add(addRel, 1, 4);
        relationPane.add(btRemoveRelationship, 1, 5);

        /* STATS */
        Label labelCount = new Label("Person Count: ");
        labelCount.setStyle("-fx-font-weight: bold;");
        Label labelPopular = new Label("Most Popular Person: ");
        labelPopular.setStyle("-fx-font-weight: bold;");
        lblMostPopular = new Label("TODO");
        lblPeopleCount = new Label("TODO");
        VBox statsPane = new VBox(labelCount, lblPeopleCount, labelPopular, lblMostPopular);
        statsPane.setSpacing(10);

        /* COMPOSE */

        Label pHelp = new Label("You can double click on a person to select its ID.");
        pHelp.setStyle("-fx-font-size: 10px;");
        pHelp.setWrapText(true);
        pHelp.setMaxWidth(200);

        Label rHelp = new Label("Class relationships need a Teacher ID (Id 1) and a Student ID (Id 2). Group relationships are only between students.");
        rHelp.setStyle("-fx-font-size: 10px;");
        rHelp.setWrapText(true);
        rHelp.setMaxWidth(200);

        VBox panel = new VBox(new Label("Manage People"),
                personPane,
                pHelp,
                new Separator(),
                new Label("Manage Relationships"),
                relationPane,
                rHelp,
                new Separator(),
                new Label("Statistics"),
                statsPane);
        panel.setPadding(new Insets(10, 10, 10, 10));
        panel.setSpacing(10);

        return panel;
    }

    public void initGraphDisplay() {
        graphPanel.init();
        update(model, null);
    }


}
