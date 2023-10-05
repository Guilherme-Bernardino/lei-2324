import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;

/**
 * Escreva uma descrição da classe TopMenuBar aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class InicialMenuBar extends MenuBar
{
    public InicialMenuBar()
    {
        init();
    }
    public void init()
    {
        // escreva seu código aqui
        MenuItem menuExit = new MenuItem("Sair");
        menuExit.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        
        // -- Nivel 1 --
        Menu menuFile = new Menu("Ficheiro");
        menuFile.getItems().add(menuExit);
        
        this.getMenus().addAll(menuFile);
    }
}
