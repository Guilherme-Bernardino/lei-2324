
/**
 * Escreva uma descrição da classe GameCounter aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class GameScore
{
    private int score;
    private int tries;

    /**
     * Construtor para objetos da classe GameCounter
     */
    public GameScore(){
        score = 0;
        tries = 0;
    }

    public void incrementWin(){
        score += 1;
        tries += 1;
    }
    
    public void incrementLoss(){
        tries += 1;
    }
    
    @Override
    public String toString(){
        return score + " / " + tries;
    }
}
