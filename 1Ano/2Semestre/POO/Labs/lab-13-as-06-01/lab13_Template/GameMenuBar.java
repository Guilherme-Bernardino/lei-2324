import javafx.application.Application;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Escreva uma descrição da classe TopMenuBar aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class GameMenuBar extends MenuBar
{
    public GameMenuBar(Stage primaryStage)
    {
        init(primaryStage);
    }
    
    public void init(Stage primaryStage)
    {
        Menu menuDef = new Menu("Definições");
        MenuItem menuRestart = new MenuItem("Nova Tentativa");
        MenuItem menuDifficulty = new MenuItem(getDifficultyString());

        Menu menuFile = new Menu("Ficheiro");
        MenuItem menuHelp = new MenuItem("Ajuda");
        MenuItem menuStart = new MenuItem("Menu Inicial");
        MenuItem menuLeave = new MenuItem("Sair");

        menuRestart.setOnAction((ActionEvent e) -> {
            primaryStage.setScene(new GamePane(primaryStage));
        });
        menuDifficulty.setOnAction((ActionEvent e) -> {
     
            MinesweeperLogic.getInstance().changeDifficulty();
            
            menuDifficulty.setText(getDifficultyString());
            
            primaryStage.setScene(new GamePane(primaryStage));
        });

        menuStart.setOnAction((ActionEvent e) -> {
            primaryStage.setScene(new MenuPane(primaryStage));
        });

        menuLeave.setOnAction((ActionEvent e) -> {
            primaryStage.close();
        });

        menuHelp.setOnAction((ActionEvent e) -> {
            Alert alertHelp = new Alert(AlertType.INFORMATION, getHelpText());
            alertHelp.show();
        });
        
        menuDef.getItems().addAll(menuRestart, menuDifficulty);
        menuFile.getItems().addAll(menuStart,menuHelp,menuLeave);

        getMenus().addAll(menuFile, menuDef);
    }
    
    public String getDifficultyString(){
        int difficulty = MinesweeperLogic.getInstance().getDifficulty();
        switch (difficulty) {
            case 1:
                return "Dificuldade: Fácil";
            case 2:
                return "Dificuldade: Médio";
            case 3:
                return "Dificuldade: Difícil";
            default:
                return "Dificuldade: Fácil";
        }
    }
    
    public String getHelpText() {
        return "Você pode começar clicando em qualquer lugar aleatório desde que seja"
                 + " seu primeiro jogo. É provável que você tenha algo parecido com a imagem. "
                 + "Lembre-se, o número são as minas adjacentes ao bloco. Então vamos pegar"
                 + " o caso na imagem. Veja o bloco inferior esquerdo na imagem (3º de "
                 + "a esquerda e a 2ª de baixo). Tem 1 mina adjacente a ela e ali "
                 + "há apenas um bloco fechado próximo a ele. Portanto, deve ser uma mina."
                 + " Sinalize clicando com o botão direito do mouse. Yay! Agora você encontrou sua primeira mina."
                 + " Faça o mesmo com os outros. Se um bloco com 2 tiver apenas dois por abrir "
                 + "as minas sinalizam as não abertas e o mesmo para outros números (se você tiver "
                 + "8, você é uma pessoa de muita sorte ;) ).\nÉ hora de limpar o outro"
                 + " blocos. O bloco A (ver imagem) já tem uma mina ao lado. Isso significa"
                 + "que não haverá outra mina adjacente a ela. Assim, você pode limpar"
                 + " a mina no canto inferior esquerdo.\nVocê frequentemente se deparará com uma situação "
                 + "onde você tem que adivinhar. Mas a maioria deles que (parece) "
                 + "exige um palpite quando o jogo está prestes a terminar. Como neste "
                 + "caixa (veja a imagem). Só resta 1 mina e se deve estar perto "
                 + "os '3's. Em qualquer um dos blocos. Portanto, a melhor coisa a fazer é abrir "
                 + "o bloco que não é comum a ambos como os blocos marcados com "
                 + "laranja na imagem e você poderá encontrar a mina.\nAntes "
                 + "chegando a esta etapa, espero que você tenha concluído pelo menos 6"
                 + "campos iniciantes e com alguma experiência. Aqui está um belo "
                 + "uma técnica útil que encontrei. Chama-se padrão 1-2. Aqui no "
                 + "na imagem acima você pode ver o 1-2 de frente para uma parede (de blocos). Aqui,"
                 + "você pode limpar o bloco ao lado de um (não no campo comum!)."
                 + "Esta técnica será útil ao jogar intermediário e avançado"
                 + "níveis. Tente pensar que há uma mina no bloco que eu disse para você limpar,"
                 + " o 1 e o 2 serão satisfeitos?\nMuitas vezes você encontrará algo como "
                 + "o caso acima. Aqui uma mina está presente em qualquer um dos blocos marcados em "
                 + "laranja. Você pode abrir o bloco azul, é seguro. Pense nisso você mesmo, "
                 + "o que acontecerá se uma mina for colocada no bloco azul, os 1s serão "
                 + "satisfeito?";
    }
}
