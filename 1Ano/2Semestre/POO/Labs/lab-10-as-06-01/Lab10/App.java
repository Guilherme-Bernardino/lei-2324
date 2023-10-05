

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Escreva uma descrição da classe JavaFX Main aqui.
 *
 * @author (seu nome aqui)
 * @version (número da versão ou data aqui)
 */
public class App extends Application
{
    // Mantemos um registro da contagem e um rótulo que apresenta a contagem:
    private int count = 0;
    private Label myLabel = new Label("0");

    /**
     * O método start é o principal ponto de entrada para aplicativos JavaFX . 
     * É chamado após o método init() ter retornado e após
     * o sistema estar pronto para a aplicação iniciar a executar
     *
     * @param  stage o palco ('scene') primário desta aplicação.
     */
    @Override
    public void start(Stage stage)
    {
        // Cria um botão ou um outro controle
        Button myButton = new Button("Count");

        // Cria um novo painel em grade
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        //Define um ação no botão usando uma referência para um método
        myButton.setOnAction(this::buttonClick);

        // Adiciona o botão e o rótulo ao painel
        pane.add(myLabel, 1, 0);
        pane.add(myButton, 0, 0);

        // O JavaFX deve ter uma 'Scene' (conteúdo da janela) dentro de um 'Stage' (janela)
        Scene scene = new Scene(pane, 300,100);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);

        // Mostra o 'Stage' (janela)
        stage.show();
    }

    /**
     * Isto será executado quando o botão for pressionado
     * O método incrementa o contador em uma unidade
     */
    private void buttonClick(ActionEvent event)
    {
        // Conta o número de ativações do botão e mostra o resultado em um rótulo
        count = count + 1;
        myLabel.setText(Integer.toString(count));
    }
}
