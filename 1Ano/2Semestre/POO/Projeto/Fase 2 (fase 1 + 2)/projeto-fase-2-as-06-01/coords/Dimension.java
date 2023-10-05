package coords;

/**
 * EN
 * Dimension data class used for pairing two coordinates pertaining to a 2D dimension of an object.
 *
 * PT
 * Classe de dados de dimensão usada para emparelhar duas coordenadas pertencentes a uma dimensão 2D de um objeto.
 *
 * @author guilh
 */
public class Dimension {

    private int x;
    private int y;

    public Dimension(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
