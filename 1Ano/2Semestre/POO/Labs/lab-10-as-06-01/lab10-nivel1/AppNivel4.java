

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
/**
 * Escreva uma descrição da classe JavaFX AppNivel4 aqui.
 *
 * @author (seu nome aqui)
 * @version (número da versão ou data aqui)
 */
public class AppNivel4 extends Application
{
    // Mantemos um registro da contagem e um rótulo que apresenta a contagem:
    private int count = 0;
    private Label myLabel = new Label("0");

    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 300;
    
    double rocketX, rocketY;
    /**
     * O método start é o principal ponto de entrada para aplicativos JavaFX . 
     * É chamado após o método init() ter retornado e após
     * o sistema estar pronto para a aplicação iniciar a executar
     *
     * @param  stage o palco ('scene') primário desta aplicação.
     */
    @Override
    public void start(Stage stage)
    {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(500, 500);
        pane.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Circle planet = new Circle();
        planet.setRadius(500);
        planet.setCenterX(50);
        planet.setCenterY(50);
        planet.setTranslateY(550);
        planet.setFill(Color.LIGHTGREY);
        
        Rectangle star1 = new Rectangle();
        Rectangle star2 = new Rectangle();
        Rectangle star3 = new Rectangle();

        star1.setWidth(20);
        star1.setHeight(20);
        star1.setRotate(45);
        star1.setTranslateX(-150);
        star1.setTranslateY(-100);
        star1.setFill(Color.WHITE);
        
        star2.setWidth(20);
        star2.setHeight(20);
        star2.setRotate(45);
        star2.setTranslateX(-120);
        star2.setTranslateY(0);
        star2.setFill(Color.WHITE);
        
        star3.setWidth(20);
        star3.setHeight(20);
        star3.setRotate(45);
        star3.setTranslateX(150);
        star3.setTranslateY(-100);
        star3.setFill(Color.WHITE);
        
        Foguete rocket = new Foguete();
        
        rocket.setOnMousePressed(e -> {
             rocketX = rocket.getLayoutX();
             rocketY = rocket.getLayoutY();
        });
        rocket.setOnMouseDragged(e -> {
             double offsetX = e.getSceneX() - rocketX - 25;
             double offsetY = e.getSceneY() - rocketY - 100;
             rocket.setTranslateX(offsetX);
             rocket.setTranslateY(offsetY);
        });
        
        rocket.setOnMouseReleased(e -> {
             // Updating the new layout positions
             rocket.setLayoutX(rocketX + rocket.getTranslateX());
             rocket.setLayoutY(rocketY + rocket.getTranslateY());
             // Resetting the translate positions
             rocket.setTranslateX(300);
             rocket.setTranslateY(0);
        });

        pane.getChildren().add(planet);   
        pane.getChildren().add(star1);
        pane.getChildren().add(star2);
        pane.getChildren().add(star3);
        pane.getChildren().add(rocket);
        // O JavaFX deve ter uma 'Scene' (conteúdo da janela) dentro de um 'Stage' (janela)
        Scene scene = new Scene(pane, WINDOW_WIDTH,WINDOW_HEIGHT);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);
        stage.setResizable(false);

        // Mostra o 'Stage' (janela)
        stage.show();
    }

}
