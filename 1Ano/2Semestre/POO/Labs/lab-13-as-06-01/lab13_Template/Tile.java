
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile{
    private int value; // if bomb is -1,
                       // if water is 0
                       // if has number is number
    private boolean flagged = false;  
    private boolean isUnveiled = false;
    private int x;
    private int y;
    
    public Tile(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

    public boolean isFlagged() {
        return flagged;
    }
    
    public void toggleFlagged(){
        if(!isUnveiled){
            this.flagged = !isFlagged();
        }
    }

    public boolean isIsUnveiled() {
        return isUnveiled;
    }
    
    public void setUnveiled(boolean value){
        this.isUnveiled = value;
    }

    public int getValue() {
        return value;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public void increaseValue() {
        value++;
    }
     @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Tile)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Tile c = (Tile) o;
         
        // Compare the data members and return accordingly
        return c.getX() == this.getX() && c.getY() == this.getY();
    }
    @Override
    public String toString() {
        return "Tile at (" + x + ", " + y + ") with value " + value;
    }
}
