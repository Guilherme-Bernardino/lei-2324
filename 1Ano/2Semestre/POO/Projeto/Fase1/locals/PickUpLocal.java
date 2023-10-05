package locals;

import coords.Dimension;
import coords.Position;
import products.Transportable;
import vehicles.AGV;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * EN
 * The PickUpLocal is a Local derived class, used for setting a pickup local for the distribution center.
 * This local serves for loading the vehicles using the transportables from the list of transportables.
 * Since it implements the Localizable interface, this object can be visually represented in a 2d matrix, using its
 * dimension and position.
 *
 * PT
 * O PickUpLocal é uma classe derivada de Local, usada para definir um local de coleta para o centro de distribuição.
 * Este local serve para carregar os veículos utilizando os transportáveis da lista de transportáveis.
 * Como implementa a interface Localizable, este objeto pode ser representado visualmente numa matriz 2d, usando a sua
 * dimensão e posição.
 *
 * @author guilh
 */
public class PickUpLocal extends Local{

    List<Transportable> transportables;

    Set<AGV> agvSet;

    public PickUpLocal(Position position, Dimension dimension) {
        super(position, dimension);
        this.transportables = new ArrayList<>();
        this.agvSet = new HashSet<>();
    }

    public PickUpLocal(){
        super(null,null);
        this.transportables = new ArrayList<>();
        this.agvSet = new HashSet<>();
    }

    /**
     * Adds a new Transportable object to the list.
     *
     * @param transportable
     * @return true if added, false otherwise
     */
    public boolean add(Transportable transportable){
        return transportables.add(transportable);
    }

    @Override
    public Position getPosition() {
        return super.position;
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

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                if (i == startX || i == endX - 1 || j == startY || j == endY - 1)  {
                    matrix[i][j] = 'P';
                } else {
                    matrix[i][j] = '.';
                }
            }
            matrix[i][48] = '.';
        }
    }

    @Override
    public Dimension getDimension() {
        return super.dimension;
    }

    @Override
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Returns the list of transportables
     *
     * @return list of transportables
     */
    public List<Transportable> getProductList() {
        return transportables;
    }

    /**
     * Sets a new transportable list.
     *
     * @param transportables
     */
    public void setProductList(List<Transportable> transportables) {
        this.transportables = transportables;
    }

    /**
     * This method is used when a vehicle can unload their packaging into
     * this drop off local.
     *
     * @param vehicle
     * @return
     */
    public boolean load(AGV vehicle) {
        if(vehicle == null){
            return false;
        }

        for (Transportable p: vehicle.getPackages()) {
            Transportable newP = p;
            add(newP);
            vehicle.getPackages().remove(p);
            return true;
        }

        return false;
    }

    /**
     * Method used to detect if there are vehicles inside its parameters.
     *
     * @param distributionCenter
     */
    public void detectAGV(DistributionCenter distributionCenter) {
        Set<AGV> detectedAGVs = new HashSet<>();

        for (AGV agv : distributionCenter.getVehicles()) {
            Position agvPosition = agv.getPosition();
            if (isPositionInsideLocal(agvPosition)) {
                detectedAGVs.add(agv);
                agv.setPickUpLocal(this);
            } else {
                agv.setPickUpLocal(null);
                detectedAGVs.remove(agv);
            }
        }

        agvSet = detectedAGVs;
    }

    /**
     * Auxiliary method. Checks if vehicles position corresponds to the inside area of this local.
     *
     * @param position
     * @return true if it is, false otherwise
     */
    private boolean isPositionInsideLocal(Position position) {
        int centerX = getPosition().getX();
        int centerY = getPosition().getY();
        int width = getDimension().getX();
        int height = getDimension().getY();

        int startX = centerX - width / 2;
        int endX = startX + width;
        int startY = centerY - height / 2;
        int endY = startY + height;

        int posX = position.getX();
        int posY = position.getY();

        return (posX >= startX && posX < endX && posY >= startY && posY < endY);
    }


    /**
     * Returns the set of Vehicle currently standing inside this location.
     */
    public Set<AGV> getVehicleSet() {
        return agvSet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: PickUp" + "\n");
        sb.append("Position: " + getPosition().getY() + "," + getPosition().getY() + "\n");
        sb.append("Dimension: " + getDimension().getX() + "," + getDimension().getY());
        return sb.toString();
    }
}
