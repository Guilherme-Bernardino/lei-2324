import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import java.awt.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Escreva uma descrição da classe MainMenu aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class MenuPane extends Scene
{
    private Button btnStart;
    private BorderPane root;

    private InicialMenuBar inicialMenuBar;
    
    public MenuPane(Stage primaryStage){
        super(new BorderPane(),500,500);
        root = new BorderPane();
        inicialMenuBar = new InicialMenuBar();
        this.init(primaryStage);
        setRoot(root);
    }
    
    public void init(Stage primaryStage){
        btnStart = new Button("Jogar Agora");
        btnStart.setTextFill(Color.BLUE);
        btnStart.setFont(new Font("Arial", 24));
        btnStart.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));

        root.setCenter(btnStart);
        root.setTop(inicialMenuBar);

        btnStart.setOnAction(e -> {
            primaryStage.setScene(new GamePane(primaryStage));
        });
    }
}
