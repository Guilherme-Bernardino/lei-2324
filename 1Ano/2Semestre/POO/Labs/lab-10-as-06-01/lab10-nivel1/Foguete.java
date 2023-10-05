import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

/**
 * Escreva uma descrição da classe Foguete aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Foguete extends StackPane
{
    /**
     * Construtor para objetos da classe Foguete
     */
    

    
    public Foguete()
    {
        createRocket();
    }
    
    public void createRocket(){
        Rectangle body = new Rectangle(70,120);
        
        body.setFill(Color.DARKGREY);
        
        Polygon top = new Polygon();
        top.getPoints().addAll(new Double[]{
            0.0, 0.0,
            -35.0, 60.0,
            35.0, 60.0 
        });

        top.setFill(Color.DARKRED);
        top.setTranslateY(-90);
        
        Polygon boosterL = new Polygon();
        boosterL.getPoints().addAll(new Double[]{
            0.0, 0.0,
            -20.0, 60.0,
            20.0, 60.0 
        });
        
        boosterL.setFill(Color.DARKRED);
        boosterL.setTranslateY(60);
        boosterL.setTranslateX(-25);
        
        Polygon boosterR = new Polygon();
        boosterR.getPoints().addAll(new Double[]{
            0.0, 0.0,
            -20.0, 60.0,
            20.0, 60.0 
        });
        
        boosterR.setFill(Color.DARKRED);
        boosterR.setTranslateY(60);
        boosterR.setTranslateX(25);
        
        getChildren().addAll(boosterL, boosterR, body, top);
    }
}
