import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.time.LocalDate;

/**
 * Escreva uma descrição da classe PlayerPane aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class PlayerPane extends VBox
{
    private Player player;
    
    public PlayerPane(Player newPlayer){
        if(newPlayer == null) throw new NullPointerException();
        
        this.player = newPlayer;
        draw();
    }
    
    private void draw(){
        setPadding(new Insets(10,10,10,10));
        
        Label lblName = new Label("player.getName()");
        lblName.setFont(new Font("Verdana",20));
        Label lblBirthDate = new Label(player.getBirthDate() + "");
        lblBirthDate.setFont(new Font("Verdana",15));
        Label lblScore = new Label(player.getScore() + "");
        lblScore.setFont(new Font("Verdana", 15));
        lblScore.setStyle("-fx-font-weight: bold");
        
        lblName.textProperty().bind(player.getStringProperty());
        lblBirthDate.textProperty().bind(player.getLocalDateProperty().asString());
        //lblBirthDate.textProperty().addListener((observable, oldValue, newValue) -> lblBirthDate.setText(newValue.toString()));
        lblScore.textProperty().bind(player.getDoubleProperty().asString());
        
        getChildren().addAll(lblName, lblBirthDate, lblScore);
    }
}
