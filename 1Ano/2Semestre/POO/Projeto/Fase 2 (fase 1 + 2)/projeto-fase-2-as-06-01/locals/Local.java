package locals;

import coords.Dimension;
import coords.Position;

/**
 * EN
 * The Local class is an abstract class referring to a location in the distribution center space used for
 * identification of a physical space.
 * This and all derived classes implement the Localizable interface, and use the position to localize itself in the matrix.
 * The locations use a position and a dimension for usage in the 2D matrix space.
 *
 * PT
 * A classe Local é uma classe abstrata referente a um local no espaço do centro de distribuição usado para
 * identificação de um espaço físico.
 * Esta e todas as classes derivadas implementam a interface Localizable e usam a posição para se localizar na matriz.
 * Os locais usam uma posição e uma dimensão para uso no espaço da matriz 2D.
 *
 * @author guilh
 */
public abstract class Local implements Localizable{

    protected Position position;
    protected Dimension dimension;

    public Local(Position position, Dimension dimension){
        this.position = position;
        this.dimension = dimension;
    }

    /**
     * Returns the dimension.
     *
     * @return dimension
     */
    public abstract Dimension getDimension();

    /**
     * Sets a new dimension.
     *
     * @param dimension
     */
    public abstract void setDimension(Dimension dimension);
}
