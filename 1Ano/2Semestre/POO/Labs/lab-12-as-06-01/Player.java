import javafx.beans.property.ObjectProperty;
import javafx.beans.property.*;
import javafx.beans.property.DoubleProperty;
import java.time.LocalDate;

/**
 * Escreva uma descrição da classe Player aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Player
{
    private StringProperty name;
    private ObjectProperty<LocalDate> birthDate;
    private DoubleProperty score;
    
    public Player(){
        this.name = new SimpleStringProperty("");
        this.birthDate = new SimpleObjectProperty(LocalDate.now());
        this.score = new SimpleDoubleProperty(0.0);
    }
    
    public Player(String name, LocalDate birthDate, double score){
        this.name = new SimpleStringProperty(name);
        this.birthDate = new SimpleObjectProperty(birthDate);
        this.score = new SimpleDoubleProperty(score);
    }
    
    public String getName(){
        return this.name.get();
    }
    
    public void setName(String name){
        this.name.set(name);
    }
    
    public LocalDate getBirthDate(){
        return this.birthDate.get();
    }
    
    public void setBirthDate(LocalDate date){
        this.birthDate.set(date);
    }
    
    public double getScore(){
        return this.score.get();
    }
    
    public void setScore(double score){
        this.score.set(score);
    }
    
    public StringProperty getStringProperty(){
        return this.name;
    }
    
    public ObjectProperty<LocalDate> getLocalDateProperty(){
        return this.birthDate;
    }
    
    public DoubleProperty getDoubleProperty(){
        return this.score;
    }
    
}
