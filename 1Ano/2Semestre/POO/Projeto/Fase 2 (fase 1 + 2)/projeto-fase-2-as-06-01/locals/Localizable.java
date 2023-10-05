package locals;

import coords.Position;

/**
 * EN
 * Localizable interface used in localizable objects such as locations and vehicles.
 * The classes that implement this interface can be accessed by position, set the position
 * and can display such position on a visual matrix.
 * PT
 * Interface localizável usada em objetos localizáveis, como locais e veículos.
 * As classes que implementam esta interface podem ser acedidas por posição, definir a posição
 * e pode exibir tal posição em uma matriz visual.
 *
 * @author guilh
 */
public interface Localizable {

    /**
     * Returns the position (x value and y value).
     *
     * @return the position on this Localizable
     */
    Position getPosition();

    /**
     * Sets a new position (x value and y value).
     *
     * @param position
     */
    void setPosition(Position position);

    /**
     * Uses the matrix of positions to display and update the position of this
     * Localizable object.
     *
     * @param matrix
     */
    void display(char[][] matrix);
}
