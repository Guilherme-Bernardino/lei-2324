package vehicles;

import coords.Position;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import locals.DropOffLocal;
import locals.Localizable;
import locals.PickUpLocal;
import products.Package;
import products.Transportable;
import sensors.*;

import java.util.ArrayList;
import java.util.List;

/**
 * EN
 * The AGV class (Automatic Guided Vehicle) is an abstract class referring to a vehicle in the distribution center space used for
 * transportation of Transportable objects.
 * This and all derived classes implement the Localizable interface, and use the position to localize itself in the matrix.
 * The vehicles use a pair of location, pickup and drop-off, for interactions with the warehouse and products/packages.
 * Uses attributes such as Direction, an enum, to determine the direction in which it should be moving towards.
 *
 * PT
 * A classe AGV (Automatic Guided Vehicle) é uma classe abstrata referente a um veículo no espaço do centro de distribuição utilizado para
 * transporte de objetos transportáveis.
 * Esta e todas as classes derivadas implementam a interface Localizable e usam a posição para se localizar na matriz.
 * As viaturas utilizam um par de localização, recolha e entrega, para interações com o armazém e produtos/embalagens.
 * Usa atributos como Direção, uma enumeração, para determinar a direção na qual ele deve se mover.
 *
 * @author guilh
 */
public abstract class AGV implements Localizable {

    ObjectProperty<Position> positionProperty;
    Position position;
    PickUpLocal pickUp;
    DropOffLocal dropOf;
    Direction direction;
    List<Direction> path;
    Sensor camera, lidar, ultrasonic;
    int speed = 1; // Default speed

    public AGV(){
        this.position = null;
        this.pickUp = null;
        this.dropOf = null;
        this.direction = null;
        this.camera = new Camera();
        this.lidar = new Lidar();
        this.ultrasonic = new Ultrasonic();
        this.path = new ArrayList<>();
        this.positionProperty = new SimpleObjectProperty<>();
    }

    /**
     * Sets a Pickup local for Transportable delivery.
     *
     * @param pickUp
     */
    public abstract void setPickUpLocal(PickUpLocal pickUp);

    /**
     * Sets a Drop-off local for Transportable delivery.
     *
     * @param dropOf
     */
    public abstract void setDropOfLocal(DropOffLocal dropOf);

    /**
     * This method is used for moving a vehicle in the desired location
     * and moving positions depending on its speed (speed = number of positions it can move
     * in one simulation step).
     *
     */
    public abstract void move();

    /**
     * Used for returning a list of transportable that depends of the
     * concrete implementation of each concrete class.
     *
     * @return list of transportable objects
     */
    public abstract List<Transportable> getPackages();

    /**
     * Used for unloading the Transportables in a drop-off local.
     *
     * @return true if it unloaded, false otherwise
     */
    public abstract boolean unload();

    /**
     * Used for loading this vehicle in a pickup local.
     *
     * @param packageList
     * @return true if it unloaded, false otherwise
     */
    public abstract boolean load(List<Transportable> packageList);

    /**
     * Returns the direction currently set.
     *
     * @return direction
     */
    public Direction getDirection(){
        return this.direction;
    }

    /**
     * Sets a new direction.
     *
     * @param direction
     */
    public void setDirection(Direction direction){
       this.direction = direction;
    }

    /**
     * Sets a new path for this vehicle to follow.
     *
     * @param directions
     */
    public void setPath(List<Direction> directions){
        if(directions != null){
            this.path = directions;
        }
    }

    /**
     * Returns the path of directions.
     *
     * @return path
     */
    public List<Direction> getPath() {
        return path;
    }

    /**
     * Retrives items that are ready for shipping.
     *
     * @return true if added, false otherwise
     */
    public abstract boolean retrieveFromShelf(Package pack);


    public Position getPositionProperty() {
        return positionProperty.get();
    }

    public ObjectProperty<Position> positionPropertyProperty() {
        return positionProperty;
    }

    public void setPositionProperty(Position position) {
        this.positionProperty.set(position);
    }
}
