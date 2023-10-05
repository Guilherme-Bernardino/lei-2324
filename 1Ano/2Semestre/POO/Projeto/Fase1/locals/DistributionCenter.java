package locals;

import coords.Dimension;
import coords.Position;
import vehicles.AGV;

import java.util.ArrayList;
import java.util.List;

/**
 * EN
 * The Distribution Center is a Local derived class, used for harboring the warehouse and pickUp and drop-off locals,
 * and vehicles in form of lists.
 * The D.C. can add locals, add vehicles, and return their specific locals or vehicles.
 * Since it implements the Localizable interface, this object can be visually represented in a 2d matrix, using its
 * dimension and position.
 *
 * PT
 * O Centro de Distribuição é uma classe derivada de Local, usada para abrigar o depósito e os locais de coleta e entrega,
 * e veículos em forma de listas.
 * O C.D. pode adicionar locais, adicionar veículos e retornar seus locais ou veículos específicos.
 * Como implementa a interface Localizable, este objeto pode ser representado visualmente numa matriz 2d, usando a sua
 * dimensão e posição.
 *
 * @author guilh
 */
public class DistributionCenter extends Local {
    List<AGV> vehicles;

    List<Local> locals;

    public DistributionCenter(Position position, Dimension dimension) {
        super(position, dimension);
        this.vehicles = new ArrayList<>();
        this.locals = new ArrayList<>();
    }

    /**
     * Adds a list of locals to this object.
     *
     * @param locals
     * @return true if added all, false otherwise
     */
    public boolean addLocals(List<Local> locals){
        return this.locals.addAll(locals);
    }

    /**
     * Adds a list of vehicles to this object.
     *
     * @param vehicles
     * @return true if added all, false otherwise
     */
    public boolean addVehicles(List<AGV> vehicles){
        return this.vehicles.addAll(vehicles);
    }

    /**
     * Returns this object's warehouse.
     * Iterates through the locals list and returns the desired local.
     *
     * @return warehouse
     */
    public Warehouse getWarehouse(){
        for (Local local : locals) {
            if(local instanceof Warehouse){
                return (Warehouse) local;
            }
        }
        return null;
    }

    /**
     * Returns this object's drop-off local.
     * Iterates through the locals list and returns the desired local.
     *
     * @return drop-off local
     */
    public DropOffLocal getDropOffLocal(){
        for (Local local : locals) {
            if(local instanceof DropOffLocal){
                return (DropOffLocal) local;
            }
        }
        return null;
    }

    /**
     * Returns this object's pickup local.
     * Iterates through the locals list and returns the desired local.
     *
     * @return pickup local
     */
    public PickUpLocal getPickUpLocal(){
        for (Local local : locals) {
            if(local instanceof PickUpLocal){
                return (PickUpLocal) local;
            }
        }
        return null;
    }

    /**
     * Returns the list of vehicles.
     *
     * @return list of vehicles
     */
    public List<AGV> getVehicles() {
        return vehicles;
    }

    /**
     * Returns the list of locals.
     *
     * @return list of locals
     */
    public List<Local> getLocals() {
        return locals;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void display(char[][] matrix) {
        int centerX = position.getX();
        int centerY = position.getY();
        int width = dimension.getX();
        int height = dimension.getY();

        int startX = centerX - width / 2;
        int endX = startX + width;
        int startY = centerY - height / 2;
        int endY = startY + height;

        // Update the matrix to represent the Distribution Center
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                if (i == startX || i == endX - 1 || j == startY || j == endY - 1)  {
                    matrix[i][j] = '*';
                } else {
                    matrix[i][j] = '.';
                }
            }
        }
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: Distribution Center" + "\n");
        sb.append("Position: " + getPosition().getY() + "," + getPosition().getY() + "\n");
        sb.append("Dimension: " + getDimension().getX() + "," + getDimension().getY());
        return sb.toString();
    }
}
