import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Game
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private GameScore gameScore;
    private GenerateColor generateColor;
    
    private Color correctColor;
    private ArrayList<Color> incorrectColors;

    /**
     * Construtor para objetos da classe Game
     */
    public Game()
    {
        gameScore = new GameScore();
        incorrectColors = new ArrayList<>();
        generateColors();
    }

    public void generateColors(){
        correctColor = new GenerateColor().getRandomColor();
        incorrectColors = new ArrayList<>(); 
        for(int i = 0; i < 3; i++){
            incorrectColors.add(new GenerateColor().getRandomColor());
        }
    }
    
    public void guess(Color color){
        if(correctColor.equals(color)){
            gameScore.incrementWin();
        } else {
            gameScore.incrementLoss();
        }
    }
    
    public String getScore(){
        return gameScore.toString();
    }
    
    public Color getCorrectColor(){
        return correctColor;
    }
    
    public ArrayList<Color> getColors(){
        ArrayList<Color> colors = incorrectColors;
        colors.add(correctColor);
        Collections.shuffle(colors);
        return colors;
    }
    
    @Override
    public String toString() {
        return String.format("CorrectColor: %s,%s,%s\n",
            getCorrectColor().getRed()*255,
            getCorrectColor().getGreen()*255,
            getCorrectColor().getBlue()*255
        );
    }
}
