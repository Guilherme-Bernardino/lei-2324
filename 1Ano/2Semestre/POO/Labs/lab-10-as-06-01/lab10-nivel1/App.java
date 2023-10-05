

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
/**
 * Escreva uma descrição da classe JavaFX App aqui.
 *
 * @author (seu nome aqui)
 * @version (número da versão ou data aqui)
 */
public class App extends Application
{
    // Mantemos um registro da contagem e um rótulo que apresenta a contagem:
    private int count = 0;
    //private Label myLabel = new Label("0");

    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 300;
    
    double garfoX, garfoY;
    
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
        // Cria um botão ou um outro controle
       // Button myButton = new Button("Count");

        // Cria um novo painel em grade
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        //pane.setVgap(10);
        //pane.setHgap(10);
        Circle gema = new Circle();
   
        gema.setCenterX(50);
        gema.setCenterY(50);
        gema.setRadius(50);
        gema.setFill(Color.YELLOW);
        
        Ellipse clara = new Ellipse();
        
        clara.setCenterX(50);
        clara.setCenterY(50);
        clara.setRadiusX(150);
        clara.setRadiusY(100);
        clara.setFill(Color.WHITE);
        clara.setRotate(45);
        
        Circle prato = new Circle();
        
        prato.setCenterX(50);
        prato.setCenterY(50);
        prato.setRadius(200);
        prato.setFill(Color.RED);
        prato.setStrokeWidth(50);
        prato.setStroke(Color.DARKRED);
        
        
        StackPane garfo = new StackPane();
        Rectangle pega = new Rectangle();
        pega.setFill(Color.GREY);
        pega.setWidth(200);
        pega.setHeight(30);
        pega.setTranslateX(50);
        Rectangle row = new Rectangle();
        row.setWidth(10);
        row.setHeight(50);
        row.setFill(Color.GREY);
        row.setTranslateX(-50);
        Rectangle dente1 = new Rectangle();
        Rectangle dente2 = new Rectangle();
        Rectangle dente3 = new Rectangle();
        dente1.setWidth(50);
        dente2.setWidth(50);
        dente3.setWidth(50);
        dente1.setHeight(10);
        dente2.setHeight(10);
        dente3.setHeight(10);
        dente1.setTranslateX(-70);
        dente2.setTranslateX(-70);
        dente3.setTranslateX(-70);
        dente1.setTranslateY(15);
        dente2.setTranslateY(0);
        dente3.setTranslateY(-15);
        
        dente1.setFill(Color.GREY);
        dente2.setFill(Color.GREY);
        dente3.setFill(Color.GREY);
        //pega.setTranslateY(100);
        
        //pega.setTranslateY(100);
        garfo.getChildren().add(pega);
        garfo.getChildren().add(row);
        garfo.getChildren().add(dente1);
        garfo.getChildren().add(dente2);
        garfo.getChildren().add(dente3);
        
        
        pane.getChildren().add(prato);
        pane.getChildren().add(clara);
        pane.getChildren().add(gema);
        pane.getChildren().add(garfo);
    
        //Define um ação no botão usando uma referência para um método
       // myButton.setOnAction(this::buttonClick);
        // Adicionar dentro da função start
        garfo.setOnMousePressed(e -> {
             garfoX = garfo.getLayoutX();
             garfoY = garfo.getLayoutY();
        });
        garfo.setOnMouseDragged(e -> {
             double offsetX = e.getSceneX() - garfoX - 25;
             double offsetY = e.getSceneY() - garfoY - 100;
             garfo.setTranslateX(offsetX);
             garfo.setTranslateY(offsetY);
        });
        
        garfo.setOnMouseReleased(e -> {
             // Updating the new layout positions
             garfo.setLayoutX(garfoX + garfo.getTranslateX());
             garfo.setLayoutY(garfoY + garfo.getTranslateY());
             // Resetting the translate positions
             garfo.setTranslateX(300);
             garfo.setTranslateY(0);
        });
        // Adiciona o botão e o rótulo ao painel
       // pane.add(myLabel, 1, 0);
       // pane.add(myButton, 0, 0);

        // O JavaFX deve ter uma 'Scene' (conteúdo da janela) dentro de um 'Stage' (janela)
        Scene scene = new Scene(pane, WINDOW_WIDTH,WINDOW_HEIGHT);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);
        scene.setFill(Color.PINK);

        // Mostra o 'Stage' (janela)
        stage.show();
    }

    /**
     * Isto será executado quando o botão for pressionado
     * O método incrementa o contador em uma unidade
     */

    /*private void buttonClick(ActionEvent event)
    {
        // Conta o número de ativações do botão e mostra o resultado em um rótulo
        count = count + 1;
        myLabel.setText(Integer.toString(count));
    }*/
}
