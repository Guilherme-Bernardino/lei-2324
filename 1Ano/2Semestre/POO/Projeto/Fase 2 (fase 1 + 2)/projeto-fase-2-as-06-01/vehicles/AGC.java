package vehicles;

import coords.Position;
import locals.DropOffLocal;
import locals.PickUpLocal;
import products.Package;
import products.Transportable;
import sensors.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * EN
 * The AGC is an AGV derived class, used to identify an Automatic Guided Cart, a vehicle.
 * This vehicle uses a list of transportables and can add to this list.
 * Since it implements the Localizable interface, this object can be visually represented in a 2d matrix, using its
 * dimension and position.
 *
 * PT
 * O AGC é uma classe derivada do AGV, utilizada para identificar um Automatic Guided Cart, um veículo.
 * Este veículo usa uma lista de transportáveis e pode ser adicionado a esta lista.
 * Como implementa a interface Localizable, este objeto pode ser representado visualmente numa matriz 2d, usando a sua
 * dimensão e posição.
 *
 * @author guilh
 */
public class AGC extends AGV {

    private List<Transportable> transportableList;

    private final int WEIGHT_LIMIT = 100;

    public AGC() {
        super();
        this.transportableList = new ArrayList<>();
    }

    @Override
    public void setPickUpLocal(PickUpLocal pickUp) {
        if(pickUp == null){
            return;
        }
        this.pickUp = pickUp;
    }

    @Override
    public void setDropOfLocal(DropOffLocal dropOf) {
        if(dropOf == null){
            return;
        }
        this.dropOf = dropOf;
    }

    @Override
    public void move() {
        if (path != null && !path.isEmpty()) {
            direction = path.remove(0);

            if(direction == Direction.UP){
                position.setY(position.getY() - speed);
            }

            if(direction == Direction.DOWN){
                position.setY(position.getY() + speed);
            }

            if(direction == Direction.LEFT){
                position.setX(position.getX() - speed);
            }

            if(direction == Direction.RIGHT){
                position.setX(position.getX() + speed);
            }

            if (path.isEmpty()) {
                direction = Direction.STOP;
            }
        } else {
            direction = Direction.STOP;
        }
    }

    @Override
    public List<Transportable> getPackages() {
        return transportableList;
    }

    /**
     * Add a transportable object to the transportables list.
     *
     * @param e
     * @return true if added, false otherwise
     */
    public boolean addPackage(Transportable e) {
        if(e == null){
            return false;
        }
        return transportableList.add(e);
    }


    /**
     * Adds multiple transportables. Accounts for weight limit.
     *
     * @param packages
     * @return true if added, false otherwise
     */
    public boolean addPackages(List<Transportable> packages) {
        double weight = 0;

        if(packages == null || packages.isEmpty()){
            return false;
        }

        if(weight >= WEIGHT_LIMIT){
            return false;
        }

        for (Transportable p : packages){
            weight += p.getTotalWeight();
            addPackage(p);
        }

        return true;
    }

    @Override
    public boolean unload(){
        if(pickUp == null){
            return false;
        }

        return pickUp.load(this);
    }

    @Override
    public boolean load(List<Transportable> packageList){
        if(dropOf == null){
            return false;
        }


        return addPackages(packageList);
    }

    @Override
    public boolean retrieveFromShelf(Package pack) {
        if(pack == null){
            return false;
        }
        return transportableList.add(pack);
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
        int width = 1;
        int height = 1;

        int startX = centerX - width / 2;
        int endX = startX + width;
        int startY = centerY - height / 2;
        int endY = startY + height;

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                if (i == startX || i == endX - 1 || j == startY || j == endY - 1)  {
                    matrix[i][j] = 'A';
                } else {
                    matrix[i][j] = '.';
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: Automatic Guided Cart ");
        sb.append("Position: " + position.getX() + "," + position.getY() + "\n");
        return sb.toString();
    }
}
