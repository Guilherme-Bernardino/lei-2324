import java.util.ArrayList;
import java.util.Random;
/**
 * Escreva uma descrição da classe wordGenerator aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class wordGenerator
{
    ArrayList<String> words;
    
    /**
     * Construtor para objetos da classe wordGenerator
     */
    public wordGenerator()
    {
        this.words = new ArrayList<String>();            
    }

    public void fillArrayList(){
        words.add("boolean");
        words.add("break");
        words.add("byte");
        words.add("case");
        words.add("char");
        words.add("class");
        words.add("continue");
        words.add("do");
        words.add("double");
        words.add("else");
        words.add("enum");
        words.add("for");
        words.add("if");
        words.add("import");
        words.add("int");
    }
    
    public void generateWord(){
            int index = (int)(Math.random()*words.size());
            System.out.println(""+words.get(index));
    }
    
    public void addWord(String addWord){
        String input = addWord;
        words.add(addWord);
    }
}
