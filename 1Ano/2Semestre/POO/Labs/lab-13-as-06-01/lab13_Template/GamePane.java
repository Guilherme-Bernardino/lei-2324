import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import java.util.List;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
/**
 * Escreva uma descrição da classe GamePane aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class GamePane extends Scene
{
    private BorderPane root;
    private GridPane minesweeperBoard;
    
    public GamePane(Stage primaryStage)
    {
        super(new BorderPane());
        root = new BorderPane();
        minesweeperBoard = new GridPane();
        init(primaryStage);
        setRoot(root);
    }

    public void init(Stage primaryStage){
        root.setTop(new GameMenuBar(primaryStage));
        root.setTop(new GameMenuBar(primaryStage));
        
        for(int x = 0; x < MinesweeperLogic.getInstance().getBoardSizeX(); x++){
            for(int y = 0; y < MinesweeperLogic.getInstance().getBoardSizeY(); y++){
                Tile tile = MinesweeperLogic.getInstance().getTile(x, y);
                TileButton tileButton = new TileButton(tile);
                tileButton.setOnAction(e -> {
                    
                    if (MinesweeperLogic.getInstance().isFlaggingMode()){
                        if(!tile.isIsUnveiled()){
                            tileButton.markFlag();
                        }
                    }else{
                        if(tile.isFlagged()){
                            return;
                        }
                        tileButton.click();
                        if(tile.getValue() == 0){
                            for(Tile waterTile : MinesweeperLogic.getInstance().propagateWaterAlgorithm(tile)){
                                TileButton waterbuttontile = getNodeByRowColumnIndex(waterTile.getY(),waterTile.getX(),minesweeperBoard);

                                if(waterbuttontile != null){
                                    waterbuttontile.click();
                                }
                            }
                        }if(tile.getValue() == -1){
                            System.out.print("Bomb!");
                            GameOverStage gameOverStage = new GameOverStage(primaryStage);
                            gameOverStage.show();
                        }
                    }
                });
                minesweeperBoard.add(tileButton, x, y);
            }
        }

        VBox vbox = new VBox();
        vbox.getChildren().addAll(getGameMenu(primaryStage), minesweeperBoard);
        
        root.setCenter(vbox);
    }
    
    public TileButton getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        TileButton result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
    
        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = (TileButton) node;
                break;
            }
        }
    
        return result;
    }
    
    public HBox getGameMenu(Stage primaryStage){
        HBox hboxmenu = new HBox();
        hboxmenu.setAlignment(Pos.TOP_CENTER);
        //-----------Botão Reiniciar jogo-------------
        Button smile = new Button();
        ImageView smileyPic = new ImageView(new Image("Images/Smiley.png"));
        smileyPic.setFitHeight(50);
        smileyPic.setFitWidth(50);
        smile.setGraphic(smileyPic);
        smile.setOnAction((ActionEvent e) -> {
            MinesweeperLogic.getInstance().setGame();
            primaryStage.setScene(new GamePane(primaryStage));
        });
        //---------- Toggle de modo de jogo ---------------
        Button toggle_bombs_or_flags = new Button();
        ImageView toggleImageView = new ImageView(new Image("Images/Bomb.png"));
        toggle_bombs_or_flags.setOnAction(e -> {
            if(MinesweeperLogic.getInstance().isFlaggingMode()){
                toggleImageView.setImage(new Image("Images/Bomb.png"));
            }else{
                toggleImageView.setImage(new Image("Images/flag.png"));
            }
            MinesweeperLogic.getInstance().toggleFlaggingMode();
        });

        toggleImageView.setFitHeight(50);
        toggleImageView.setFitWidth(50);
        toggle_bombs_or_flags.setGraphic(toggleImageView);

        hboxmenu.getChildren().addAll(smile, toggle_bombs_or_flags);
        return hboxmenu;
    }
}
