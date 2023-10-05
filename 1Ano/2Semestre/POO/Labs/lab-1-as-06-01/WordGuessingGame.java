import java.io.Reader;

/**
 * Escreva uma descrição da classe WordGuessingGame aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class WordGuessingGame
{
    
    private String hiddenWord;
    private String guessedWord;
    private static int numberOfTries = 0;
    private InputReader reader;
    /**
     * Construtor para objetos da classe WordGuessingGame
     */
    public WordGuessingGame()
    {
        this.hiddenWord = "abc";
        this.guessedWord = "___";
        this.reader = new InputReader();
    }

    public void setHiddenWord(String hiddenWord){
        this.hiddenWord= hiddenWord;
    }
    
    public void setGuessedWord(String guessedWord){
        this.guessedWord= guessedWord;
    }
    
    public void setNumberOfTries(int numberOfTries){
        this.numberOfTries++;
    }
    
    private void showGuessedWord(){
        System.out.println(""+ hiddenWord);
    }
    
    public void play(){
        showWelcome();
        showGuessedWord();
        guess();
        showResult();
    }
    
    private void guess(){
        char input = reader.getChar(null);
        char[] charArray = hiddenWord.toCharArray();
        
        for(int i = 0; i< hiddenWord.length(); i++ ){
            if(hiddenWord.charAt(i) == input){
                charArray[i] = input;
                guessedWord = String.valueOf(charArray);
            }
        }
    }

    
    private void showWelcome(){
        System.out.println("Boas vindas ao jogo da adivinha");
    }
    
    private void showResult(){
        while(guessedWord != hiddenWord){
            numberOfTries++;
        }
    }
}
