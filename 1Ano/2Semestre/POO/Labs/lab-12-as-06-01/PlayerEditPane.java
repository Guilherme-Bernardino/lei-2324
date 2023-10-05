import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * Escreva uma descrição da classe PlayerEditPane aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class PlayerEditPane extends GridPane
{
   private Player player;
   
   public PlayerEditPane(Player newPlayer){
       if(newPlayer == null) throw new NullPointerException();
       this.player = newPlayer;
       
       draw();
   }
   
   private void draw(){
        setPadding(new Insets(10,10,10,10));
        setVgap(5);
        setHgap(10);
        
        Label lblName = new Label("Name");
        TextField txtName = new TextField();
        Label lblBirthDate = new Label("Birth Date");
        DatePicker dtBirthDate = new DatePicker();
        Label lblScore = new Label("Score");
        Spinner spScore = new Spinner(0,20,0, 0.5);
        
        Button btnUpdate = new Button("Update");
        
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                player.setName(txtName.getText());
                player.setBirthDate(dtBirthDate.getValue());
                player.setScore((double) spScore.getValue()); 
            }
        });
        
        add(lblName, 0,0);
        add(txtName, 1,0);
        add(lblBirthDate, 0,1);
        add(dtBirthDate, 1,1);
        add(lblScore, 0,2);
        add(spScore, 1,2);
        add(btnUpdate, 0,3);

    }
    
}
