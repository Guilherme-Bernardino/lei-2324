import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Escreva uma descrição da classe GenerateColor aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class GenerateColor
{
    private Random rand;
    /**
     * Construtor para objetos da classe GenerateColor
     */
    public GenerateColor()
    {
        rand = new Random();
    }

    public Color getRandomColor(){
        return Color.rgb(
            rand.nextInt(255),
            rand.nextInt(255),
            rand.nextInt(255)
        );
    }
}
