import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GameOverStage extends Stage{

    private BorderPane root;
    private Scene scene;

    public GameOverStage(Stage primaryStage){
        root = new BorderPane();
        this.scene = new Scene(root, 150, 100);
        init(scene);
        scene.setRoot(root);
    }

    public void init(Scene primaryScene){
        Text txtGameOver = new Text("Game Over");
        Button btnTryAgain = new Button("Try Again");
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UNDECORATED);

        btnTryAgain.setOnAction((ActionEvent e) -> {
            MinesweeperLogic.getInstance().setGame();
            close();
        });

        root.getChildren().addAll(txtGameOver,btnTryAgain);
    }
}
