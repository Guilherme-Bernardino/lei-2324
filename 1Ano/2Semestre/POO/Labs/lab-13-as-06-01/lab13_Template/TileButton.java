import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TileButton extends Button {

    private Tile tile;
    private ImageView image = new ImageView(new Image("Images/Undiscovered.png"));

    public TileButton(Tile tile){
        this.tile = tile;
    }

    public void click() {
        if(MinesweeperLogic.getInstance().isFlaggingMode()){
            markFlag();
            return;
        }

        if(tile.isFlagged()) return;
        if(tile.isIsUnveiled()) return ;


        tile.setUnveiled(true);
        setText( tile.getValue() + "");
    }

    public void changeButtonSize(ImageView image, int xPixels, int yPixels) {
        image.setFitHeight(yPixels);
        image.setFitWidth(xPixels);
    }

    public void markFlag() {
        if(tile.isFlagged()){
            setText("");
        }else{
            //setText("Flag");
            image.setImage(new Image("Images/flag.png"));
            changeButtonSize(image, 50,50);
            setGraphic(image);
        }
        tile.toggleFlagged();
    }

    public Tile getTile() {
        return tile;
    }
}
