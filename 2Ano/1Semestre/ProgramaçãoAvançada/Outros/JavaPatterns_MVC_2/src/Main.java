import controller.UniversityNetworkController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Person;
import model.UniversityNetwork;
import view.UniversityNetworkView;

/**
 * @author brunomnsilva
 */
public class Main extends Application {

    @Override
    public void start(Stage ignored) throws Exception {

        UniversityNetwork model = createTestModel();
        UniversityNetworkView view = new UniversityNetworkView(model);
        UniversityNetworkController controller = new UniversityNetworkController(view, model);

        Scene scene = new Scene(view, 1200, 800);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("University Network");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        view.initGraphDisplay();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private UniversityNetwork createTestModel() {
        UniversityNetwork estNet = new UniversityNetwork();
        estNet.addPerson(new Person(135, "Rodrigo", Person.PersonRole.STUDENT));
        estNet.addPerson(new Person(131, "Pedro", Person.PersonRole.STUDENT));
        estNet.addPerson(new Person(2, "Ana", Person.PersonRole.STUDENT));
        estNet.addPerson(new Person(231, "Rita", Person.PersonRole.STUDENT));
        estNet.addPerson(new Person(233, "Zé", Person.PersonRole.STUDENT));
        estNet.addPerson(new Person(235, "Joao", Person.PersonRole.STUDENT));
        estNet.addPerson(new Person(215, "Catia", Person.PersonRole.STUDENT));
        estNet.addPerson(new Person(18, "Alberto", Person.PersonRole.STUDENT));
        estNet.addPerson(new Person(35, "Pedro", Person.PersonRole.TEACHER));
        estNet.addPerson(new Person(31, "Ana", Person.PersonRole.TEACHER));
        estNet.addClassRelationship("Algebra Geral", 31,131);
        estNet.addClassRelationship("Algebra Geral",31, 215);
        estNet.addClassRelationship("Análise Matematica", 31, 18);
        estNet.addClassRelationship("Análise Matematica",31, 131);
        estNet.addClassRelationship("PA", 35, 18);
        estNet.addClassRelationship("PA", 35, 131);
        estNet.addClassRelationship("PA", 35, 235);
        estNet.addGroupRelationship("Colegas Tuna", 231, 18);
        estNet.addGroupRelationship("Colegas Tuna", 231, 131);
        estNet.addGroupRelationship("Colegas Tuna", 231, 215);
        estNet.addGroupRelationship("Colegas Tuna", 18, 215);
        estNet.addGroupRelationship("Colegas Grupo PA-1", 231, 18);
        estNet.addGroupRelationship("Colegas Grupo PA-1", 131, 235);
        estNet.addGroupRelationship("Colegas Grupo Algebra-1", 135, 215);
        return estNet;
    }

}
