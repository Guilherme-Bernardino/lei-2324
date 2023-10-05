import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application{
    private Stage stage;
    private Game game;
    
    @Override
    public void start(Stage stage)
    {
        this.game = new Game();
        this.stage = stage;
        stage.setScene(getInitialScene());
        stage.show();
    }
    
    private Scene getInitialScene(){
        Label welcomeLabel = new Label("Bem vindo ao jogo Color Guesser");
        welcomeLabel.setTranslateY(-30);
        welcomeLabel.setFont(new Font("Arial", 30));
        welcomeLabel.setTextFill(Color.WHITE);
        Label infoLabel = new Label("Clique em 'Start' para começar");
        infoLabel.setTextFill(Color.WHITE);
        
        Button btnStart = new Button("Start");
        btnStart.setTranslateY(40);
        
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                changeScene(getGameScene());
                stage.setTitle("Game");
            }
        });
        
        StackPane sp = new StackPane();
        
        
        sp.getChildren().addAll(welcomeLabel, infoLabel, btnStart);
        Scene scene = new Scene(sp, 500,500);
        scene.getStylesheets().addAll(this.getClass().getResource("styles/style.css").toExternalForm());
        
        stage.setTitle("Welcome to the Color Guesser");
        
        sp.setId("background");
        scene.getStylesheets().addAll(this.getClass().getResource("styles/style.css").toExternalForm());
        
        return scene;
    }
    
    private void changeScene(Scene scene){
        stage.setScene(scene);
    }
    
    private Scene getGameScene(){
        List<Button> buttons = new ArrayList<>();
        List<Color> colors = new ArrayList<>();

        BorderPane bp = new BorderPane();
        bp.setMinSize(500, 500);
        
        HBox hbox = new HBox();
        
        colors.addAll(game.getColors());
        
        for(int i=0; i<colors.size(); i++){
            Button btn = new Button("R:" + Math.round(colors.get(i).getRed()*255) +"\n" + 
                                    "G: " + Math.round(colors.get(i).getGreen()*255) +"\n" + 
                                    "B: " + Math.round(colors.get(i).getBlue()*255));
            buttons.add(btn);
            hbox.getChildren().add(btn);
            hbox.setMargin(btn,new Insets(0,0,10,0));
            
        }
        
        buttons.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                game.guess(colors.get(0));
                game.generateColors();
                changeScene(getGameScene());
            }
        });
        
        buttons.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                game.guess(colors.get(1));  
                game.generateColors();
                changeScene(getGameScene());
            }
        });
        
        buttons.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                game.guess(colors.get(2));
                game.generateColors();
                changeScene(getGameScene());
            }
        });
        
        buttons.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                game.guess(colors.get(3));
                game.generateColors();
                changeScene(getGameScene());
            }
        });
        

        Rectangle sq = new Rectangle();
        sq.setHeight(100);
        sq.setWidth(100);
        sq.setFill(game.getCorrectColor());
        
        Label score = new Label(game.getScore());
        score.setPadding(new Insets(10,10,10,10));
        
 
        //bp.getChildren().addAll(sq, hbox);
        bp.setBottom(hbox);
        bp.setCenter(sq);
        bp.setTop(score);
        
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setPadding(new Insets(0,10,0,0));     
        hbox.setSpacing(10);
        
        Scene scene = new Scene(bp, 500,500);
  
        return scene;
    }
    
}
